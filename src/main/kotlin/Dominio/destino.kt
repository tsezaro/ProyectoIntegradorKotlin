import com.fasterxml.jackson.annotation.JsonProperty

/*------------------------parte 0
De los destinos sabemos:
País
Ciudad.
Costo Base: cada destino tiene un costo base propio.
Queremos poder determinar si un destino es local, sabiendo que los destinos consideran a la Argentina como país de localía.
Costo,  que tiene un usuario por visitarlo, se calcula según:
Si el destino no es local se le debe sumar un 20% al costo base.
Si el Usuario que lo visita es del mismo país que el destino, se le aplica un descuento del 1% del costo base por cada año de antigüedad, contemplando un máximo de 15 años
Ejemplo: Destino : Italia , costo base: $ 30.000
Usuario residente en Italia, usando aplicación 16 años
Costo = 30.000 + 6.000 - 4.500 = $ 31.500*/

/*------------------------parte 1
Para que un destino sea válido:
Debe tener tanto país como ciudad, no deben ser nulos o vacíos.
El costo base debe ser mayor a cero.*/


class Destino(
    var pais: String = "", //0 // TODO: convertir a objecto para aumentar escalabilidad.
    var ciudad: String = "", //0 // TODO: convertir a objecto para aumentar escalabilidad.
    @JsonProperty("costo") var costoBase: Double = 0.0,//0
    private val localidad: String = "argentina", //0 TODO: convertir a objecto para aumentar escalabilidad.
    id: Int = 0//2
) : Entidad(id) {//2

    fun esLocal(): Boolean {
        return pais.lowercase() == this.localidad
    }//0

    fun costoTotal(persona: Usuario): Double {
        val descuentoLocal: Double = if (esLocal()) 1.0 else 1.2
        val costoParcial: Double = costoBase * descuentoLocal
        return if (persona.esResidente(this.pais))costoParcial-(costoParcial*persona.descuentoAntiguedad()) else costoParcial
    }//0

    fun esValido(): Boolean {
        return this.pais.isNotBlank() && this.ciudad.isNotBlank() && (this.costoBase > 0)
    }//1

    override fun busqueda(coincidencia : String) : Boolean = this.pais.contains(coincidencia) || this.ciudad.contains(coincidencia)//2

}


