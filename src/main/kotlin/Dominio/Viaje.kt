import Dominio.Tarea

/***************************************************************************************
                                 OBJETO OBSERVADO
 **************************************************************************************/

class Viaje(
/*
    Además de permitir, a los usuarios, compartir sus itinerarios de viajes,
    queremos que puedan estimar y comparar distintas propuestas, a la hora de elegir dónde viajar.

    Luego de configurar un viaje, y ver si el costo del mismo es un valor acorde a su presupuesto,
    el usuario confirma realizar el viaje. Esto debe actualizar los destinos visitados por él.

*/
    var itinerario: Itinerario,
    var vehiculo: Vehiculo,
    var viajero: Usuario,
    var diasVehiculo: Int,

)  {
    
    val postObservers = mutableListOf<ViajeObserver>()

    fun destino() : Destino = itinerario.destino

    fun costo(): Double {
        return itinerario.destino.costoTotal(viajero) + itinerario.costo() + vehiculo.costoAlquiler(this.diasVehiculo)
    }

    fun viajar() {
        viajero.destinosVisitados.add(this.destino())
        this.itinerario.agregarUsuario(this.viajero)
        postObservers.forEach { it.postViaje(this) }
    }

    fun agregaPostObserver(postObserver: ViajeObserver){
        postObservers.add(postObserver)
    }
}
