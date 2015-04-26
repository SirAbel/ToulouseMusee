package MuseeToulouse


import spock.lang.*

/**
 *
 */
class DemandeVisiteServiceIntegrationSpec extends Specification {

    InitializationService initializationService
    DemandeVisiteService demandeVisiteService

    def setup() {
    }

    def cleanup() {
    }

    void "test reconstruct list"() {

        given: "un jeu de Test et un string representant une Map"
        initializationService
        def chaineMap = [musee1: 1, musee2: 2, musee3: 3].toString()

        when: "on lance la reconstruction de la chaine"
        def res = demandeVisiteService.reconstructList(chaineMap)

        then: "on obtient une liste de 3 elements"
        res.size() == 3

        and: "on a la liste des musee du map"
        res*.nom == [Musee.get(1).nom, Musee.get(2).nom, Musee.get(3).nom]

        when: "on lance la reconstruction avec une chaine non conforme"
        res = demandeVisiteService.reconstructList("fgg")

        then: "on obtient une liste vide"
        res.size() == 0
    }
}
