package MuseeToulouse

import grails.transaction.Transactional

@Transactional
class MuseeService {


    Musee insertOrUpdateMusee(Musee unMusee, Gestionnaire unGestionnaire) {

        unGestionnaire.save()
        unGestionnaire.addToMusees(unMusee)
        unMusee.save(flush: true, validate: false)
        return unMusee

    }

    void deleteMusee(Musee unMusee) {
        unMusee.delete()
    }


    List<Musee> searchMusees(String nomMusee, Integer codePostalMusee, String rueMusee) {
        def criteria = Musee.createCriteria()
        List<Musee> result = criteria.list {
            if (nomMusee) {

                like 'nom', "%${nomMusee}%"
            }
            if (codePostalMusee) {

                eq 'adresse.codePostal', codePostalMusee
            }
            if (rueMusee) {

                like 'adresse.rue', "%${rueMusee}%"
            }

            order('nom')
        }
        result
    }
}
