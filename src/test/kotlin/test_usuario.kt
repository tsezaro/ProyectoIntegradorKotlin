/* Test Usuario
 *
 * NOTE: Aca solo se testean funcionalidades especifica de los usuarios.
 */


import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import java.time.LocalDate
import io.kotest.matchers.types.shouldBeInstanceOf
import io.kotest.matchers.types.shouldNotBeInstanceOf
import java.time.LocalDateTime


class TestUsuario : DescribeSpec({
    isolationMode = IsolationMode.InstancePerTest
    describe("TESTEANDO TODO LO DE LA CLASE USUARIO") {

        val viaje = Destino(
            pais = "Suiza",
            ciudad = "napole",
            costoBase = 12.000
        )
        val viaje1 = Destino(
            pais = "Italia",
            ciudad = "napole",
            costoBase = 12.000
        )

        /* USUARIOS */
        val bariloche = Destino(
            pais = "Argentina",
            ciudad = "Bariloche",
            costoBase = 80000.0
        )

        val glaciarPeritoMoreno = Destino(
            pais = "Argentina",
            ciudad = "Santa Cruz",
            costoBase = 100000.0
        )

        val termasCacheuta = Destino(
            pais = "Argentina",
            ciudad = "Mendoza",
            costoBase = 45000.0
        )

        val cataratasIguazu = Destino(
            pais = "Argentina",
            ciudad = "Misiones",
            costoBase = 60000.0
        )

        val laCumbrecita = Destino(
            pais = "Argentina",
            ciudad = "Cordoba",
            costoBase = 30000.0
        )

        val cr7 = Usuario(
            nombre = "cristiano",
            username = "carlitos",
            apellido = "ronaldo",
            paisDeResidencia = "peru",//TODO: cambiar strings por objetos (aumenar escalabilidad)
            fechaDeAlta = LocalDate.of(2000, 9, 25),
            destinosDeseados = mutableListOf(viaje),
            diasParaViajar = 15,
            destinosVisitados = mutableListOf(
                bariloche,
                glaciarPeritoMoreno,
                laCumbrecita,
                cataratasIguazu,
                termasCacheuta
            ),
            criterioItinerario = Exigente(Dificultad.MEDIA, 20)
        )

        val chaquenioPalavecino = Usuario(
            nombre = "Chaqueño",
            username = "elchaqueño",
            apellido = "Palavecino",
            paisDeResidencia = "Argentina",
            fechaDeAlta = LocalDate.now().minusYears(35),
            destinosDeseados = mutableListOf(bariloche, glaciarPeritoMoreno),
            destinosVisitados = mutableListOf(laCumbrecita),
            diasParaViajar = 52,
            criterioItinerario = Localista()
        )

        val luquitaModric = Usuario(
            nombre = "Luka",
            username = "luquitas85",
            apellido = "Modric",
            paisDeResidencia = "Croacia",
            fechaDeAlta = LocalDate.now().minusYears(18),
            destinosDeseados = mutableListOf(laCumbrecita),
            destinosVisitados = mutableListOf(cataratasIguazu),
            diasParaViajar = 15,
            criterioItinerario = Precavido()
        )

        val friedrichGauss = Usuario(
            nombre = "Friedrich",
            username = "DisquisitionesArithmeticae",
            apellido = "Gauss",
            paisDeResidencia = "Alemania",
            fechaDeAlta = LocalDate.now().minusYears(150),
            diasParaViajar = 14,
            criterioItinerario = Activo()
        )

        val miaKhalifa = Usuario(
            nombre = "Sarah",
            username = "MiaKhalifa69",
            apellido = "Chamoun",
            paisDeResidencia = "Libano",
            fechaDeAlta = LocalDate.now().minusYears(9),
            destinosDeseados = mutableListOf(termasCacheuta),
            destinosVisitados = mutableListOf(),
            diasParaViajar = 210,
            criterioItinerario = Soniador()
        )

        val rogerFederer = Usuario(
            nombre = "Roger",
            username = "sumajestad",
            apellido = "Federer",
            paisDeResidencia = "Suiza",
            fechaDeAlta = LocalDate.now().minusYears(1),
            destinosDeseados = mutableListOf(termasCacheuta),
            destinosVisitados = mutableListOf(),
            diasParaViajar = 1,
            criterioItinerario = Soniador()
        )

        val actividad1 = Actividad(
            costo = 200.00,
            descripcion = "cine",
            inicio = LocalDateTime.of(2022, 5, 22, 15, 0),
            fin = LocalDateTime.of(2022, 5, 22, 16, 0),
            dificultad = Dificultad.BAJA
        )

        val actividad2 = Actividad(
            costo = 223.00,
            descripcion = "bowling",
            inicio = LocalDateTime.of(2022, 5, 22, 20, 0),
            fin = LocalDateTime.of(2022, 5, 22, 22, 0),
            dificultad = Dificultad.MEDIA
        )

        val actividad3 = Actividad(
            costo = 223.00,
            descripcion = "bowling",
            inicio = LocalDateTime.of(2022, 5, 22, 17, 0),
            fin = LocalDateTime.of(2022, 5, 22, 18, 0),
            dificultad = Dificultad.MEDIA
        )

        val dia1 = Dia(actividades = mutableListOf(actividad1, actividad2))
        val dia2 = Dia()
        val dia3 = Dia()
        val exigente1 = Exigente(Dificultad.MEDIA, 20)
        val activo1 = Activo()

        val itinerario1 = Itinerario(
            destino = viaje,
            creador = friedrichGauss,
            dias = mutableListOf(dia1, dia2, dia3)
        )

        val itinerarioTermasCacheuta = Itinerario(
            destino = termasCacheuta,
            creador = miaKhalifa,
            dias = mutableListOf(dia1, dia2, dia3)
        )


        val itinerarioBarilche = Itinerario(
            destino = bariloche,
            creador = luquitaModric,
            dias = mutableListOf(dia1, dia2, dia3)
        )


        val itinerarioGlaciarPeritoMoreno = Itinerario(
            destino = glaciarPeritoMoreno,
            creador = cr7
        )


        val itinerarioCataratasIguazu = Itinerario(
            destino = cataratasIguazu,
            creador = friedrichGauss
        )

        val itinerarioLaCumbrecita = Itinerario(
            destino = laCumbrecita,
            creador = chaquenioPalavecino
        )


        val moto1 = Moto(
            marca = Marcas.GILERA,
            modelo = Modelos.ALPISO,
            fechaDeFabricacion = LocalDate.of(2019, 10, 15),
            costoDiario = 10.0,
            kilometrajeLibre = true,
            cilindrada = 260,
        )

        val moto2 = Moto(
            marca = Marcas.GILERA,
            modelo = Modelos.ALPISO,
            fechaDeFabricacion = LocalDate.of(2019, 10, 15),
            costoDiario = 10.0,
            kilometrajeLibre = true,
            cilindrada = 100,

        )

        val auto1 = Auto(
            marca = Marcas.PORCHE,
            modelo = Modelos.CARRERA,
            fechaDeFabricacion = LocalDate.of(2019, 10, 15),
            costoDiario = 10.0,
            kilometrajeLibre = true,
            hatchback = true
        )

        val auto2 = Auto(
            marca = Marcas.FERRARI,
            modelo = Modelos.F150,
            fechaDeFabricacion = LocalDate.of(2022, 1, 15),
            costoDiario = 10.0,
            kilometrajeLibre = true,
            hatchback = false
        )

        val auto3 = Auto(
            marca = Marcas.PEUGEOT,
            modelo = Modelos.FELINE208,
            fechaDeFabricacion = LocalDate.of(2022, 1, 15),
            costoDiario = 10.0,
            kilometrajeLibre = true,
            hatchback = false
        )

        describe("Test personalidad") {
            it("cambiar de personalidad") {
                cr7.cambiarCriterio(Activo())
                cr7.criterioItinerario.shouldBeInstanceOf<Activo>()
                cr7.criterioItinerario.shouldNotBeInstanceOf<Soniador>()
                cr7.cambiarCriterio(Soniador())
                cr7.criterioItinerario.shouldBeInstanceOf<Soniador>()
                cr7.criterioItinerario.shouldNotBeInstanceOf<Activo>()
            }
        }
        describe("usuario valido") {
            it("todos los campos string no deben ser null o vacios") {
                cr7.nombreNoNull() shouldBe true
                cr7.apellidoNoNull() shouldBe true
                cr7.usernameNoNull() shouldBe true
                cr7.paisDeResidenciaNoNull() shouldBe true
            }
            it("la fecha del alta no puede ser posterior  a la del dia") {
                cr7.fechaAltaAnteriorAfechaActual() shouldBe true
            }

            it("dias para viajar mayores a 0") {
                cr7.diasParaViajarMayorACero() shouldBe true
            }
            it("usuario tiene por lo menos 1 destino deseado") {
                cr7.destinosDeseadosNoNull() shouldBe true
            }
            it("este usuario es valido") {
                cr7.esValido() shouldBe true
            }
        }


        describe("Test de Usuario") {

            it("Confirmar que el usuario creo un itinerario") {
                itinerarioBarilche.creador shouldBe luquitaModric
            }


            it("Constatando que un Usuario es Residente") {
                chaquenioPalavecino.esResidente("Argentina") shouldBe true
            }

            it("Constatando la antiguedad de un Usuario y el descuento de antiguedad") {
                chaquenioPalavecino.antiguedad() shouldBe 35
                chaquenioPalavecino.descuentoAntiguedad() shouldBe 0.15
            }

            it("Agregando amigo y consultando si es amigo ") {
                cr7.agregarAmigo(chaquenioPalavecino)
                cr7.esAmigo(chaquenioPalavecino) shouldBe true
                chaquenioPalavecino.esAmigo(cr7) shouldBe false
                chaquenioPalavecino.agregarAmigo(cr7)
                chaquenioPalavecino.esAmigo(cr7) shouldBe true
            }

            it("Obtener lista de amigos ") {

                cr7.agregarAmigo(chaquenioPalavecino)
                cr7.agregarAmigo(luquitaModric)
                cr7.agregarAmigo(friedrichGauss)
                cr7.agregarAmigo(miaKhalifa)
                cr7.amigos shouldBe mutableListOf(chaquenioPalavecino, luquitaModric, friedrichGauss, miaKhalifa)
            }

            it("Averiguar si usuario conoce destino") {
                luquitaModric.conoceDestino(cataratasIguazu) shouldBe true
                miaKhalifa.conoceDestino(cataratasIguazu) shouldBe false
            }

            it("Averiguar si alcanza los dias y si puede realizar el Itinerario") {
                miaKhalifa.alcanzanLosDiasPara(itinerarioTermasCacheuta) shouldBe true
                miaKhalifa.puedeRealizarItinerario(itinerarioTermasCacheuta) shouldBe true
                rogerFederer.alcanzanLosDiasPara(itinerarioTermasCacheuta) shouldBe false
                rogerFederer.puedeRealizarItinerario(itinerarioTermasCacheuta) shouldBe false
            }

            it("testeanis que relajado agarra cualquier viaje"){
                var criterio=Relajado()
                criterio.puedeRealizarItinerario(rogerFederer,itinerarioTermasCacheuta) shouldBe true
            }

            it("Personalidad Exigente, preguntar si realizar el Itinerario") {
                cr7.puedeRealizarItinerario(itinerarioGlaciarPeritoMoreno) shouldBe false
                cr7.criterioItinerario.shouldBeInstanceOf<Exigente>()
                cr7.puedeRealizarItinerario(itinerarioTermasCacheuta) shouldBe true
            }

            it("Personalidad Soniador, preguntar si realizar el Itinerario") {
              miaKhalifa.puedeRealizarItinerario(itinerarioTermasCacheuta) shouldBe true
                miaKhalifa.criterioItinerario.shouldBeInstanceOf<Soniador>()
                rogerFederer.criterioItinerario.shouldBeInstanceOf<Soniador>()
                rogerFederer.puedeRealizarItinerario(itinerarioBarilche) shouldBe false
                miaKhalifa.destinosDeseados= mutableListOf(cataratasIguazu,laCumbrecita)
                var criterio=Soniador()
                criterio.puedeRealizarItinerario(miaKhalifa,itinerarioLaCumbrecita) shouldBe true

            }

            it("Personalidad Precavido, preguntar si realizar el Itinerario") {
                // TODO: testear cuando devuelve true
                luquitaModric.puedeRealizarItinerario(itinerarioBarilche) shouldBe false
                luquitaModric.criterioItinerario.shouldBeInstanceOf<Precavido>()
            }

            it("Personalidad Localista, preguntar si realizar el Itinerario") {
                // TODO: testear cuando devuelve false
                chaquenioPalavecino.puedeRealizarItinerario(itinerarioLaCumbrecita) shouldBe true
                chaquenioPalavecino.criterioItinerario.shouldBeInstanceOf<Localista>()
            }

            it("Personalidad Activo, preguntar si realizar el Itinerario") {
                // TODO: testear cuando devuelve false
                friedrichGauss.puedeRealizarItinerario(itinerarioCataratasIguazu) shouldBe true
                friedrichGauss.criterioItinerario.shouldBeInstanceOf<Activo>()
            }

        }

        describe("probamos el porcentage de dificultad de n itinerario") {
            it("Comprobamos que en itinerario1 el 50% es dificultad baja de las actividades") {
                itinerario1.porcentajeDificultad(Dificultad.BAJA) shouldBe 50
            }
            it("Agregando actividad 3 Comprobamos que en itinerario1 el 66% es dificultad media de las actividades") {
                dia1.agregarActividad(actividad3)
                itinerario1.porcentajeDificultad(Dificultad.MEDIA) shouldBe 66
            }
        }

        describe("PRUEBA DE CRITERIOS PARA ALQUILAR VEHICULOS") {
            it("Comprobamos que le viene bien cualquier vheiculo") {
                cr7.leGusta(auto1) shouldBe true
                cr7.leGusta(auto2) shouldBe true
                cr7.leGusta(moto1) shouldBe true
            }
            it("Comprobamos que al neofilo solo le gustan los autos nuevos") {
                cr7.criterioVehiculo= Neofilo()
                auto2.fechaDeFabricacion=LocalDate.of(2022, 1, 15)
                cr7.leGusta(auto2) shouldBe true
                auto2.fechaDeFabricacion=LocalDate.of(2000, 1, 15)
                cr7.leGusta(auto2) shouldBe false
            }

            it("Comprobamos que al supersticioso solo le gustan los autos cuya fechaDeFabricacion es par") {
                cr7.criterioVehiculo= Supersticioso()
                auto2.fechaDeFabricacion=LocalDate.of(2022, 1, 15)
                cr7.leGusta(auto2) shouldBe true
                auto2.fechaDeFabricacion=LocalDate.of(2001, 1, 15)
                cr7.leGusta(auto2) shouldBe false
            }

            it("Comprobamos que al caprichoso le gustan los autos cuya y modelo comiencen con la misma letra") {
               cr7.criterioVehiculo= Caprichoso()
                cr7.leGusta(auto2) shouldBe true
                cr7.leGusta(auto1) shouldBe false

            }
            it("Comprobamos que al selectivo solo le gustan los vehiculos de la marca ferrari") {
                cr7.criterioVehiculo= Selectivo(Marcas.FERRARI)
                cr7.leGusta(auto2) shouldBe true
                cr7.leGusta(auto1) shouldBe false
            }

            it("Comprobamos que al Sinlimite le gustan los autos cuyo kilometrajelibre=true") {
                cr7.criterioVehiculo= SinLimite()
                auto2.kilometrajeLibre=false
                cr7.leGusta(auto1) shouldBe true
                cr7.leGusta(auto2) shouldBe false
            }

            it("Comprobamos que al Combinado le gustan los autos segun su lista de criterios") {
                cr7.criterioVehiculo= Combinado(mutableListOf(SinLimite(),Supersticioso()))
                cr7.leGusta(auto1) shouldBe false
                cr7.leGusta(auto2) shouldBe true
                cr7.leGusta(auto3) shouldBe true
                cr7.criterioVehiculo= Combinado()
                cr7.leGusta(auto1) shouldBe false
            }

        }
        describe("valor del destino mas caro"){
            it("el precio del destino mas caro es"){
            cr7.destinosDeseados= mutableListOf(laCumbrecita,cataratasIguazu)
            cr7.precioMayor() shouldBe 60000.0
            }
        }
    }
})



