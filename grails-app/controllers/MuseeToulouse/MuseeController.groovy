package MuseeToulouse


import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class MuseeController {

    MuseeService museeService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def doSearchMuseum() {
        if(params.var) {
            museeService.favorites.clear()
        }

        def favorites = museeService.favorites
        def museesList = []
        if(!params.accueil) {
            museesList = museeService.searchMusees(params.nom, params.CP, params.rue)
            museesList = museeService.filtreFavorites(museesList)
        }
        render(view: 'search', model: [favoriteList: favorites,
                                       favoriteListCount: favorites.size(),
                                       museeInstanceList: museesList,
                                      museeInstanceCount: museesList.size()])
    }

    def paginatedResults() {
        params.paginate = Integer.parseInt(params.paginate)
        def favorites = museeService.favorites
        List<Musee> museesList = museeService.reconstructList(params.museeInstanceList,"none")
        render(view: params.view, model: [favoriteList: favorites,
                                          favoriteListCount: favorites.size(),
                                          museeInstanceList: museesList,
                                          museeInstanceCount: museesList.size()])
    }

    def removeFromFavorite() {
        museeService.removefavorite(params.instance)
        def instance = museeService.searchMusees(params.instance,null,null)
        List<Musee> museesList = museeService.reconstructList(params.museeInstanceList,params.instance)
        museesList.add(instance.get(0))
        museesList.sort{a,b->a.getId()<=>b.getId()}
        def favorites = museeService.favorites
        render(view: params.view, model: [favoriteList: favorites,
                                      favoriteListCount: favorites.size(),
                                      museeInstanceList: museesList,
                                      museeInstanceCount: museesList.size()])
    }

    def selectFavorite() {
        def instance = museeService.searchMusees(params.instance,null,null)
        museeService.addFavorite(instance.get(0))
        List<Musee> museesList = museeService.reconstructList(params.museeInstanceList,params.instance)
        def favorites = museeService.favorites
        render(view: params.view, model: [favoriteList: favorites,
                                      favoriteListCount: favorites.size(),
                                      museeInstanceList: museesList,
                                      museeInstanceCount: museesList.size()])
    }

    def index(Integer max) {
        def favorites = museeService.favorites
        museeService.favorites.clear()
        respond Musee.list(params), model: [museeInstanceCount: Musee.count(),
                                            favoriteList: favorites,
                                            favoriteListCount: favorites.size()]
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
