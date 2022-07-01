package Dominio

import Destino
import Itinerario
import Mail
import MailSender
import Repositorio
import Usuario

abstract class Tarea (var servicioCorreo: MailSender){

    fun ejecutar(usuario: Usuario) {
        this.ejecutarParticular(usuario)

        this.servicioCorreo.sendMail(
            Mail(
                from = "app@holamundo.com",
                to = usuario.correoElectronico,
                subject = "Se realizo la tarea ${this.tipo()}",
                content = "Se realizo la tarea ${this.tipo()}"
            )
        )
    }

    abstract fun ejecutarParticular(usuario: Usuario)

    abstract fun tipo() : String

}

class puntuarItinerario(
    var puntaje: Int,
    var itinerarios: Repositorio<Itinerario>,
    servicioCorreo: MailSender,
) : Tarea(servicioCorreo) {
    /*
    Darle un puntaje a los itinerarios a puntuar. Para eso se debe especificar
    el puntaje que queremos darle a todos los itinerarios.
    */
    override fun ejecutarParticular(usuario: Usuario) {
        this.itinerarios.memoria.filter { it.noPuntuo(usuario) }.forEach{ it.puntuar(usuario, this.puntaje) }
    }

    override fun tipo() = "puntuar itinerario"
}

class transferirItinerario(
    var itinerarios: Repositorio<Itinerario>,
    servicioCorreo: MailSender
) : Tarea(servicioCorreo) {
    /*
    Transferir todos sus itinerarios al amigo que menos destinos visitados tenga.
    */
    // nota de herni: esto me hace pensar que son itinerarios de los viajes que realizo y los que creó.

    fun itinerariosDeUsuario(usuario: Usuario): List<Itinerario> {
        return this.itinerarios.memoria.filter { it.obtenerUsuarios().contains(usuario) }
    }

    fun amigoConMenosDestinos(usuario: Usuario): Usuario {
        return usuario.amigos.minByOrNull { it.destinosVisitados.size }!!
    }

    override fun ejecutarParticular(usuario: Usuario) {
        this.itinerariosDeUsuario(usuario).forEach { it.agregarUsuario(this.amigoConMenosDestinos(usuario)) }
    }

    override fun tipo() = "transferir itinerario"

}

class hacerceAmigosConMismoDestino(
    var destino: Destino,
    var usuarios: Repositorio<Usuario>,
    servicioCorreo: MailSender
) : Tarea(servicioCorreo) {
    /*
    Hacerse amigo de todos los usuarios que conozcan un destino específico.
    */
    override fun ejecutarParticular(usuario: Usuario) {
        usuarios.memoria.filter { it.conoceDestino(destino) }.filter { it != usuario }.forEach { usuario.amigos.add(it) }
    }

    override fun tipo() = "Hacerse amigo Con mismo destino"
}

class agregarDestinosMasCaros(
    servicioCorreo: MailSender
) : Tarea(servicioCorreo) {

    /*Agregar a los destinos deseados, el destino deseado más caro de
    cada uno de sus amigos (el que el amigo considere más caro para sí mismo).*/

    override fun ejecutarParticular(usuario: Usuario) {
        usuario.amigos
            .map { amigo -> amigo.destinosDeseados.maxByOrNull { it.costoTotal(amigo) }!! }
            .toSet().toList()
            .forEach { usuario.destinosDeseados.add(it) }
    }

    override fun tipo() = "Se realizo la tarea agregarDestinosMasCaros"
}

