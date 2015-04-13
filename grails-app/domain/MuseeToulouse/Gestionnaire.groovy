package MuseeToulouse

class Gestionnaire {

    String nom

    static hasMany = [
            musees : Musee
    ]

    static constraints = {
        nom blank: false
        musees nullable: true
    }
}
