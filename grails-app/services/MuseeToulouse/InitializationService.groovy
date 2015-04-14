package MuseeToulouse

import grails.transaction.Transactional

@Transactional
class InitializationService {

    MuseeService museeService

    Gestionnaire gestionnaire1
    Gestionnaire gestionnaire2
    Gestionnaire gestionnaire3
    Gestionnaire gestionnaire4

    Musee musee1
    Musee musee2
    Musee musee3
    Musee musee4

    Adresse adresse1
    Adresse adresse2
    Adresse adresse3
    Adresse adresse4


    def populateDB() {
        if(!Musee.count()) {
            gestionnaire1 = new Gestionnaire(nom:'patrick').save()
            gestionnaire2 = new Gestionnaire(nom: 'Helena').save()
            gestionnaire3 = new Gestionnaire(nom: 'Rose').save()
            gestionnaire4 = new Gestionnaire(nom: 'Bob').save()

            adresse1 = new Adresse(numero: 3,rue: 'RUE DU JAPON',codePostal: 31400,ville: 'Toulouse')
            adresse2 = new Adresse(numero: 13,rue: 'RUE DU JAPON',codePostal: 31400,ville: 'Toulouse')
            adresse3 = new Adresse(numero: 25,rue: 'RUE DU JAPON',codePostal: 31400,ville: 'Toulouse')
            adresse4 = new Adresse(numero: 40,rue: 'RUE DU JAPON',codePostal: 31400,ville: 'Toulouse')


            musee1 = new Musee(nom: 'musee1', horairesOuverture: 'matin et après-midi', telephone: "0624228601", accesMetro:'pas dacces',accesBus:'faut pas exagerer nn plus',adresse: adresse1)
            musee2 = new Musee(nom: 'musee2', horairesOuverture: 'matin et après-midi', telephone: "0624238601", accesMetro:'pas dacces',accesBus:'faut pas exagerer nn plus',adresse: adresse2)
            musee3 = new Musee(nom: 'musee3', horairesOuverture: 'matin et après-midi', telephone: "0624238601", accesMetro:'pas dacces',accesBus:'faut pas exagerer nn plus',adresse: adresse3)
            musee4 = new Musee(nom: 'musee4', horairesOuverture: 'matin et après-midi', telephone: "0624238601", accesMetro:'pas dacces',accesBus:'faut pas exagerer nn plus',adresse: adresse4)

            museeService.insertOrUpdateMusee(musee1, gestionnaire1)
            museeService.insertOrUpdateMusee(musee2, gestionnaire2)
            museeService.insertOrUpdateMusee(musee3, gestionnaire3)
            museeService.insertOrUpdateMusee(musee4, gestionnaire4)

        }
    }
}
