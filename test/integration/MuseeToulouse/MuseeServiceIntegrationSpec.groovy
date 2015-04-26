package MuseeToulouse


import spock.lang.*

/**
 *
 */
class MuseeServiceIntegrationSpec extends Specification {

    MuseeService museeService
    InitializationService initializationService

    void "test insertion ou mise à jour d'un musée"() {

        given:"un musée et son adresse"
        Adresse adresseMusee = new Adresse(numero: "48", rue: "rue du pouset",
                codePostal: 72000, ville: "Paris")
        Musee unMusee = new Musee(nom: "Louvre",
                horairesOuverture: "Toute la journée",
                telephone: "05 91 63 31 42", accesMetro: "??",
                accesBus: "??", adresse: adresseMusee)

        and: "un gestionnaire"
        Gestionnaire unGestionnaire = new Gestionnaire(nom: "Abel")

        when: "on tente de répercuter en base la création ou " +
                "la modification du musée"
        Musee resultMusee =
                museeService.insertOrUpdateMusee(unMusee,unGestionnaire)

        then: "On test si on a toujours le même musée"
        resultMusee == unMusee

        and:"On vérifie si il n'y pas d'erreur"
        !resultMusee.hasErrors()

        and:"le musée a un id valide"
        resultMusee.id

        and:"On cherche si l'id correspond à une entrée en BD"
        Musee.findById(resultMusee.id) != null

        and: "Vérifie si on a le bon gestionnaire"
        resultMusee.gestionnaire == unGestionnaire

        and:"si l'ajout du musée a éte fais dans la list du gestionnaire"
        unGestionnaire.musees.contains(resultMusee)
    }

    void "test de suppression d'un musée"() {

        given: "un museé dans la BD et son gestionnaire"
        def unMusee = Musee.get(1)
        def unGestionnaire = unMusee.gestionnaire


        when:"On lance la suppression"
        museeService.deleteMusee(unMusee)

        then:"le musée n'est plus dans la BD"
        Musee.findById(unMusee.id) == null

        and:"la suppression est cascadé"
        !unGestionnaire.musees.contains(unMusee)
    }

    void "test du moteur de recherche"() {

        given:"le jeu de Test du service d'initialisation "
        initializationService

        when: "on cherche les musées avec un nom contenant 'see' "
        List<Musee> res = museeService.searchMusees("archive",null,null)

        then: "on récupère tout les musée le premier musee"
        res.size() == 1
        res*.id == [1]

        when: "on cherche les musées avec un code postal = 31400"
        res = museeService.searchMusees(null,"31300",null)

        then: "on a 2 match musee de l'histoire et musee des instruments"
        res.size() == 2
        res*.id == [6,9]

        and: "ils sont ordonnés par nom"
        res*.nom == ["MUSEE DE L'HISTOIRE DE LA MEDECINE DE TOULOUSE",
                     "MUSEE DES INSTRUMENTS DE MEDECINE DES HOPITAUX DE TOULOUSE"]

        when: "on cherche les musee avec un nom de rue contenant 'Saints'"
        res = museeService.searchMusees(null,null,'japon')

        then: "on recupère le musée 11"
        res.size() == 1
        res*.id == [11]

        when: "on cherche les musées avec un nom inexistant"
        res = museeService.searchMusees("test",null,null)

        then: "pas de match"
        res.size() == 0

        when: "effectue une recherche sans paramètres"
        res = museeService.searchMusees(null, null, null)

        then: "on récupère tout les musées"
        res.size() == 12
    }

    void "test de la fonction filtre favoris"() {

        given: "un jeu de test"
        initializationService
        def unMusee = Musee.get(1)

        when: "on récupère l'unique musee avec un CP 31500, liste de favoris avec celui-ci"
        def liste = museeService.searchMusees(null, "31500", null)
        museeService.favorites.add(unMusee)

        and: "on effectue le filtrage"
        def res = museeService.filtreFavorites(liste)

        then: "la liste retourné est vide"
        res.size() == 0
    }

    void "test de la fonction de reconstruction de liste"() {

        given: "un jeu de test"
        initializationService
        def mapParam = [musee1: 1, musee2: 2, musee3: 3]

        when: "on a une chaine de caractère contenant la Map et un nom de musee"
        def chaineListe = mapParam.toString()
        def chaineNom = Musee.get(1).getNom()

        and: "on lance la reconstruction de la liste"
        def res = museeService.reconstructList(chaineListe, chaineNom)

        then: "une liste de 2 element est retourné"
        res.size() == 2

        and: "le premier musee n'est plus dans la liste"
        res*.nom == [Musee.get(2).nom, Musee.get(3).nom]

        when: "on lance la reconstruction avec un nom n'appartenant pas à la liste"
        def res2 = museeService.reconstructList(chaineListe, "none")

        then: "une liste de 3 elements est retournée"
        res2.size() == 3

        and: "aucun des musee n'a été supprimé"
        res2*.nom == [Musee.get(1).nom, Musee.get(2).nom, Musee.get(3).nom]

        when: "on passe en paramètre une map incohérente avec les données manipulées"
        res = museeService.reconstructList("ggg","none")

        then: "on a une liste vide en retour"
        res.size() == 0
    }

    void "test ajout favoris"() {

        given: "un musee de la BD"
        def unMusee = Musee.get(1)

        when : "la liste des favoris est vide"
        museeService.favorites.clear()

        and: "on ajoute le musee à la liste"
        museeService.addFavorite(unMusee)

        then: "la taille de la liste est incrémentée de 1"
        museeService.favorites.size() == 1

        and: "le seul element de la liste est unMusee"
        museeService.favorites*.nom == [unMusee.nom]

        when: "on ajoute un second element"
        def unSecondMusee = Musee.get(2)
        museeService.addFavorite(unSecondMusee)

        then: "la taille de la liste est incrémentée"
        museeService.favorites.size() == 2

        then: "les favoris sont ordonnés par ordre croissant de noms"
        museeService.favorites*.nom == [unMusee.nom,unSecondMusee.nom]
    }

    void "test suppression favoris"() {

        given: "un musee inseré dans une liste de favoris vide"
        def unMusee = Musee.get(1)
        museeService.favorites.clear()
        museeService.favorites.add(unMusee)

        when: "on lance la suppression de cette élement"
        museeService.removefavorite(unMusee.getNom())

        then: "la liste est de nouveau vide"
        museeService.favorites.size() == 0
    }
}
