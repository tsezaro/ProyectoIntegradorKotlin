/*--------------------------parte 2
Vehículos

Sabemos que cuentan con:

Marca: es el nombre de la empresa que lo fabrica.
Modelo: es el nombre del vehículo.
Año de Fabricación: es el año en que se fabricó esa unidad específica.
Costo Diario: cada vehículo tiene un costo diario, a la hora de ser alquilado.
Costo Base: corresponde al costo diario por la cantidad de días de alquiler.
Tiene convenio: esto sucede si el vehículo es de alguna Marca con la que tengamos un convenio. A la fecha, solo tenemos convenio con la Marca “Honda”.


Antigüedad: es la cantidad de años desde su fabricación (Ej. año de fabricación 2021->antigüedad : 1 año.)
Kilometraje Libre: cada vehículo puede contar con kilometraje libre o no.

Contamos con las siguientes unidades: Motos - Autos - Camionetas.
Para determinar el costo de alquiler tenemos:
Motos: al costo base se le agrega…
Si es de una cilindrada mayor a 250cc, $500 por día.
Autos: al costo base se le agrega…
Si es hatchback (sin baúl) un 10% del costo base
Si NO es hatchback un 25% del costo base
Camionetas:  al costo base se le agrega…
Si el alquiler es menor o igual a una semana —➤ $10.000
Si el alquiler es mayor a una semana —➤ $10.000 + $1.000 más por cada día de exceso.
Si la camioneta es 4x4 se le aplica un recargo del 50% a los cálculos antes mencionados.

A los vehículos con convenio se le descuenta un 10% del costo calculado al momento.
Ej: Moto “Honda” de 500cc, con costo diario de $1.000, alquilada por 10 días.
$1.000 x 10 días + $500 x 10 días = $15.000 —➤ por tener convenio se descuenta el 10% el costo total es $13.500*/

import java.time.LocalDate
import java.time.Period

abstract class Vehiculo(
    var marca: Marcas = Marcas.GENERICO,
    var modelo: Modelos= Modelos.GENERICO ,
    var fechaDeFabricacion: LocalDate,
    var costoDiario: Double,
    var kilometrajeLibre: Boolean,
    id: Int = 0
) : Entidad(id) {//2

    fun aplicaDescuentoConvenio(dias: Int):Double{
       return if(this.tieneConvenio()){this.costoBase(dias)*0.1}else{0.0}
    }

    fun antiguedad(): Int {
        return Period.between(fechaDeFabricacion, LocalDate.now()).years
    }

     fun costoBase(dias:Int): Double {
        return costoDiario * dias
    }

    abstract fun costoParticular(dias:Int):Double

    fun costoAlquiler(dias:Int):Double{
        return this.costoBase(dias)+this.costoParticular(dias)-this.aplicaDescuentoConvenio(dias)
    }

    fun tieneConvenio():Boolean{
        return this.marca == Marcas.HONDA
    }

    override fun busqueda(coincidencia : String) : Boolean = this.marca.name == coincidencia || this.modelo.name.startsWith(coincidencia)
}

enum class Marcas{
    GENERICO,HONDA,FERRARI,PEUGEOT,GILERA,PORCHE
}

enum class Modelos{
    GENERICO,ALPISO,CARRERA,F150,FELINE208,F75,CITY
}

open class Moto(
    marca: Marcas,
    modelo: Modelos,
    fechaDeFabricacion: LocalDate,
    costoDiario: Double,
    kilometrajeLibre: Boolean,
    var cilindrada: Int
) :
    Vehiculo(marca, modelo, fechaDeFabricacion, costoDiario, kilometrajeLibre){

    override fun costoParticular(dias:Int):Double {
        return if (cilindrada>250) {500.0 * dias } else {0.0}
    }
}

class Auto(
    marca: Marcas,
    modelo: Modelos,
    fechaDeFabricacion: LocalDate,
    costoDiario: Double,
    kilometrajeLibre: Boolean,
    var hatchback: Boolean=false

) :
    Vehiculo(marca, modelo, fechaDeFabricacion, costoDiario, kilometrajeLibre){
    override  fun costoParticular(dias:Int): Double {
            return super.costoBase(dias)*if(hatchback) 0.1 else 0.25
    }

}

open class Camioneta(
    marca: Marcas,
    modelo: Modelos,
    fechaDeFabricacion: LocalDate,
    costoDiario: Double,
    kilometrajeLibre: Boolean,
    var es4x4: Boolean

) :
    Vehiculo(marca, modelo, fechaDeFabricacion, costoDiario, kilometrajeLibre){

    override  fun costoParticular(dias:Int): Double {
        val recargo4x4: Double = if(es4x4){1.5}else{1.0}
        val recargoPorDias: Double= if(dias>7){1000.0*diasDeExceso(dias)} else {0.0}
        val totalBruto : Double = (10000.0 + recargoPorDias) * recargo4x4

        return totalBruto
    }

    fun diasDeExceso(dias:Int):Int{
        return dias - 7
    }
}
