package MuseeToulouse
import grails.transaction.Transactional

@Transactional
class InitializationService {

    MuseeService museeService
    Gestionnaire gestionnaire1
    Musee musee1
    Adresse adresse1


    def populateDB() {

        boolean firstLine = true
        new File("resources/Musee.csv").splitEachLine(';'){ line ->
            if(firstLine || Musee.count()==12 ) {
                firstLine = false
            } else {
                gestionnaire1 = new Gestionnaire(nom: line[1].trim()).save()
                adresse1 = new Adresse(numero: line[7].trim(),
                        rue: line[8].trim(),
                        codePostal: Integer.parseInt(line[9].trim()),
                        ville: line[10].trim())
                musee1 = new Musee(nom: line[0].trim(),
                        horairesOuverture: line[2].trim(),
                        telephone: line[4].trim(),
                        accesMetro: line[5].trim(),
                        accesBus: line[6].trim(),
                        adresse: adresse1)
                museeService.insertOrUpdateMusee(musee1, gestionnaire1)
            }
        }
    }

}
