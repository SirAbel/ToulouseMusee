package MuseeToulouse

class Musee {

    String nom
    String horairesOuverture
    String telephone
    String accesMetro
    String accesBus
    Gestionnaire gestionnaire
    Adresse adresse

    static embedded = ['adresse']

    static constraints = {

        nom blank: false
        gestionnaire nullable: true
        horairesOuverture blank: false
        telephone blank: false, validator: {
            it.matches("[0-9]+")
        }
        accesMetro blank: false
        accesBus blank: false

    }
}
