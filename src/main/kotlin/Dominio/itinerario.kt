/*------------------------------ent rega 1
Itinerarios
Los usuarios del sistema pueden crear itinerarios para realizar actividades en un Destino determinado.
Los itinerarios, sólo pueden ser editados por el usuario creador, o por un amigo, si es que este último conoce el
destino perteneciente al itinerario. Necesitamos poder determinar la cantidad de días, actividades, duración promedio
por día, dificultad y costo del mismo.La dificultad de un itinerario está dada por las dificultades de las actividades
diarias que lo componen, es decir que si prevalecen más actividades de un tipo de dificultad, esta debería
ser la del itinerario, llegado el caso que las cantidades sean iguales, se establece la de mayor dificultad.

Los itinerario son válidos si:
Tienen creador y destino no nulos.
Deben tener al menos 1 día con actividades.
Las actividades del día no deben solaparse en horarios.
Las puntuaciones deben ser del 1 al 10 y no pueden recibir más de una por usuario.


*/


open class Itinerario(
    var destino: Destino = Destino(),//1
    var creador: Usuario = Usuario(),
    var tablaUsuarios: MutableList<UsuarioItem> = mutableListOf(),
    var dias: MutableList<Dia> = mutableListOf(),//1
    id: Int = 0//2
 ) : Entidad(id) {//2

    fun puedeEditar(persona: Usuario): Boolean {
        return ((persona == this.creador) || (this.creador.esAmigo(persona) && persona.conoceDestino(this.destino)))
    }//1

    fun cantidadDias(): Int {
        return this.dias.size
    }//1

    fun obtenerActividades(): List<Actividad> {
        return dias.flatMap { it.actividades }
    }//1

    fun dificultad(): Dificultad {
        if (dias.isEmpty()){
            return Dificultad.NULA
        }
        return listOf(Dificultad.ALTA,Dificultad.MEDIA, Dificultad.BAJA).maxByOrNull { this.contarDificultades(it) }!!
    }//1

    fun contarDificultades(dificultad: Dificultad): Int {
        return this.obtenerActividades().count{it.dificultad == dificultad}
    }//1

    fun costo(): Double {
        return obtenerActividades().sumOf { it.costo }
    }//1

    fun duracionPromedioPorDia(): Long {
        return dias.sumOf { it.sumaDuracionActividades() } / this.cantidadDias()
    }//1

    fun cantidadActividades(): Int {
        return this.obtenerActividades().size
    }//1
    fun porcentajeDificultad(dificultad: Dificultad): Int {
        return if (this.dias.size >= 1){
            ((this.contarDificultades(dificultad) * 100) / this.cantidadActividades())
        } else 0
    }//1

     fun puntuar(usuario: Usuario, puntaje: Int) {
         this.tablaUsuarios.first { it.usuario == usuario }.puntuacion = puntaje
     }//1

     fun yaPuntuo(usuario: Usuario): Boolean {
         return this.tablaUsuarios.any{ it.tienePuntuacionValida() && it.usuario == usuario }
     }//1

     fun noPuntuo(usuario: Usuario): Boolean {
         return !this.yaPuntuo(usuario)
     }//1

     fun obtenerPuntuadores(): List<Usuario> {
         return this.tablaUsuarios.map { it.usuario }
     }//1

     fun cambiarDestino(destino: Destino) {
             this.destino = destino
     }//1

    fun agregarDia(nuevo_dia: Dia) {
        this.dias.add(nuevo_dia)
    }//1

     //TODO: AGREGAR CHICHE ( enviar mensaje de error cuando es falso)
    fun quitarDia(dia: Dia) {
            this.dias.remove(dia)
    }//1

    fun actividadesSeSolapan(): Boolean {
        return dias.any {it.actividadesSeSolapan()}
    }//1

     fun esValido(): Boolean {
         return ((this.creador != null) &&
                 (this.destino != null) &&
                 !this.actividadesSeSolapan() &&
                 this.tablaUsuarios.all{ it.tienePuntuacionValida() } &&
                 !this.existenDuplicados() &&
                 this.noPuntuo(this.creador))
     }//1

     fun existenDuplicados(): Boolean {
         val usuarios = this.obtenerPuntuadores()
         return usuarios.distinct().size != usuarios.size
     }//1

     override fun busqueda(coincidencia : String) : Boolean =
         this.destino.pais.contains(coincidencia) || this.destino.ciudad.contains(coincidencia) ||
                 this.obtenerActividades().any{it.toString() == coincidencia}//2

    fun usuariosSinPuntuar():List<Usuario> {
        return this.tablaUsuarios.filter { !it.tienePuntuacionValida() }.map { it.usuario }
    }

    fun usuarioNoPuntuo(usuario: Usuario):Boolean{
        return this.usuariosSinPuntuar().contains(usuario)
    }

    fun obtenerUsuarios(): List<Usuario> {
        return this.tablaUsuarios.map { it.usuario }
    }

    fun agregarUsuario(usuario: Usuario) {
        if (!this.obtenerUsuarios().contains(usuario)) {
            this.tablaUsuarios.add(UsuarioItem(usuario, -1))
        }
    }
}


class UsuarioItem(var usuario: Usuario,
                  var puntuacion: Int = -1) // -1 por defecto para que reviente tod0)
{
    fun tienePuntuacionValida(): Boolean {
        return (this.puntuacion >= 1) && (this.puntuacion <= 10)
    }
}
