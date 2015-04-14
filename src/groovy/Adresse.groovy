package MuseeToulouse

class Adresse {

    Integer numero
    String rue
    Integer codePostal
    String ville


    static constraints = {

        numero min: 1, maxSize: 4
        rue blank: false
        codePostal min:10000, max: 99999
        ville blank: false

    }


    @Override
    public String toString() {
        return numero + ", " + rue + " - " + codePostal + ", " + ville
    }
}
