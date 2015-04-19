package MuseeToulouse


import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class MuseeController {

    MuseeService museeService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def doSearchMuseum() {
        def museesList =
                museeService.searchMusees(params.nom, params.CP, params.rue)
        museeService.favorites.clear()
        render(view: 'index', model: [museeInstanceList: museesList,
                                      museeInstanceCount: museesList.size()])
    }

    def removeFromFavorite() {
        params.max = 5
        museeService.removefavorite(params.instance)
        def favorites = museeService.favorites
        render(view: 'index', model: [favoriteList: favorites,
                                      favoriteListCount: favorites.size(),
                                      museeInstanceList: Musee.list(params),
                                      museeInstanceCount: Musee.count])
    }

    def selectFavorite() {
        params.max = 5
        def instance = museeService.searchMusees(params.instance,null,null)
        museeService.addFavorite(instance.get(0))
        def favorites = museeService.favorites
        render(view: 'index', model: [favoriteList: favorites,
                                      favoriteListCount: favorites.size(),
                                      museeInstanceList: Musee.list(params),
                                      museeInstanceCount: Musee.count])
    }

    def index(Integer max) {
        params.max = Math.min(max ?: 5, 100)
        respond Musee.list(params), model: [museeInstanceCount: Musee.count()]
    }

    def show(Musee museeInstance) {
        respond museeInstance
    }

    def create() {
        respond new Musee(params)
    }

    @Transactional
    def save(Musee museeInstance) {
        if (museeInstance == null) {
            notFound()
            return
        }

        if (museeInstance.hasErrors()) {
            respond museeInstance.errors, view: 'create'
            return
        }

        museeInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message',
                        args: [message(code: 'musee.label', default: 'Musee'),
                               museeInstance.id])
                redirect museeInstance
            }
            '*' { respond museeInstance, [status: CREATED] }
        }
    }

    def edit(Musee museeInstance) {
        respond museeInstance
    }

    @Transactional
    def update(Musee museeInstance) {
        if (museeInstance == null) {
            notFound()
            return
        }

        if (museeInstance.hasErrors()) {
            respond museeInstance.errors, view: 'edit'
            return
        }

        museeInstance.save flush: true


        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message',
                        args: [message(code: 'Musee.label', default: 'Musee'),
                               museeInstance.id])
                redirect museeInstance
            }
            '*' { respond museeInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Musee museeInstance) {

        if (museeInstance == null) {
            notFound()
            return
        }

        museeInstance.delete flush: true
        //museeService.deleteMusee(museeInstance)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message',
                        args: [message(code: 'Musee.label', default: 'Musee'),
                               museeInstance.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message',
                        args: [message(code: 'musee.label', default: 'Musee'),
                               params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
