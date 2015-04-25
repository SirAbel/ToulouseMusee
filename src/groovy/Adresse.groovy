package MuseeToulouse

class Adresse {

    String numero
    String rue
    Integer codePostal
    String ville


    static constraints = {

        numero blank: false
        rue blank: false
        codePostal min:10000, max: 99999
        ville blank: false

    }


    @Override
    public String toString() {
        return numero + ", " + rue + " - " + codePostal + ", " + ville
    }
}
