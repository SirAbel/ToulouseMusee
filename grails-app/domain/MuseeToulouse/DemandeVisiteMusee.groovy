package MuseeToulouse

class DemandeVisiteMusee {

    Date dateDemande

    static constraints = {

        dateDemande min: new Date()
    }

    static belongsTo = [
            musee:Musee, demandeVisite : DemandeVisite
    ]
}
