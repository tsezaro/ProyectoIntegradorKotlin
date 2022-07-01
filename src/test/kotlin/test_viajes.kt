import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf
import java.time.LocalDate
import java.time.LocalDateTime

class TestViajes : DescribeSpec({
    isolationMode = IsolationMode.InstancePerTest
    describe("TESTEANDO TODO LO la clase viajes"){
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

        val cancun = Destino(
            pais = "Mexico",
            ciudad = "cancun",
            costoBase = 100000.0
        )

        val pepe = Usuario(
            nombre = "fernando",
            apellido = "pancho",
            paisDeResidencia = "Brasil",
            username = "perez",
            destinosDeseados = mutableListOf(glaciarPeritoMoreno),
            correoElectronico = "pepepecas@yahoo.com",
            amigos = mutableListOf()
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
            criterioItinerario = Localista(),
            correoElectronico = "elChaqueño@hotmail.com",
            amigos = mutableListOf()
        )

        val cr7 = Usuario(
            nombre = "cristiano",
            username = "carlitos",
            apellido = "ronaldo",
            paisDeResidencia = "peru",//TODO: cambiar strings por objetos (aumenar escalabilidad)
            fechaDeAlta = LocalDate.of(2000, 9, 25),
            destinosDeseados = mutableListOf(bariloche),
            diasParaViajar = 15,
            destinosVisitados = mutableListOf(
                glaciarPeritoMoreno,
                laCumbrecita,
                cataratasIguazu,
                termasCacheuta
            ),
            criterioItinerario = Exigente(Dificultad.MEDIA, 20),
            correoElectronico = "elbicho@outlook.com",
            amigos = mutableListOf()
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
            descripcion = "zoologico",
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
            descripcion = "parque acuatico",
            inicio = LocalDateTime.of(2022, 5, 22, 15, 0),
            fin = LocalDateTime.of(2022, 5, 22, 16, 0),
            dificultad = Dificultad.BAJA
        )

        // TODO: automatizar la creacion de dias
        val dia1=Dia(actividades = mutableListOf(actividad1,actividad2))
        val dia2=Dia()
        val dia3=Dia()
        val dia4=Dia(actividades = mutableListOf(actividad1))
        val dia5=Dia(actividades = mutableListOf(actividad3))
        val dia6=Dia(actividades = mutableListOf(actividad4))


        val itinerario1 = Itinerario(
            destino = cancun,
            creador = cr7,
            dias = mutableListOf(dia1,dia2,dia3)
        )

        val itinerario2 = Itinerario(
            destino = glaciarPeritoMoreno,
            creador = pepe,
            dias =  mutableListOf(dia2,dia4),
        )

        val itinerario3 = Itinerario(
            destino = termasCacheuta,
            creador = pepe,
            dias =  mutableListOf(dia1,dia2,dia4),
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
        val usuariosRepo = Repositorio<Usuario>()
        val itinerariosRepo = Repositorio<Itinerario>()
        val correo = Mail("","","","")

        val viajeABariloche = Viaje(itinerario1,vehiculo1,cr7,2)
        val viajeAGlaciarPeritoMoreno = Viaje(itinerario2,vehiculo2,pepe,5)
        val servicioCorreo = StubServicioCorreo()

        describe("Cristiano Viaja a Cancun"){
            val observer1 = ObserverMailAmigo(servicioCorreo, viajeABariloche)
            val observer2 = ObserverNoEsLocal()

            it("El costo del viaje a Bariloche"){
                viajeABariloche.costo() shouldBe 120673.0
            }

            it("Cristiano decide enviar email a los amigos de su viaje y quiere q los proximos viajes sean locales"){

                viajeABariloche.agregaPostObserver(observer1)
                viajeABariloche.agregaPostObserver(observer2)
                viajeABariloche.postObservers.size shouldBe 2
                cr7.destinosVisitados.size shouldBe 4
                cr7.puedeRealizarItinerario(itinerario2) shouldBe false
                cr7.criterioItinerario.shouldBeInstanceOf<Exigente>()
                viajeABariloche.viajar()
                cr7.destinosVisitados.size shouldBe 5
                cr7.criterioItinerario.shouldBeInstanceOf<Localista>()
                cr7.puedeRealizarItinerario(itinerario2) shouldBe true

            }

        //}

        //describe("El viaje de Pepe"){

            it("El costo del viaje a Glaciar Perito Moreno"){
                viajeAGlaciarPeritoMoreno.costo() shouldBe 100775.0
            }

            it("Pepe decide enviar email a los amigos de su viaje y quiere q los proximos viajes sean locales"){
                //val observer1 = ObserverMailAmigo(servicioCorreo, viajeAGlaciarPeritoMoreno)
                //val observer2 = ObserverNoEsLocal()
                viajeAGlaciarPeritoMoreno.agregaPostObserver(observer1)
                viajeAGlaciarPeritoMoreno.agregaPostObserver(observer2)
                viajeAGlaciarPeritoMoreno.postObservers.size shouldBe 2
                pepe.destinosVisitados.size shouldBe 0
                pepe.puedeRealizarItinerario(itinerario2) shouldBe false
                viajeAGlaciarPeritoMoreno.viajar()
                pepe.destinosVisitados.size shouldBe 1
                pepe.puedeRealizarItinerario(itinerario2) shouldBe false

            }

        }

    }
})

class StubServicioCorreo(var emailEnviado: MutableList<Mail> = mutableListOf()) : MailSender {
    override fun sendMail(mail: Mail) {
        this.emailEnviado.add(mail)
    }
}