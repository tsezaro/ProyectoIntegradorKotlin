/*------------------------------- Usuarios parte 0
Usuarios:
De los usuarios nos interesa:
-Nombre: Su nombre real.
-Apellido: Su apellido real.
-Username: El alias que lo identificará dentro de la aplicación.
-Fecha de Alta: es la Fecha en que comenzó a utilizar la aplicación.
-Antigüedad: se determina a partir de la Fecha de Alta:
    (fun antiguedad(): Int)
-País de residencia: es el pais donde vive
------------------------------------------*/

/*------------------------------- Usuarios parte 1

A lo que ya sabíamos de los usuarios se agrega lo siguiente:
-Días para viajar: es la cantidad de días que dispone para realizar un viaje
-Amigos: son usuarios de la aplicación, que un usuario considera amigo. La relación de amistad
no necesariamente debe ser bidireccional.
-Destinos deseados: todos los usuarios tienen destinos que desean visitar.
-Destinos visitados: son los destinos que visitaron, en algún viaje realizado.

Es necesario determinar si un usuario conoce a un destino, esto sucede cuando el usuario desea ir al destino, o ya lo visitó.
    fun conoceDestino(destino: Destino): Boolean

Itinerarios a puntuar: solo puede puntuar itinerarios de los cuales no sea el creador y con destinos que conoce.
    fun puntuar(puntuador: Usuario, puntaje: Int)

Para saber si un usuario puede realizar un itinerario, el itinerario debe cubrir los días para viajar que tiene el
usuario, es decir que : días para viajar >= días de itinerario

A su vez, identificamos distintos criterios para terminar de definir si el usuario puede realizar el itinerario.
Al momento conocemos a los siguientes:
-los relajados: no lo dudan, cualquier itinerario le viene bien
-el precavido: solo acepta si el destino ya lo conoce o si lo conoce algún amigo.
-el localista solo le gusta viajar dentro de Argentina.
-hay soñadores: solo quieren viajar a los destinos deseados o a destinos de mayor valor que el del destino
deseado más caro.
-están los activos: estos quieren tener todos los días actividades para realizar.
otros son exigentes: quieren garantizarse que, por lo menos un determinado porcentaje de actividades del itinerario,
sean de una determinada Dificultad. (Ejemplo el 50% de las actividades sean de una Dificultad ALTA).

El sistema debe permitirle al usuario, cambiar de criterio en el momento que lo desee.

 fun puedeRealizarItinerario(itinerario: Itinerario): Boolean(USA EL STRATEGI)

Los usuarios son válidos si:
Todos los campos String no deben ser nulos o vacíos.
La fecha de alta no puede ser posterior a la del día.
Los días para viajar, deben ser mayores a cero.
Todos los usuarios deben tener al menos un destino deseado.*/

/*------------------------------- Usuarios parte 2

No a  todos los usuarios les gusta cualquier vehículo, tenemos identificado algunas formas en que determinan si les gusta.

el neófilo, le gustan los vehículos que no sean antiguos, es decir que su antigüedad sea menor a 2 años
el supersticioso, solo quiere vehículos fabricados en años pares

al caprichoso, le gustan los vehículos cuya inicial de la marca, coincida con la inicial del modelo.
el selectivo, siempre que el vehículo sea de una marca específica.
el sin límite, quiere que tenga el kilometraje libre
el combinado es la combinación de 2 ó más, de las formas de determinar mencionadas antes, en cuyo caso, para que le
guste un vehículo se tienen que cumplir todas las condiciones impuestas por las distintas formas.
*/

import Dominio.Tarea
import java.time.LocalDate
import java.time.Period

