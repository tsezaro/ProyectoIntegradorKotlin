/* Test Destino
 *
 * NOTE: Aca solo se testean funcionalidades especifica del Destino.
 */

import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import java.time.LocalDate

class TestDestino : DescribeSpec({
    isolationMode = IsolationMode.InstancePerTest
    describe("TESTEANDO TODO LO DE LA CLASE DESTINO") {
        /* USUARIOS */
        val ruperto = Usuario(
            nombre = "Ruperto",
            apellido = "Rupe",
            paisDeResidencia = "Bolivia",
            username = "Funez",
            fechaDeAlta = LocalDate.now().minusYears(20)
        )
        val luciano = Usuario(
            nombre = "Luciano",
            username = "LuVega",
            apellido = "Vega",
            paisDeResidencia = "Argentina",
            fechaDeAlta = LocalDate.now().minusYears(24)
        )

        /* DESTINOS */
        val bsas = Destino(pais = "Argentina", ciudad = "Buenos Aires", costoBase = 8000.0)
        val lapaz = Destino(pais = "Bolivia", ciudad = "lapaz", costoBase = 30000.0)


        /* TESTS */
        describe("Metodo esLocal()") {
            it("Destino con PAIS Argentina es considerado local") {
                bsas.esLocal() shouldBe true
                lapaz.esLocal() shouldBe false
            }
        }

        describe("Metodo costoTotal()") {
            it("Costo Total") {
                lapaz.costoTotal(ruperto) shouldBe 30600
                bsas.costoTotal(luciano) shouldBe 6800.0
            }
        }

        describe("destino.esValido()") {
            it("destino con pais, ciudad y costo mayor a cero es valido") {
                lapaz.esValido() shouldBe true
            }
            it("costoBase menor a 0 (cero)") {
                val novalido = Destino(pais = "Bolivia", ciudad = "lapaz", costoBase = -1.0)
                novalido.esValido() shouldBe false
            }
            it("pais no nulo") {
                val novalido = Destino(pais = "", ciudad = "lapaz", costoBase = 30000.0)
                novalido.esValido() shouldBe false
            }
            it("ciudad no nula") {
                val novalido = Destino(pais = "Bolivia", ciudad = "", costoBase = 30000.0)
                novalido.esValido() shouldBe false
            }
        }
    }
})

