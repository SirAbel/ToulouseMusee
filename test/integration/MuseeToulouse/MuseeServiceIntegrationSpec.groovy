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

        when:"on cherche les musées avec un nom contenant 'see' "
        List<Musee> res = museeService.searchMusees("archive",null,null)

        then:"on récupère tout les musée le premier musee"
        res.size() == 1
        res*.id == [1]

        when:"on cherche les musées avec un code postal = 31400"
        res = museeService.searchMusees(null,"31300",null)

        then:"on a 2 match musee de l'histoire et musee des instruments"
        res.size() == 2
        res*.id == [6,9]

        and:"ils sont ordonnés par nom"
        res*.nom == ["MUSEE DE L'HISTOIRE DE LA MEDECINE DE TOULOUSE",
                     "MUSEE DES INSTRUMENTS DE MEDECINE DES HOPITAUX DE TOULOUSE"]

        when:"on cherche les musee avec un nom de rue contenant 'Saints'"
        res = museeService.searchMusees(null,null,'japon')

        then:"on recupère le musée 11"
        res.size() == 1
        res*.id == [11]

        when:"on cherche les musées avec un nom inexistant"
        res = museeService.searchMusees("test",null,null)

        then: "pas de match"
        res.size() == 0

        when:"effectue une recherche sans paramètres"
        res = museeService.searchMusees(null, null, null)

        then: "on récupère tout les musées"
        res.size() == 12

        /*and:"ils sont ordonnés suivant le nom du musée"
        res*.nom == [initializationService.musee1.nom,
                     initializationService.musee2.nom,
                     initializationService.musee3.nom,
                     initializationService.musee4.nom]*/
    }
}
