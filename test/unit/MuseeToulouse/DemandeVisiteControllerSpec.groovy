package MuseeToulouse


import grails.test.mixin.*
import spock.lang.*
import sun.util.calendar.Gregorian

@TestFor(DemandeVisiteController)
@Mock([DemandeVisite,DemandeVisiteService])
class DemandeVisiteControllerSpec extends Specification {

    //DemandeVisiteService demandeVisiteService = new DemandeVisiteService()


    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
        params["code"] = 35
        params["dateDebutPeriode"] = new GregorianCalendar(2015, Calendar.DECEMBER, 28).time
        params["dateFinPeriode"] = new GregorianCalendar(2015, Calendar.DECEMBER, 31).time
        params["nbPersonnes"] = 4
        params["statut"] = 'Pending'

    }

    void "Test the index action returns the correct model"() {

        when: "The index action is executed"
        controller.index()

        then: "The model is correct"
        !model.demandeVisiteInstanceList
        model.demandeVisiteInstanceCount == 0
    }

    /*
    void "Test the redirectedCreation action returns the correct model"() {

        given: "the service"
        controller.demandeVisiteService = demandeVisiteService

        when: "The index action is executed with the populated params"
        controller.params.favoriteList = "[musee1: 1,musee2: 2]"
        controller.redirectedCreation()

        then: "The model is correct"
        model.favorieList
        model.favorieListCount == 2
    }*/


    void "Test the create action returns the correct model"() {
        when: "The create action is executed"
        controller.create()

        then: "The model is correctly created"
        model.demandeVisiteInstance != null
    }

    void "Test the save action correctly persists an instance"() {

        when: "The save action is executed with an invalid instance"
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'POST'
        def demandeVisite = new DemandeVisite()
        demandeVisite.validate()
        controller.save(demandeVisite)

        then: "The create view is rendered again with the correct model"
        model.demandeVisiteInstance != null
        view == 'create'

        when: "The save action is executed with a valid instance"
        response.reset()
        populateValidParams(params)
        demandeVisite = new DemandeVisite(params)

        controller.save(demandeVisite)

        then: "A redirect is issued to the show action"
        response.redirectedUrl == '/demandeVisite/show/1'
        controller.flash.message != null
        DemandeVisite.count() == 1
    }

    void "Test that the show action returns the correct model"() {
        when: "The show action is executed with a null domain"
        controller.show(null)

        then: "A 404 error is returned"
        response.status == 404

        when: "A domain instance is passed to the show action"
        populateValidParams(params)
        def demandeVisite = new DemandeVisite(params)
        controller.show(demandeVisite)

        then: "A model is populated containing the domain instance"
        model.demandeVisiteInstance == demandeVisite
    }

    void "Test that the edit action returns the correct model"() {
        when: "The edit action is executed with a null domain"
        controller.edit(null)

        then: "A 404 error is returned"
        response.status == 404

        when: "A domain instance is passed to the edit action"
        populateValidParams(params)
        def demandeVisite = new DemandeVisite(params)
        controller.edit(demandeVisite)

        then: "A model is populated containing the domain instance"
        model.demandeVisiteInstance == demandeVisite
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when: "Update is called for a domain instance that doesn't exist"
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'PUT'
        controller.update(null)

        then: "A 404 error is returned"
        response.redirectedUrl == '/demandeVisite/index'
        flash.message != null


        when: "An invalid domain instance is passed to the update action"
        response.reset()
        def demandeVisite = new DemandeVisite()
        demandeVisite.validate()
        controller.update(demandeVisite)

        then: "The edit view is rendered again with the invalid instance"
        view == 'edit'
        model.demandeVisiteInstance == demandeVisite

        when: "A valid domain instance is passed to the update action"
        response.reset()
        populateValidParams(params)
        demandeVisite = new DemandeVisite(params).save(flush: true)
        controller.update(demandeVisite)

        then: "A redirect is issues to the show action"
        response.redirectedUrl == "/demandeVisite/show/$demandeVisite.id"
        flash.message != null
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when: "The delete action is called for a null instance"
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'DELETE'
        controller.delete(null)

        then: "A 404 is returned"
        response.redirectedUrl == '/demandeVisite/index'
        flash.message != null

        when: "A domain instance is created"
        response.reset()
        populateValidParams(params)
        def demandeVisite = new DemandeVisite(params).save(flush: true)

        then: "It exists"
        DemandeVisite.count() == 1

        when: "The domain instance is passed to the delete action"
        controller.delete(demandeVisite)

        then: "The instance is deleted"
        DemandeVisite.count() == 0
        response.redirectedUrl == '/demandeVisite/index'
        flash.message != null
    }
}
