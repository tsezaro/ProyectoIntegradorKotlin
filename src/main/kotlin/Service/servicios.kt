/* ------------------------ Servicios parte2
Nuestro repositorio de Destinos debe actualizarse a demanda obteniendo datos desde un servicio externo.
 Para ello debemos implementar

Para actualizar nuestro repositorio de Destinos utilizaremos el método getDestinos() de ServiceDestinos.
El mismo se conectará con un servicio REST y retornará un JSON con el siguiente formato:

[
{
    "id": 9,
    "pais": "Argentina",
    "ciudad": "Mar del Plata",
    "costo": 10000
},
{
    "id": 12,
    "pais": "Brasil",
    "ciudad": "Rio de Janeiro",
    "costo": 20000
},
{
    "pais" : "Indonesia",
    "ciudad": "Bali",
    "costo": 30000
},
(...)
]
*/

package Service

import Destino
import Repositorio
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef

class AdapterDestinos(
    val servicio: ServicioDestinos,
    val objectMapper: ObjectMapper,
    val repositorio: Repositorio<Destino> = Repositorio<Destino>()

) {//2
    fun parsearDestinos(): List<Destino> {
        val json = servicio.getDestinos()
        return objectMapper.readValue(json, jacksonTypeRef<List<Destino>>())
    }

    fun updateRepositorio() {
        repositorio.update(this.parsearDestinos())
    }
}

interface ServicioDestinos {
     fun getDestinos(): String
}

fun getObjectMapper():ObjectMapper{
    val objectMapper= jacksonObjectMapper()
    objectMapper.registerModule(JavaTimeModule())
    return objectMapper
}


