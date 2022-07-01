/* Test Dia
 *
 * NOTE: Aca solo se testean funcionalidades especifica del Dia.
 */


import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import java.time.LocalDateTime


class TestDia : DescribeSpec({
    isolationMode = IsolationMode.InstancePerTest

    val actividad1 = Actividad(
        costo = 200.00,
        descripcion = "cine",
        inicio = LocalDateTime.of(2022, 5, 22, 15, 0),
        fin = LocalDateTime.of(2022, 5, 22, 16, 0),
        dificultad = Dificultad.BAJA
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
    val actividad2 = Actividad(
        costo = 223.00,
        descripcion = "bowling",
        inicio = LocalDateTime.of(2022, 5, 22, 20, 0),
        fin = LocalDateTime.of(2022, 5, 22, 22, 0),
        dificultad = Dificultad.MEDIA
    )
    val actividad5 = Actividad(
        costo = 223.00,
        descripcion = "bowling",
        inicio = LocalDateTime.of(2022, 5, 22, 15, 0),
        fin = LocalDateTime.of(2022, 5, 22, 16, 0),
        dificultad = Dificultad.BAJA
    )


    val dia1=Dia(actividades = mutableListOf(actividad1,actividad2))

    describe("metodo horarioLibre") {
        it(" ver si se puede meter una actividad intermedia") {
            val inicio = LocalDateTime.of(2022, 5, 22, 18, 0)
            val fin = LocalDateTime.of(2022, 5, 22, 19, 30)
            dia1.horarioLibre(mutableListOf(actividad1,actividad2), inicio, fin) shouldBe true
        }

        it("ver si hay un horario libre antes de la actividad mas temprana") {
            val inicio = LocalDateTime.of(2022, 5, 22, 8, 0)
            val fin = LocalDateTime.of(2022, 5, 22, 9, 30)
            dia1.horarioLibre(mutableListOf(actividad1,actividad2), inicio, fin) shouldBe true
        }

        it("ver si hay horario libre despues de la ultima actividad agendada") {
            val inicio = LocalDateTime.of(2022, 5, 22, 22, 0)
            val fin = LocalDateTime.of(2022, 5, 22, 23, 30)
            dia1.horarioLibre(mutableListOf(actividad1,actividad2), inicio, fin) shouldBe true
        }

        it("horario ocupado totalmente(interseccion total)") {
            val inicio = LocalDateTime.of(2022, 5, 22, 15, 0)
            val fin = LocalDateTime.of(2022, 5, 22, 16, 0)
            dia1.horarioLibre(mutableListOf(actividad1,actividad2), inicio, fin) shouldBe false
        }

        it("interseccion derecha") {
            val inicio = LocalDateTime.of(2022, 5, 22, 15, 30)
            val fin = LocalDateTime.of(2022, 5, 22, 16, 30)
            dia1.horarioLibre(mutableListOf(actividad1,actividad2), inicio, fin) shouldBe false
        }

        it("interseccion izquierda") {
            val inicio = LocalDateTime.of(2022, 5, 22, 14, 30)
            val fin = LocalDateTime.of(2022, 5, 22, 15, 30)
            dia1.horarioLibre(mutableListOf(actividad1,actividad2), inicio, fin) shouldBe false
        }

        it("que la contemple") {
            val inicio = LocalDateTime.of(2022, 5, 22, 12, 0)
            val fin = LocalDateTime.of(2022, 5, 22, 18, 0)
            dia1.horarioLibre(mutableListOf(actividad1,actividad2), inicio, fin) shouldBe false
        }

        it("ingresa una actividad mas corta en un horario donde ya hay una actividad mas larga") {
            val inicio = LocalDateTime.of(2022, 5, 22, 15, 30)
            val fin = LocalDateTime.of(2022, 5, 22, 15, 45)
            dia1.horarioLibre(mutableListOf(actividad1,actividad2), inicio, fin) shouldBe false
        }

        it(" ver si hay un horario libre otro dia") {
            // NOTE: ESTO ESTA DEPRECADO
            val inicio = LocalDateTime.of(2022, 5, 23, 15, 0)
            val fin = LocalDateTime.of(2022, 5, 23, 16, 0)
            dia1.horarioLibre(mutableListOf(actividad1,actividad2), inicio, fin) shouldBe true
        }
    }

    describe("dia.actividadesSeSolapan()") {
        it("SI se solapan las actividades") {
            dia1.actividades.add(actividad4)
            dia1.actividadesSeSolapan() shouldBe true
            dia1.actividades shouldBe listOf(actividad1, actividad2, actividad4)
        }
    }
    describe("dia sin actividades") {
        it("lista de actividades vacia") {
            dia1.actividades= mutableListOf()
            dia1.sinActividades() shouldBe true
            dia1.actividades.add(actividad4)
            dia1.sinActividades() shouldBe false
        }
    }

})

