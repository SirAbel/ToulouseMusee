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

    static hasMany = [
            visiteRequests : DemandeVisiteMusee
    ]

    static constraints = {

        nom blank: false
        gestionnaire nullable: true
        horairesOuverture blank: false
        telephone blank: false
        accesMetro blank: false
        accesBus blank: false
        visiteRequests nullable: true

    }
}
