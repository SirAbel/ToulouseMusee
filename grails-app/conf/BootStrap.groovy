import MuseeToulouse.InitializationService

class BootStrap {

    InitializationService initializationService

    def init = { servletContext ->

        initializationService.populateDB()
    }
    def destroy = {
    }
}
