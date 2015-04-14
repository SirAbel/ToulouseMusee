package MuseeToulouse

import grails.transaction.Transactional

@Transactional
class MuseeService {


    Musee insertOrUpdateMusee(Musee unMusee, Gestionnaire unGestionnaire) {

        unGestionnaire.save()
        unGestionnaire.addToMusees(unMusee)
        unMusee.save()
        return unMusee

    }

    void deleteMusee(Musee unMusee) {
        unMusee.delete()
    }
}