open class Usuario (
    var nombre: String = "",//0
    var username: String = "",//0
    var apellido: String = "",//0
    var paisDeResidencia: String = "",//0//TODO: cambiar strings por objetos (aumenar escalabilidad)
    var fechaDeAlta: LocalDate = LocalDate.now(),//0
    val diasParaViajar: Int = 0,//1
    val amigos: MutableList<Usuario> = mutableListOf(),//1
    var destinosDeseados: MutableList<Destino> = mutableListOf(),//1
    val destinosVisitados: MutableList<Destino> = mutableListOf(),//1 //TODO: refactorizar, se deberia obtener de los itinerarios realizados.
    var criterioItinerario: CriterioItinerario = Relajado(),//1
    var criterioVehiculo: CriterioVehiculo = CualquierVehiculo(),//2
    var correoElectronico:String = "",//3
    var tareas: MutableList<Tarea> = mutableListOf(),
    id: Int = 0//2
) : Entidad(id) {//2

    fun antiguedad(): Int {
        return Period.between(fechaDeAlta, LocalDate.now()).years
    }//0

    fun esResidente(pais: String): Boolean {
        return pais.lowercase() == this.paisDeResidencia.lowercase()
    }//0

    fun descuentoAntiguedad(): Double {
        return if (this.antiguedad() < 15) { this.antiguedad() / 100.0 } else { 0.15 }
    }//0


    fun conoceDestino(destino: Destino): Boolean {
        return destinosDeseados.contains(destino) or destinosVisitados.contains(destino)
    }//1

    fun puedeRealizarItinerario(itinerario: Itinerario): Boolean {
        return this.alcanzanLosDiasPara(itinerario) && criterioItinerario.puedeRealizarItinerario(this, itinerario)

    }//1

    fun alcanzanLosDiasPara(itinerario: Itinerario): Boolean {
        return this.diasParaViajar >= itinerario.cantidadDias()
    }//1


    fun cambiarCriterio(nueva: CriterioItinerario) {
        this.criterioItinerario = nueva
    }//1

    fun agregarAmigo(nuevo_amigo: Usuario) {
        this.amigos.add(nuevo_amigo)
    }//1

    fun esAmigo(usuario: Usuario): Boolean {
        return usuario in this.amigos
    }//1

    fun precioMayor(): Double {
        return destinosDeseados.maxOf { it.costoTotal(this) }
    }//1

    fun esValido(): Boolean {
        return (this.nombreNoNull() && this.apellidoNoNull() && this.usernameNoNull() &&
                this.paisDeResidenciaNoNull() && this.fechaAltaAnteriorAfechaActual() &&
                this.diasParaViajarMayorACero() && this.destinosDeseadosNoNull())
    }//1

    fun nombreNoNull(): Boolean {
        return this.nombre != ""
    }//1

    fun apellidoNoNull(): Boolean {
        return this.apellido != ""
    }//1

    fun usernameNoNull(): Boolean {
        return this.username != ""
    }//1

    fun paisDeResidenciaNoNull(): Boolean {
        return this.paisDeResidencia != ""
    }//1

    fun fechaAltaAnteriorAfechaActual(): Boolean {
        return this.fechaDeAlta < LocalDate.now()
    }//1

    fun diasParaViajarMayorACero(): Boolean {
        return this.diasParaViajar > 0
    }//1

    fun destinosDeseadosNoNull(): Boolean {
        return this.destinosDeseados.size >= 1
    }//1

    override fun busqueda(coincidencia : String) : Boolean =
        (this.nombre.contains(coincidencia) || this.apellido.contains(coincidencia) || this.username == coincidencia)//2

    fun leGusta(vehiculo: Vehiculo):Boolean{
        return criterioVehiculo.leGusta(vehiculo)
    }//2

    fun agregarTarea(tarea: Tarea){
        this.tareas.add(tarea)
    }

    fun ejecutarTareas(){
        this.tareas.forEach { it.ejecutar(this) }
    }

}
/********************** << CRITERIOS ITIENERARIOS >> **************************/
 interface CriterioItinerario {

     fun puedeRealizarItinerario(usuario: Usuario, itinerario: Itinerario): Boolean
}//1

class Relajado() : CriterioItinerario {
    override fun puedeRealizarItinerario(usuario: Usuario, itinerario: Itinerario): Boolean {
        return true
    }
}//1

class Precavido() : CriterioItinerario {
    override fun puedeRealizarItinerario(usuario: Usuario, itinerario: Itinerario): Boolean {
        return usuario.conoceDestino(itinerario.destino) or usuario.amigos.any { it.conoceDestino(itinerario.destino) }
    }
}//1

class Localista() : CriterioItinerario{
    override fun puedeRealizarItinerario(usuario: Usuario, itinerario: Itinerario): Boolean {
        return itinerario.destino.esLocal()
    }
}//1

class Soniador() : CriterioItinerario {
    override fun puedeRealizarItinerario(usuario: Usuario, itinerario: Itinerario): Boolean {
        return usuario.destinosDeseados.contains(itinerario.destino) || (itinerario.destino.costoTotal(usuario) >= usuario.precioMayor())
    }
}//1

class Activo() : CriterioItinerario {
    override fun puedeRealizarItinerario(usuario: Usuario, itinerario: Itinerario): Boolean {
        return !itinerario.dias.any { it.sinActividades()}
    }
}//1

class Exigente(var tipoDificultad: Dificultad,var porcentajeDificultad: Int) : CriterioItinerario {

    override fun puedeRealizarItinerario(usuario: Usuario, itinerario: Itinerario): Boolean {
        return itinerario.porcentajeDificultad(tipoDificultad) >= porcentajeDificultad
    }
}//1

/********************** << CRITERIO VEHICULOS >> ***********************************/

interface CriterioVehiculo {
    fun leGusta(vehiculo: Vehiculo): Boolean
}//2

class CualquierVehiculo() : CriterioVehiculo {
    override fun leGusta(vehiculo: Vehiculo): Boolean {
        return true
    }

}//2

class Neofilo() : CriterioVehiculo {
    override fun leGusta(vehiculo: Vehiculo): Boolean {
        return vehiculo.antiguedad() <= 2
    }

}//2

class Supersticioso() : CriterioVehiculo {
    override fun leGusta(vehiculo: Vehiculo): Boolean {
        return vehiculo.fechaDeFabricacion.year % 2 == 0
    }
}//2

class Caprichoso() : CriterioVehiculo {

    override fun leGusta(vehiculo: Vehiculo): Boolean {
        return vehiculo.marca.name.lowercase().first() == vehiculo.modelo.name.lowercase().first()
    }

}//2

class Selectivo(val marca: Marcas) : CriterioVehiculo {
    override fun leGusta(vehiculo: Vehiculo): Boolean {
        return vehiculo.marca == marca
    }
}//2

class SinLimite() : CriterioVehiculo {
    override fun leGusta(vehiculo: Vehiculo): Boolean {
        return vehiculo.kilometrajeLibre
    }
}//2

class Combinado(
    var criterios: MutableList<CriterioVehiculo> = mutableListOf()
) : CriterioVehiculo {

    override fun leGusta(vehiculo: Vehiculo): Boolean {
        return  this.criterios.isNotEmpty() && this.criterios.all { it.leGusta(vehiculo) }
    }
}//2
