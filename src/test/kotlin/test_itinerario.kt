/* Test Itinerario
 *
 * NOTE: Aca solo se testean funcionalidades especifica del Itinerario.
 */


import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import java.time.LocalDate
import java.time.LocalDateTime


class TestItinerario : DescribeSpec({
    isolationMode = IsolationMode.InstancePerTest

    describe("TESTEANDO TODO LO DE LA CLASE ITINERARIO") {
        /*  DESTINOS */
        val viaje = Destino(
            pais = "Italia",
            ciudad = "napole",
            costoBase = 12.000
        )

        /* USUARIOS */
        val cr7 = Usuario(
            nombre = "cristiano",
            apellido = "ronaldo",
            username = "elbicho",
            paisDeResidencia = "Portugal",
            fechaDeAlta = LocalDate.now().minusYears(23),
            destinosDeseados = mutableListOf(viaje)
        )
        val pepe = Usuario(
            nombre = "fernando",
            apellido = "pancho",
            paisDeResidencia = "Brasil",
            username = "perez",
            destinosDeseados = mutableListOf(viaje)
        )
        val chaquenioPalavecino = Usuario(
            nombre = "Chaqueño",
            apellido = "Palavecino",
            username = "elchaqueño",
            paisDeResidencia = "Argentina",
            fechaDeAlta = LocalDate.now().minusYears(35)
        )


        /* ITINERARIO */
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

        val actividad4 = Actividad(
            costo = 223.00,
            descripcion = "bowling",
            inicio = LocalDateTime.of(2022, 5, 22, 15, 0),
            fin = LocalDateTime.of(2022, 5, 22, 16, 0),
            dificultad = Dificultad.BAJA
        )

        // TODO: automatizar la creacion de dias
        val dia1=Dia(actividades = mutableListOf(actividad1,actividad2))
        val dia2=Dia()
        val dia3=Dia()
        val dia4=Dia()


        val itinerario1 = Itinerario(
            destino = viaje,
            creador = pepe,
            dias = mutableListOf(dia1,dia2,dia3)
        )

        val itinerario2 = Itinerario(
            destino = viaje,
            creador = pepe,
            dias =  mutableListOf(dia1,dia2),
        )

        /* TEST */

        describe("Probamos que un usuario tiene permiso para editar un itinerario") {
            it("pepe puede crear itinerario") {
                itinerario1.puedeEditar(pepe) shouldBe true
            }
            it("Cristiano Ronaldo no tiene permiso para editar un itinerario") {
                itinerario1.puedeEditar(cr7) shouldBe false
            }
            it("Si Cristiano es amigo de Pepe, y conoce el destino, puede editar el itinerario") {
                pepe.agregarAmigo(cr7)
                itinerario1.puedeEditar(cr7) shouldBe true
            }
        }

        describe("Probamos el costo de un itinerario") {
            it("Costo del itinerario1") {
                itinerario1.costo() shouldBe 423.00
            }
            it("Agrego una actividad y calculamos el costo") {
                dia1.agregarActividad(actividad3)
                itinerario1.costo() shouldBe 646.00
            }
            it("quito una actividad y calculamos el costo") {
                dia1.quitarActividad(actividad1)
                itinerario1.costo() shouldBe 223.00
            }
        }

        describe("Probamos el Promedio Diario por actividad") {
            it("promedio diario del itinerario1 actividad1 = 60 min, actividad 2 = 120 min cantidad dias = 3  (60+120)/10") {
                itinerario1.duracionPromedioPorDia() shouldBe 60
            }
            it("agrego actividad 3, actividad1 = 60 min, actividad 2 = 120 min, actividad 3 = 60 min, cantidad dias = 10  (60+120+60)/10") {
                dia1.agregarActividad(actividad3)
                itinerario1.duracionPromedioPorDia() shouldBe 80
            }
        }

        //DEPRECADO cambiar la cantidad
        // TODO: testear agregar dias al itinerario y ver la cantidad

        describe("Probamos testear la dificultad del itinerario") {

            it("toma la mayor dificultad cuando hay un empate de dificultades") {
                itinerario1.dias = mutableListOf(dia1,dia2)
                actividad1.dificultad = Dificultad.BAJA
                actividad2.dificultad = Dificultad.MEDIA
                itinerario1.dificultad() shouldBe Dificultad.MEDIA
                actividad1.dificultad = Dificultad.ALTA
                actividad2.dificultad = Dificultad.MEDIA
                itinerario1.dificultad() shouldBe Dificultad.ALTA
            }

            it("Mayor cantidad de actividades MEDIAs => dificultad itinerario MEDIA") {
                dia1.actividades = mutableListOf(actividad1,actividad2,actividad3)
                itinerario1.dias = mutableListOf(dia1,dia3)
                actividad1.dificultad = Dificultad.BAJA
                actividad2.dificultad = Dificultad.MEDIA
                actividad3.dificultad = Dificultad.MEDIA
                itinerario1.dificultad() shouldBe Dificultad.MEDIA
            }

            it("Mayor cantidad de actividades BAJAs => dificultad itinerario BAJA") {
                dia1.actividades = mutableListOf(actividad1,actividad2,actividad3)
                itinerario1.dias = mutableListOf(dia1,dia2)
                actividad1.dificultad = Dificultad.BAJA
                actividad2.dificultad = Dificultad.BAJA
                actividad3.dificultad = Dificultad.MEDIA
                itinerario1.dificultad() shouldBe Dificultad.BAJA
            }
            it("Mayor cantidad de actividades ALTAs => dificultad itinerario ALTA") {
                dia1.actividades = mutableListOf(actividad1,actividad2,actividad3,actividad4)
                itinerario1.dias = mutableListOf(dia1,dia2)
                actividad1.dificultad = Dificultad.BAJA
                actividad2.dificultad = Dificultad.MEDIA
                actividad3.dificultad = Dificultad.ALTA
                actividad4.dificultad = Dificultad.ALTA
                itinerario1.dificultad() shouldBe Dificultad.ALTA
            }
        }

        describe("itinerario.actividadesSeSolapan()") {
            it("NO se solapan las actividades") {
                itinerario1.actividadesSeSolapan() shouldBe false
                dia1.actividades shouldBe listOf(actividad1, actividad2)
            }
        }

        describe("test puntuacion") {
            it ("test metodo tieneCalificacionValida()") {
                var puntuacion = UsuarioItem(cr7, puntuacion = -7)
                puntuacion.tienePuntuacionValida() shouldBe false
                puntuacion = UsuarioItem(usuario = cr7, puntuacion = 8)
                puntuacion.tienePuntuacionValida() shouldBe true
                puntuacion = UsuarioItem(usuario = cr7, puntuacion = 1000)
                puntuacion.tienePuntuacionValida() shouldBe false
            }
        }

        describe("Test puntuaciones") {
            it("un usuario puede puntuar") {
                itinerario1.agregarUsuario(cr7)
                itinerario1.noPuntuo(cr7) shouldBe true
                itinerario1.puntuar(cr7, 4 )
                itinerario1.puedeEditar(cr7)
                itinerario1.yaPuntuo(cr7) shouldBe true
            }
        }

        describe("itinerario.esValido()") {
            itinerario2.esValido() shouldBe true
        }

        describe("agregar y quitar dia") {
            it("agregar dia") {
                itinerario1.dias = mutableListOf(dia1,dia2)
                itinerario1.agregarDia(dia3)
                itinerario1.dias shouldBe mutableListOf(dia1,dia2,dia3)
            }

            it("quitar dia") {
                itinerario1.quitarDia(dia3)
                itinerario1.dias shouldBe mutableListOf(dia1,dia2)
            }
        }

        describe("itinerario sin actividades tiene dificultad nula"){
            it("dificultad nula"){
                itinerario1.dias= mutableListOf()
                itinerario1.dificultad() shouldBe Dificultad.NULA
            }
        }

    }


})

