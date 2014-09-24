package unq.objetos3.censoEconomico.domain.registros

import org.joda.time.LocalDate
import unq.objetos3.censoEconomico.JasmineSpec
import unq.objetos3.censoEconomico.domain.{Empresa, FuenteInformacion, Departamento}
import unq.objetos3.censoEconomico.domain.registros.validadores.{ValidadorProvincia, ValidadorFecha, ValidadorVentas}
import unq.objetos3.censoEconomico.homes.InMemoryHomeRegistros

class RegistroSpec extends JasmineSpec {
  implicit val homeRegistros = InMemoryHomeRegistros

  describe("Un registro") {
    afterEach {
      homeRegistros.clear()
    }

    describe("es consistente si") {
      it("sus ventas son positivas") {
        (new Registro(ventas = 500) with ValidadorVentas).esConsistenteMixin should be (true)
      }

      it("la informacion fue obtenida despues del anio al que refiere") {
        (new Registro(fecha = new LocalDate(2012, 12, 12), anioObtencion = 2013) with ValidadorFecha).esConsistenteMixin should be (true)
      }

      it("la provincia es coherente con las que la fuente trabaja") {
        (new Registro(departamento = Departamento("CABA", "CABA"), fuente = FuenteInformacion("GCBA", "CABA")) with ValidadorProvincia)
          .esConsistenteMixin should be (true)
      }

      it("la provincia es incoherente con la fuente pero el registro no es anonimo") {
        val cooperativa = Empresa("Cooperativa de metalurgica",
          FuenteInformacion("Cooperativas Santa Fe", "Santa Fe"),
          Departamento("Villa Maria", "Cordoba")
        )

        (new Registro(empresa = Some(cooperativa), departamento = cooperativa.departamento, fuente = cooperativa.fuente) with ValidadorProvincia)
          .esConsistenteMixin should be (true)
      }
    }

    describe("no es consistente si") {
      it("sus ventas son negativas") {
        (new Registro(ventas = -500) with ValidadorVentas).esConsistenteMixin should be (false)
      }

      it("la informacion fue obtenida el mismo anio al que refiere") {
        (new Registro(fecha = new LocalDate(2012, 12, 12), anioObtencion = 2012) with ValidadorFecha).esConsistenteMixin should be (false)
      }

      it("la provincia no es coherente con las que la fuente trabaja") {
        (new Registro(departamento = Departamento("Lanus", "Buenos Aires"), fuente = FuenteInformacion("GCBA", "CABA")) with ValidadorProvincia)
          .esConsistenteMixin should be (false)
      }
    }
  }

}
