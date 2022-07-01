import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import java.time.LocalDate

class TestVehiculo : DescribeSpec ({
    isolationMode = IsolationMode.InstancePerTest
    describe("test Vehiculo") {


        val vehiculo1 = Auto(
            marca = Marcas.FERRARI,
            modelo = Modelos.F75,
            fechaDeFabricacion = LocalDate.of(2019, 10, 15),
            costoDiario = 100.0,
            kilometrajeLibre = true

        )


        val vehiculos2 = Auto(
            marca = Marcas.HONDA,
            modelo = Modelos.CITY,
            fechaDeFabricacion = LocalDate.of(2020, 10, 15),
            costoDiario = 100.0,
            kilometrajeLibre = true
        )

        it("aplicamos descuento convenio") {
            vehiculo1.aplicaDescuentoConvenio(7) shouldBe 0.0
            vehiculos2.aplicaDescuentoConvenio(7) shouldBe 70.0
        }

        it("tiene convenio por marca ") {
            vehiculo1.tieneConvenio() shouldBe false
            vehiculos2.tieneConvenio() shouldBe true
        }

        it("la antiguedad es") {
            vehiculo1.antiguedad() shouldBe 2
            vehiculos2.antiguedad() shouldBe 1
        }

        it("costo base del vehiculo") {
            vehiculo1.costoBase(7) shouldBe 700.0
        }

    }

})

class TestMoto : DescribeSpec ({
    isolationMode = IsolationMode.InstancePerTest
    describe("test de Moto") {
        val moto1 = Moto(
            marca = Marcas.GILERA,
            modelo = Modelos.ALPISO,
            fechaDeFabricacion = LocalDate.of(2019, 10, 15),
            costoDiario = 10.0,
            kilometrajeLibre = true,
            cilindrada = 260
        )
        it("costo de alquiler de la moto cilindrada>250") {
            moto1.cilindrada = 100
            moto1.costoAlquiler(7) shouldBe 70.0
                    }
        it("costo de alquiler de la moto cilindrada<250") {
            moto1.cilindrada = 260
            moto1.costoAlquiler(7) shouldBe 3570.0
        }
    }
})

class TestAuto : DescribeSpec ({
    isolationMode = IsolationMode.InstancePerTest
    describe("test de Auto") {


        val auto1 = Auto(
            marca = Marcas.FERRARI,
            modelo = Modelos.F75,
            fechaDeFabricacion = LocalDate.of(2022, 10, 15),
            costoDiario = 10.0,
            kilometrajeLibre = true,
            hatchback = true
        )


        it("costo de alquiler de auto con hatchback") {
            auto1.costoAlquiler(7) shouldBe 77.0
            auto1.costoAlquiler(10) shouldBe 110.0
        }
        it("costo de alquiler de auto con hatchback") {
            auto1.hatchback = false
            auto1.costoAlquiler(7) shouldBe 87.5
        }

    }
})

class TestCamioneta : DescribeSpec ({
    isolationMode = IsolationMode.InstancePerTest
    describe("test de Camioneta") {

        val camioneta1 = Camioneta(
            marca = Marcas.FERRARI,
            modelo = Modelos.F75,
            fechaDeFabricacion = LocalDate.of(2022, 10, 15),
            costoDiario = 10.0,
            kilometrajeLibre = true,
            es4x4 = true
        )
        it("costo de alquiler de camioneta 4X4") {
            camioneta1.costoAlquiler(7) shouldBe 15070.0
            camioneta1.costoAlquiler(10) shouldBe 19600.0
        }
        it("costo de alquiler de camioneta 4X2") {
            camioneta1.es4x4 = false
            camioneta1.costoAlquiler(7) shouldBe 10070.0

        }
        it("calculando dias de exceso") {
            camioneta1.diasDeExceso(7) shouldBe 0
            camioneta1.diasDeExceso(10) shouldBe 3
        }
    }
})