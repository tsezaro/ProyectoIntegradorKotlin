import java.time.LocalDateTime

open class Dia(
    var actividades: MutableList<Actividad> = mutableListOf()
) {

    fun sumaDuracionActividades(): Long {
        return actividades.sumOf { it.duracion() }
    }//1

    fun agregarActividad(actividad: Actividad) {
        this.actividades.add(actividad)
    }//1

    fun quitarActividad(actividad: Actividad) {
        this.actividades.remove(actividad)
    }//1

    fun horarioLibre(lista: List<Actividad> , inicio: LocalDateTime, fin: LocalDateTime): Boolean {
        return lista.all { this.antesDelHorarioLibre(it, inicio) || this.despuesDelHorarioLibre(it, fin) }
    }//1

    fun antesDelHorarioLibre(actividad: Actividad, horario: LocalDateTime): Boolean {
        return (actividad.inicio <= horario) && (actividad.fin <= horario)
    }//1

    fun despuesDelHorarioLibre(actividad: Actividad, horario: LocalDateTime): Boolean {
        return (actividad.inicio >= horario) && (actividad.fin >= horario)
    }//1

    fun actividadesSeSolapan(): Boolean {
        return actividades.any { actividad ->
            !this.horarioLibre(this.actividadesSacandoA(actividad), actividad.inicio, actividad.fin) }
    }//1

    fun actividadesSacandoA(actividad: Actividad):List<Actividad>{
        return  this.actividades.filter {it != actividad}
    }//1

    fun sinActividades(): Boolean {
        return this.actividades.size == 0
    }//1

}