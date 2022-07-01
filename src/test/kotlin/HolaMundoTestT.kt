/* HolaMundoTestT
 *
 * NOTE: ESTO ESTA DEPRECADOOOOOOOOOOOO
 *       USAR OTROS ARCHIVOS DE TESTS
 *       PARA TESTEAR COSAS ESPECIFICAS
 */

import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import java.time.LocalDate
import java.time.LocalDateTime

class HolaMundoTest : DescribeSpec({
    isolationMode = IsolationMode.InstancePerTest

    /*  DESTINOS */
    val viaje = Destino(
        pais = "Italia",
        ciudad = "napole",
        costoBase = 12.000)

    /*  USUARIOS */
    // TODO: reutilizar y reducir usuarios.
    val usuario = Usuario(
        nombre = "Ruperto",
        apellido = "Rupe",
        paisDeResidencia = "Bolivia",
        username = "Funez",
        fechaDeAlta = LocalDate.now().minusYears(20))
    val persona2 = Usuario(
        nombre = "Luciano",
        username = "LuVega",
        apellido = "Vega",
        paisDeResidencia = "Argentina",
        fechaDeAlta = LocalDate.now().minusYears(24))
    val cr7 = Usuario(
        nombre ="cristiano",
        apellido ="ronaldo",
        username = "elbicho",
        paisDeResidencia = "Portugal",
        fechaDeAlta = LocalDate.now().minusYears(23),
        destinosDeseados = mutableListOf(viaje))
    val messi = Usuario(
        nombre ="leo",
        apellido ="messi",
        username = "lapulga",
        paisDeResidencia = "Espania",
        fechaDeAlta = LocalDate.now().minusYears(6),
        destinosVisitados = mutableListOf(viaje))
    val tomiiiii = Usuario(
        nombre = "tomas",
        apellido = "Sezaro",
        username = "tomiii",
        paisDeResidencia = "argentina",
        fechaDeAlta = LocalDate.now().minusYears(29))
    val daiana = Usuario(
        nombre = "daiana",
        apellido = "secundario",
        username = "daiana",
        paisDeResidencia = "Italia",
        fechaDeAlta = LocalDate.now().minusYears(10))
    val fedeeeeeee = Usuario(
        nombre = "federico",
        apellido = "marquez",
        username = "federico",
        paisDeResidencia = "uruaguay",
        fechaDeAlta = LocalDate.now().minusYears(9))
    val pepe = Usuario(
        nombre = "fernando",
        apellido = "pancho",
        paisDeResidencia = "Brasil",
        username = "perez",
        destinosDeseados = mutableListOf(viaje))
    val hector = Usuario(
        nombre = "fernando",
        apellido = "pancho",
        paisDeResidencia = "Brasil",
        username = "perez",
        destinosDeseados = mutableListOf(viaje))

    /* ACTIVIDADES */
    val actividad1= Actividad(
        costo = 200.00,
        descripcion = "cine",
        inicio = LocalDateTime.of(2022,5,22,15,3),
        fin = LocalDateTime.of(2022,5,22,15,4),
        dificultad = Dificultad.BAJA)


    /* TESTS */
    describe ("primera parte tp 0") {
        describe("cliente sin descuento") {

            it("los clientes solo tienen descuento si viajan a su pais de recidencia") {
                pepe.esResidente("CatMAndu") shouldBe false
            }

            it("estamos probando que los strings ingresados esten en minuscula") {
                hector.esResidente("bRaSil") shouldBe true
            }
        }

        describe("Usuario con antiguedad > 15") {
            val destino = Destino(pais = "Bolivia", ciudad = "La Paz", 30000.0)

            it("Es residente") {
                usuario.esResidente("Bolivia") shouldBe true
            }
            it("Descuento por antiguedad de 20 tiene que ser 15%(*0.15)") {
                usuario.descuentoAntiguedad() shouldBe 0.15//*100
            }
            it("Costo Total") {
                destino.costoTotal(usuario) shouldBe 30600
            }
        }

        describe("Viaje") {

            it("Calcular antiguedad") {
                tomiiiii.antiguedad() shouldBe 29
            }

            it("Viaje a Destino Local de un Argentino") {
                val bsas = Destino(pais = "Argentina", ciudad = "Buenos Aires", costoBase = 8000.0)
                bsas.esLocal() shouldBe true
                persona2.esResidente("Argentina") shouldBe true
                bsas.costoTotal(persona2) shouldBe 6800.0
            }
        }


        describe("viaje no local") {

            it("calcular viaje sin ser local") {
                viaje.esLocal() shouldBe false
                daiana.esResidente("Italia") shouldBe true
            }
        }


        describe("Test descuento por antiguedad") {

            it("mucha antiguedad") {
                cr7.descuentoAntiguedad() shouldBe 0.15
            }
            it("poca antiguedad") {
                messi.descuentoAntiguedad() shouldBe 0.06
            }
        }

        describe ("Test amigos") {

            it ("obtenerAmigos") {
                tomiiiii.agregarAmigo(fedeeeeeee)
                tomiiiii.agregarAmigo(daiana)
                tomiiiii.amigos shouldBe mutableListOf(fedeeeeeee, daiana)
            }
            it ("esAmigo") {
                tomiiiii.agregarAmigo(fedeeeeeee)
                tomiiiii.esAmigo(fedeeeeeee) shouldBe true
                tomiiiii.esAmigo(daiana) shouldBe false
            }
        }
    }

    describe("segunda parte tp1"){

//        val itinerario1= Itinerario(destino = viaje  , cantidadDias = 10 , Creador = pepe)
//        itinerario1.agregarActividad(actividad1,pepe)
//        itinerario1.agregarActividad(actividad2,pepe)
//        itinerario1.agregarActividad(actividad3,pepe)
//
//        it("agregar actividad"){
//            itinerario1.actividades  shouldBe mutableListOf(actividad1,actividad2,actividad3)
//        }

        describe("testeando funciones de inicio y fin de actividades "){


            it("devuelve la diferencia en minutos"){
                actividad1.duracion()  shouldBe 1
            }

            it("devuelve el numero de dia de la actividad"){
                actividad1.obtenerDia()  shouldBe 22
            }
        }

        describe("para que un destino sea valido"){

            it("debe tener tanto pais como ciudad y no deven ser nulos a vacios"){
                viaje.pais.isNotEmpty()  shouldBe  true
            }

            it("el costo base debe ser mayor a 0"){
                (viaje.costoBase > 0) shouldBe  true
            }
        }

        describe("las actividades son validas si"){

            it("descripcion no es nula o vacia"){
                actividad1.descripcion.isNotEmpty()  shouldBe  true
            }

            it("si el inicio de la actividad es menor a el final de la misma"){
                (actividad1.inicio < actividad1.fin)  shouldBe  true
            }

            it("el costo debe ser mayor o igual a 0"){
                (actividad1.costo >= 0) shouldBe true
            }
        }

//        describe("Los itinerarios son validos si"){
//
//            it("tiene creador y destino no nulos"){
//                itinerario1.destino.shouldNotBeNull()
//                itinerario1.Creador.shouldNotBeNull()
//            }
//
//            it("deben tener al menos 1 dia con actividades"){
//                (itinerario1.actividades.size > 1 ) shouldBe true
//            }
//        }
    }
})


