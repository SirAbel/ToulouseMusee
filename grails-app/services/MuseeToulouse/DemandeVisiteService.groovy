package MuseeToulouse

import grails.transaction.Transactional

@Transactional
class DemandeVisiteService {

    def reconstructList(String param) {

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
            instanceList.add(temp)
        }

        return instanceList
    }
}
