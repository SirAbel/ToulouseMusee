package MuseeToulouse

class DemandeVisite {

    Integer code
    Date dateDebutPeriode
    Date dateFinPeriode
    Integer nbPersonnes
    String statut

    static constraints = {

        code min:1
        nbPersonnes min: 1, max: 6
        dateDebutPeriode min: new Date()
        dateFinPeriode validator: { val, obj->
            obj.dateDebutPeriode.before(val)
        }
        statut inList: ['Pending', 'Confirmed', 'Refused']
        museumsRequested nullable: true
    }

    static hasMany = [
            museumsRequested : DemandeVisiteMusee
    ]
}
