/* Test Actividad
 *
 * NOTE: Aca solo se testean funcionalidades especifica de la Actividad.
 */


import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import java.time.LocalDateTime


class TestActividad : DescribeSpec({
    isolationMode = IsolationMode.InstancePerTest
    describe("TESTEANDO TODO LO DE CLASE ACTIVIDAD") {
        val actividad1 = Actividad(
            costo = 200.00,
            descripcion = "cine",
            inicio = LocalDateTime.of(2022, 5, 22, 15, 3),
            fin = LocalDateTime.of(2022, 5, 22, 15, 4),
            dificultad = Dificultad.BAJA
        )

        val actividad2 = Actividad(
            costo = -3500.00,
            inicio = LocalDateTime.of(2022, 5, 21, 12, 30),
            fin = LocalDateTime.of(2022, 3, 21, 15, 30),
        )

        it("devuelve la diferencia en minutos") {
            actividad1.duracion() shouldBe 1
        }

        it("devuelve el numero de dia de la actividad") {
            actividad1.obtenerDia() shouldBe 22
        }

        describe("actividad valida") {
            it("la descripcion no es null o vacia") {
                actividad1.descripcionNoNull() shouldBe true
            }

            it("la descripcion es null o vacia") {
                actividad2.descripcionNoNull() shouldBe false
            }

            it("hora de inicio mayor a fin") {
                actividad1.validacionHorario() shouldBe true
            }

            it("hora de inicio menor a fin") {
                actividad2.validacionHorario() shouldBe false
            }

            it("deben tener una dificultad establecida") {
                actividad1.dificultadNoNula() shouldBe true
            }

            it("No deben tener una dificultad establecida") {
                actividad2.dificultadNoNula() shouldBe false
            }

            it("costo mayor a cero o igual a cero") {
                actividad1.costoNoNull() shouldBe true
            }

            it("costo menor a cero o igual a cero") {
                actividad2.costoNoNull() shouldBe false
            }

            it("actividad valida") {
                actividad1.esValida() shouldBe true
            }

            it("actividad valida") {
                actividad2.esValida() shouldBe false
            }
        }
    }
})


