import Service.ServicioDestinos
import Service.AdapterDestinos
import Service.getObjectMapper
import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import java.time.LocalDate
import java.time.LocalDateTime


class TestRepositorio : DescribeSpec({
    isolationMode = IsolationMode.InstancePerTest

    describe("TESTEANDO TODO LO DE LA CLASE REPOSITORIO"){
        /*variables*/
        val actividad1 = Actividad(
            costo = 200.00,
            descripcion = "cine",
            inicio = LocalDateTime.of(2022, 5, 22, 15, 3),
            fin = LocalDateTime.of(2022, 5, 22, 15, 4),
            dificultad = Dificultad.BAJA
        )

        val bsas = Destino(pais = "Argentina", ciudad = "Buenos Aires", costoBase = 8000.0)
        val lapaz = Destino(pais = "Bolivia", ciudad = "lapaz", costoBase = 30000.0)
        val sucre = Destino(pais = "Bolivia", ciudad = "sucre", costoBase = 30000.0)

        val chaquenioPalavecino = Usuario(
            nombre = "Chaqueño",
            username = "elchaqueño",
            apellido = "Palavecino",
            paisDeResidencia = "Argentina",
            fechaDeAlta = LocalDate.now().minusYears(35),
            destinosDeseados = mutableListOf(bsas, lapaz),
            destinosVisitados = mutableListOf(lapaz),
            diasParaViajar = 52,
            criterioItinerario = Localista()
        )

        val luquitaModric = Usuario(
            nombre = "Luka",
            username = "luquitas85",
            apellido = "Modric",
            paisDeResidencia = "Croacia",
            fechaDeAlta = LocalDate.now().minusYears(18),
            destinosDeseados = mutableListOf(lapaz),
            destinosVisitados = mutableListOf(bsas),
            diasParaViajar = 15,
            criterioItinerario = Precavido()
        )

        val dia1=Dia(actividades = mutableListOf(actividad1))
        val dia2=Dia()
        val dia3=Dia()

        val itinerario1 = Itinerario(
            destino = bsas,
            creador = chaquenioPalavecino,
            dias = mutableListOf(dia1,dia2,dia3)
        )

        val itinerario2 = Itinerario(
            destino = lapaz,
            creador = luquitaModric,
            dias = mutableListOf(dia1,dia2)
        )

        val vehiculo1 = Auto(
            marca = Marcas.FERRARI,
            modelo = Modelos.F75,
            fechaDeFabricacion = LocalDate.of(2019, 10, 15),
            costoDiario = 100.0,
            kilometrajeLibre = true
        )


        val vehiculo2 = Auto(
            marca = Marcas.HONDA,
            modelo = Modelos.CITY,
            fechaDeFabricacion = LocalDate.of(2020, 10, 15),
            costoDiario = 100.0,
            kilometrajeLibre = true
        )

        /*repos*/
        val destinosRepo = Repositorio<Destino>()
        val usuariosRepo = Repositorio<Usuario>()
        val itinerariosRepo = Repositorio<Itinerario>()
        val vehiculosRepo = Repositorio<Vehiculo>()

        /*test*/
        describe("Probando funcionalidades del Repo"){
            it("Agregando nuevos objetos a los repo destino"){
                destinosRepo.create(bsas)
                bsas.id shouldBe 1
                destinosRepo.create(lapaz)
                lapaz.id shouldBe 2
                destinosRepo.memoria.contains(bsas) shouldBe true
                destinosRepo.memoria.contains(lapaz) shouldBe true
                destinosRepo.memoria.count() shouldBe 2
            }

            it("Eliminando un objeto al repo usuarios"){
                usuariosRepo.create(chaquenioPalavecino)
                usuariosRepo.create(luquitaModric)
                usuariosRepo.memoria.count() shouldBe 2

                usuariosRepo.delete(chaquenioPalavecino)
                usuariosRepo.memoria.contains(luquitaModric) shouldBe true
                usuariosRepo.memoria.contains(chaquenioPalavecino) shouldBe false
                usuariosRepo.memoria.count() shouldBe 1
            }
            it( "actualizando un objeto del repo itinerario"){
                itinerariosRepo.create(itinerario1)
                itinerariosRepo.create(itinerario2)

                itinerario1.cambiarDestino(lapaz)
                itinerariosRepo.update(itinerario1)
                itinerariosRepo.memoria.count() shouldBe 2
                itinerario1.id shouldBe 1

                itinerario2.cambiarDestino(bsas)
                itinerariosRepo.update(itinerario2)
                itinerariosRepo.memoria.count() shouldBe 2
                itinerario2.id shouldBe 2
            }

            it ("actualizamos multiples objetos") {
                itinerariosRepo.create(itinerario1)
                itinerariosRepo.create(itinerario2)

                itinerario1.cambiarDestino(lapaz)
                itinerario2.cambiarDestino(bsas)

                itinerariosRepo.update(listOf(itinerario1, itinerario2))
                itinerariosRepo.memoria.count() shouldBe 2
                itinerario1.id shouldBe 1
                itinerario2.id shouldBe 2
            }

            it ("update lanza excepcion cuando el ID no existe en memoria") {
                itinerario1.id = 89
                // TODO: este test.
            }


            it("devolver un objeto por id del repo Vehiculo"){
                vehiculosRepo.create(vehiculo1)
                vehiculosRepo.create(vehiculo2)
                vehiculosRepo.getById(1) shouldBe vehiculo1
                vehiculosRepo.getById(2) shouldBe vehiculo2
            }
            it("Devolver una lista de objetos segun los criterios"){
                destinosRepo.create(bsas)
                destinosRepo.create(lapaz)

                usuariosRepo.create(chaquenioPalavecino)
                usuariosRepo.create(luquitaModric)

                vehiculosRepo.create(vehiculo1)
                vehiculosRepo.create(vehiculo2)

                itinerariosRepo.create(itinerario1)
                itinerariosRepo.create(itinerario2)
                itinerario1.destino = lapaz
                itinerario2.destino = sucre

                destinosRepo.search("Argentina") shouldBe listOf(bsas)
                usuariosRepo.search("luquitas85") shouldBe listOf(luquitaModric)
                vehiculosRepo.search("F7") shouldBe listOf(vehiculo1)

                val actual = itinerariosRepo.search("Bolivia")
                actual.contains(itinerario1) shouldBe true
                actual.contains(itinerario2) shouldBe true
                actual.size shouldBe 2
            }
        }
    }

    describe("Test adapterServicio") {

        val repositorioDestino = Repositorio<Destino>()
        val servicioDestino = StubServicioDestino()
        val adapterDestino = AdapterDestinos(servicioDestino,getObjectMapper(), repositorioDestino)


        it ("Test adapterDestino.parsearDestinos()") {
            val actual = adapterDestino.parsearDestinos()

            actual.size shouldBe 3

            actual[0].id shouldBe 9
            actual[0].pais shouldBe "Argentina"
            actual[0].ciudad shouldBe "Mar del Plata"
            actual[0].costoBase shouldBe 10000

            actual[1].id shouldBe 12
            actual[1].pais shouldBe "Brasil"
            actual[1].ciudad shouldBe "Rio de Janeiro"
            actual[1].costoBase shouldBe 20000

            actual[2].id shouldBe 0
            actual[2].pais shouldBe "Indonesia"
            actual[2].ciudad shouldBe "Bali"
            actual[2].costoBase shouldBe 30000
        }

        it ("Test adapterDestino.updateDestinos()") {
            // Setup

            repositorioDestino.memoria = mutableListOf(
                Destino(id = 9, pais = "Argentina", ciudad = "Mar del Plata", costoBase = 8000.0),
                Destino(id = 12, pais = "Brasil", ciudad = "Rio de Janeiro", costoBase = 30000.0)
            )

            // Test
            adapterDestino.updateRepositorio()

            repositorioDestino.memoria.size shouldBe 3

            val marDelPlata: Destino = repositorioDestino.getById(9)
            marDelPlata.id shouldBe 9
            marDelPlata.costoBase shouldBe 10000.0
            marDelPlata.costoBase shouldNotBe 8000.0

            val rioJaneiro: Destino = repositorioDestino.getById(12)
            rioJaneiro.id shouldBe 12
            rioJaneiro.costoBase shouldBe 20000.0
            rioJaneiro.costoBase shouldNotBe 30000.0

            val bali: Destino = repositorioDestino.memoria[2]
            bali.id shouldNotBe 0
            bali.pais shouldBe "Indonesia"
            bali.ciudad shouldBe "Bali"
            bali.costoBase shouldBe 30000
        }
    }
})

class StubServicioDestino : ServicioDestinos {
    private val JSON =  "[\n" +
            "    {\n" +
            "        \"id\": 9,\n" +
            "        \"pais\": \"Argentina\",\n" +
            "        \"ciudad\": \"Mar del Plata\",\n" +
            "        \"costo\": 10000\n" +
            "      }, \n" +
            "  {\n" +
            "        \"id\": 12,\n" +
            "        \"pais\": \"Brasil\",\n" +
            "        \"ciudad\": \"Rio de Janeiro\",\n" +
            "        \"costo\": 20000\n" +
            "      },\n" +
            "   {\n" +
            "        \"pais\": \"Indonesia\",\n" +
            "        \"ciudad\": \"Bali\",\n" +
            "        \"costo\": 30000\n" +
            "      }\n" +
            "]\n"

    override fun getDestinos(): String {
        return JSON
    }
}

