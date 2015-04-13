package MuseeToulouse

class Adresse {

    Integer numero
    String rue
    Integer codePostal
    String ville

    static belongsTo = [
            musee : Musee
    ]

    static constraints = {

        numero min: 1, maxSize: 4
        rue blank: false
        codePostal min:10000, max: 99999
        ville blank: false
        musee nullable: true

    }
}
