package MuseeToulouse
import grails.transaction.Transactional

@Transactional
class MuseeService {


    List<Musee> favorites = new ArrayList<Musee>();

    Musee insertOrUpdateMusee(Musee unMusee, Gestionnaire unGestionnaire) {
        unGestionnaire.save()
        unGestionnaire.addToMusees(unMusee)
        unMusee.save(flush: true)
        return unMusee

    }

    //marche quand utilisé dans les test
    //d'integration mais pas depuis le controlleur??
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
            //join 'gestionnaire'
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
        favorites.sort{a,b->a.getId()<=>b.getId()}
    }

    List<Musee> reconstructList(String param,String name) {

        List<Musee> instanceList = new ArrayList<Musee>()
        if(param.length()<=5) {
            return instanceList
        }
        def l = param.length()-2
        param = param.getAt(0 .. l)
        def var = param.split(",")
        def length = var.length

        for(int i=0;i<length;i++) {

            def temp = Musee.get(Integer.parseInt(var[i].split(":")[1].toString().trim()))
            if(!temp.getNom().equals(name)) {
                instanceList.add(temp)
            }
        }

        return instanceList
    }

    List<Musee> filtreFavorites(List<Musee> museesList) {

        for (int i=0;i<favorites.size();i++) {
            for(int j=0;j<museesList.size();j++) {
                if(favorites.get(i).getNom().equals(museesList.get(j).getNom())) {
                    museesList.remove(j)
                }
            }
        }

        return museesList
    }


}
