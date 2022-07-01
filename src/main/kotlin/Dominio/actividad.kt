/*-------------------- parte 1
Actividades
Cuentan con :
Costo: un valor en pesos.
Descripción: un detalle resumido de la actividad a realizar.
Inicio: es el horario del día, para comenzar la actividad.
Fin: es el horario del día, para finalizar la actividad.
Duración: se desprende de los minutos transcurridos entre el inicio y fin de la actividad
Dificultad: existen 3 tipos de dificultades
BAJA MEDIA ALTA */

/*-------------------- parte 2
Las actividades son válidas si:

La descripción no es nula o vacía.
El horario de inicio < horario fin.
Deben tener una Dificultad establecida.
El costo debe ser mayor o igual a cero.*/



import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

class Actividad(
    val costo: Double = 0.0, //1
    val descripcion: String = "", //1
    val inicio: LocalDateTime = LocalDateTime.now(), //1
    val fin: LocalDateTime = LocalDateTime.now(), //1
    var dificultad: Dificultad = Dificultad.NULA //1

) {

    fun duracion(): Long {
        return ChronoUnit.MINUTES.between(this.inicio, this.fin)
    }//1

    fun obtenerDia(): Int {
        return this.inicio.dayOfMonth
    }//1

    fun esValida(): Boolean {
        return this.validacionHorario() && this.descripcionNoNull() && this.dificultadNoNula() && this.costoNoNull()
    }//1

    fun validacionHorario(): Boolean {
        return inicio.isBefore(fin)
    }//1

    fun descripcionNoNull(): Boolean {
        return this.descripcion.isNotEmpty()
    }//1

    fun dificultadNoNula(): Boolean{
        return this.dificultad != Dificultad.NULA
    }//1

    fun costoNoNull(): Boolean{
        return this.costo >= 0
    }//1

}

enum class Dificultad{
    NULA, BAJA, MEDIA, ALTA
}//1
