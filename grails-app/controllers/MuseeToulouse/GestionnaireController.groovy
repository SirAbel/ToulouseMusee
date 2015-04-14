package MuseeToulouse


import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class GestionnaireController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Gestionnaire.list(params), model: [gestionnaireInstanceCount: Gestionnaire.count()]
    }

    def show(Gestionnaire gestionnaireInstance) {
        respond gestionnaireInstance
    }

    def create() {
        respond new Gestionnaire(params)
    }

    @Transactional
    def save(Gestionnaire gestionnaireInstance) {
        if (gestionnaireInstance == null) {
            notFound()
            return
        }

        if (gestionnaireInstance.hasErrors()) {
            respond gestionnaireInstance.errors, view: 'create'
            return
        }

        gestionnaireInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'gestionnaire.label', default: 'Gestionnaire'), gestionnaireInstance.id])
                redirect gestionnaireInstance
            }
            '*' { respond gestionnaireInstance, [status: CREATED] }
        }
    }

    def edit(Gestionnaire gestionnaireInstance) {
        respond gestionnaireInstance
    }

    @Transactional
    def update(Gestionnaire gestionnaireInstance) {
        if (gestionnaireInstance == null) {
            notFound()
            return
        }

        if (gestionnaireInstance.hasErrors()) {
            respond gestionnaireInstance.errors, view: 'edit'
            return
        }

        gestionnaireInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Gestionnaire.label', default: 'Gestionnaire'), gestionnaireInstance.id])
                redirect gestionnaireInstance
            }
            '*' { respond gestionnaireInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Gestionnaire gestionnaireInstance) {

        if (gestionnaireInstance == null) {
            notFound()
            return
        }

        gestionnaireInstance.delete flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Gestionnaire.label', default: 'Gestionnaire'), gestionnaireInstance.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'gestionnaire.label', default: 'Gestionnaire'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
