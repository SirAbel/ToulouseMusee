package MuseeToulouse

import grails.transaction.Transactional

@Transactional
class MuseeService {

    List<Musee> favorites = new ArrayList<Musee>();

    Musee insertOrUpdateMusee(Musee unMusee, Gestionnaire unGestionnaire) {

        unGestionnaire.save()
        unGestionnaire.addToMusees(unMusee)
        unMusee.save(flush: true, validate: false)
        return unMusee

    }

    //marche quand utilisé dans les test
    //d'integration mais pas depuis le controlleur
    void deleteMusee(Musee unMusee) {
        unMusee.delete(flush: true)
    }

    List<Musee> searchMusees(String nomMusee, String codePostalMusee, String rueMusee) {
        def criteria = Musee.createCriteria()
        List<Musee> result = criteria.list() {
            if (nomMusee) {

                ilike 'nom', "%${nomMusee}%"
            }
            if (codePostalMusee && codePostalMusee !='0') {
                def codePostal = Integer.parseInt(codePostalMusee)
                eq 'adresse.codePostal', codePostal
            }
            if (rueMusee) {

                ilike 'adresse.rue', "%${rueMusee}%"
            }

            order('nom')
        }
        result
    }

    void removefavorite(String name) {
        for(int i=0;i<favorites.size();i++) {
            def elem = favorites.get(i)
            if( elem.getNom() == name) {
                favorites.remove(elem)
            }
        }
    }

    void addFavorite(Musee instance) {
        favorites.add(instance)
    }


}
