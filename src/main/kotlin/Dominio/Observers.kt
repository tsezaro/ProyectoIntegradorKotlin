

/*A este comportamiento habitual queremos brindarle la posibilidad al usuario que active o desactive
otros de forma dinámica.
Al momento tenemos para ofrecer los siguientes:*/

interface ViajeObserver {
    fun postViaje( viaje: Viaje)
}

class ObserverMailAmigo( var servicioCorreo: MailSender,viaje: Viaje) : ViajeObserver {
/*
    1)Enviar a los amigos que desean el destino del viaje un mail avisando que visitaron el destino.
    El mail se envía desde
    from=app@holamundo.com ,
    to= amigo con destino deseado
    subject =“Visitaron un destino que te puede interesar”
    content= “ Hola! <nombre usuario receptor>, <nombre y apellido usuario emisor> visitó <País y Ciudad destino>.
*/

    var listaAmigos: MutableList<Usuario> = viaje.viajero.amigos

    override fun postViaje( viaje: Viaje) {

        var loDesean: MutableList<Usuario> =
            listaAmigos.filter {it.destinosDeseados.contains(viaje.itinerario.destino)} as MutableList<Usuario>

        loDesean.forEach {
            servicioCorreo.sendMail(
                Mail(
                    from = "app@holamundo.com",
                    to = it.nombre,
                    subject = "Visitaron un destino que te puede interesar",
                    content = " Hola!! ${ it.nombre}, ${ viaje.viajero.nombre}, ${viaje.viajero.apellido}" +
                            " visito ${ viaje.itinerario.destino.pais} ${ viaje.itinerario.destino.ciudad}"
                )
            )
        }
    }
}

class ObserverNoEsLocal() : ViajeObserver {
    /*
    2)Si el viaje no es local, modificar el criterio de selección de itinerario a localista,
    esto le asegura al usuario que la app va a proponer destinos locales para cuando quiera armar un nuevo viaje).
    */
    override fun postViaje(viaje: Viaje) {
        if (!viaje.itinerario.destino.esLocal()) {
            viaje.viajero.criterioItinerario = Localista()
        }
    }
}

class ObserverPasaAPuntuar() : ViajeObserver {
    /*3)Agregar a los itinerarios a puntuar el itinerario del viaje.*/
    override fun postViaje(viaje: Viaje) {
        viaje.itinerario.puntuar(viaje.viajero,-1)
    }
}

class ObserverViajeSinConvenioVehiculo() : ViajeObserver {
    /*
        4)Si el viaje no tiene un vehículo con convenio, el usuario se convierte en selectivo, y le pasan
        a gustar los vehículos de una marca con convenio.
    */
    override fun postViaje( viaje: Viaje) {
        if (!viaje.vehiculo.tieneConvenio()) {
            viaje.viajero.criterioVehiculo = Selectivo(Marcas.HONDA)
        }
    }
}

interface MailSender {
    fun sendMail(mail: Mail)
}

data class Mail(val from: String, val to: String, val subject: String, val content: String) {}

