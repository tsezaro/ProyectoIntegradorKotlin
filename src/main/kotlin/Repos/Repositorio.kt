/*------------------ Repositorios parte 2

Para cada una de las entidades Destino, Usuario,  Itinerario, Vehículo, se pide crear un objeto Repositorio que tenga una colección de objetos en memoria, y que implemente el siguiente comportamiento:

void create(T object): Agrega un nuevo objeto a la colección, y le asigna un identificador único (id). El identificador puede ser autoincremental para evitar que se repita.

void delete(T object): Elimina el objeto de la colección.

void update(T object): Modifica el objeto dentro de la colección. De no existir el objeto buscado, es decir, un objeto con ese id, se debe lanzar una excepción.

T getById(Int id): Retorna el objeto cuyo id sea el recibido como parámetro.

List<T> search(String value): Devuelve los objetos que coincidan con la búsqueda de acuerdo a los siguientes criterios:

Destino: El valor de búsqueda debe coincidir parcialmente con el nombre del país, o con el nombre de la ciudad.

Usuario: El valor de búsqueda debe coincidir parcialmente con su nombre o apellido, o exáctamente con su username.

Itinerario: El valor de búsqueda debe coincidir parcialmente con el país o ciudad del destino que corresponda o con alguna de las actividades.

Vehículo: El valor de búsqueda debe coincidir exactamente con la marca o con el comienzo del modelo.*/

abstract class Entidad(open var id: Int = 0){//2

    abstract fun busqueda(coincidencia: String):Boolean
}

open class Repositorio<T : Entidad>  {//2
    var memoria: MutableList<T> = mutableListOf()
    var contador: Int = 1

    fun create(objeto: T){
        objeto.id = contador
        memoria.add(objeto)
        this.aumentarContador()
    }

    fun aumentarContador() {
        this.contador += 1
    }

    fun delete(objeto: T) {
        // TODO: checar: deberia borrarse el objeto directamente o borrarse por ID??? (ver "fun delete(id: Int)" )
        this.memoria.remove(objeto)
    }

    fun delete(id: Int) {
        this.delete(this.getById(id))
    }

    fun getById(id: Int) : T {
        return memoria.first { it.id== id }
    }

    fun update(objeto: T){
        val id: Int = objeto.id

        if(objeto.id==0){
            this.create(objeto)
            return
        }

        if (!this.memoria.map {it.id}.contains(id)) throw Exception("No hay objeto con este ID")

        this.delete(id)
        this.memoria.add(objeto)
    }

    fun update(lista: List<T>) {
        lista.forEach { this.update(it) }
    }

    fun search(coincidencia : String) : List <T> = this.memoria.filter{ it.busqueda(coincidencia) }

}


