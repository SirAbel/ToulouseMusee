package MuseeToulouse



import spock.lang.*

/**
 *
 */
class InitializationServiceIntegrationSpec extends Specification {

    InitializationService initializationService


    void "ajout d'entrées dans la base de données"() {

        given: "une base de donnée vide"
        Musee.count() == 0

        when: "On insert de nouvelles entées"
        initializationService.populateDB()

        then: "12 nouvelle entrée ont été ajoutées"
        Musee.count() == 12


        when: " la BD contient 12 élements"
        Musee.count() == 12

        and: "on essai de réinserer les mêmes élements"
        initializationService.populateDB()

        then: "aucun nouvel elem n'est ajouté"
        Musee.count() == 12
    }
}
