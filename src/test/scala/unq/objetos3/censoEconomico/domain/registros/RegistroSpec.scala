package unq.objetos3.censoEconomico.domain.registros

import org.joda.time.LocalDate
import unq.objetos3.censoEconomico.JasmineSpec
import unq.objetos3.censoEconomico.domain.registros.validadores.mixines.{ValidadorVentasMixin, ValidadorProvinciaMixin, ValidadorFechaMixin}
import unq.objetos3.censoEconomico.domain.registros.validadores.strategy.{ValidadorProvinciaStrategy, ValidadorFechaStrategy, ValidadorVentasStrategy}
import unq.objetos3.censoEconomico.domain.{Empresa, FuenteInformacion, Departamento}
import unq.objetos3.censoEconomico.homes.InMemoryHomeRegistros

class RegistroSpec extends JasmineSpec {
  implicit val homeRegistros = InMemoryHomeRegistros

  describe("Un registro") {
    afterEach {
      homeRegistros.clear()
    }

    describe("es consistente (con mixines) si") {
      it("sus ventas son positivas") {
        (new Registro(ventas = 500) with ValidadorVentasMixin).esConsistenteMixin should be (true)
      }

      it("la informacion fue obtenida despues del anio al que refiere") {
        (new Registro(fecha = new LocalDate(2012, 12, 12), anioObtencion = 2013) with ValidadorFechaMixin).esConsistenteMixin should be (true)
      }

      it("la provincia es coherente con las que la fuente trabaja") {
        (new Registro(departamento = Departamento("CABA", "CABA"), fuente = FuenteInformacion("GCBA", "CABA")) with ValidadorProvinciaMixin)
          .esConsistenteMixin should be (true)
      }

      it("la provincia es incoherente con la fuente pero el registro no es anonimo") {
        val cooperativa = Empresa("Cooperativa de metalurgica",
          FuenteInformacion("Cooperativas Santa Fe", "Santa Fe"),
          Departamento("Villa Maria", "Cordoba")
        )

        (new Registro(empresa = Some(cooperativa), departamento = cooperativa.departamento, fuente = cooperativa.fuente) with ValidadorProvinciaMixin)
          .esConsistenteMixin should be (true)
      }
    }

    describe("no es consistente (con mixines) si") {
      it("sus ventas son negativas") {
        (new Registro(ventas = -500) with ValidadorVentasMixin).esConsistenteMixin should be (false)
      }

      it("la informacion fue obtenida el mismo anio al que refiere") {
        (new Registro(fecha = new LocalDate(2012, 12, 12), anioObtencion = 2012) with ValidadorFechaMixin).esConsistenteMixin should be (false)
      }

      it("la provincia no es coherente con las que la fuente trabaja") {
        (new Registro(departamento = Departamento("Lanus", "Buenos Aires"), fuente = FuenteInformacion("GCBA", "CABA")) with ValidadorProvinciaMixin)
          .esConsistenteMixin should be (false)
      }
    }

    describe("es consistente (con strategies) si") {
      it("sus ventas son positivas") {
        Registro(ventas = 500).conValidacion(ValidadorVentasStrategy).esConsistenteStrategy should be (true)
      }

      it("la informacion fue obtenida despues del anio al que refiere") {
        Registro(fecha = new LocalDate(2012, 12, 12), anioObtencion = 2013)
          .conValidacion(ValidadorFechaStrategy)
          .esConsistenteStrategy should be (true)
      }

      it("la provincia es coherente con las que la fuente trabaja") {
        Registro(departamento = Departamento("CABA", "CABA"), fuente = FuenteInformacion("GCBA", "CABA"))
          .conValidacion(ValidadorProvinciaStrategy)
          .esConsistenteStrategy should be (true)
      }

      it("la provincia es incoherente con la fuente pero el registro no es anonimo") {
        val cooperativa = Empresa("Cooperativa de metalurgica",
          FuenteInformacion("Cooperativas Santa Fe", "Santa Fe"),
          Departamento("Villa Maria", "Cordoba")
        )

        Registro(empresa = Some(cooperativa), departamento = cooperativa.departamento, fuente = cooperativa.fuente)
          .conValidacion(ValidadorProvinciaStrategy)
          .esConsistenteStrategy should be (true)
      }
    }

    describe("no es consistente (con strategies) si") {
      it("sus ventas son negativas") {
        Registro(ventas = -500).conValidacion(ValidadorVentasStrategy).esConsistenteStrategy should be (false)
      }

      it("la informacion fue obtenida el mismo anio al que refiere") {
        Registro(fecha = new LocalDate(2012, 12, 12), anioObtencion = 2012)
          .conValidacion(ValidadorFechaStrategy)
          .esConsistenteStrategy should be (false)
      }

      it("la provincia no es coherente con las que la fuente trabaja") {
        Registro(departamento = Departamento("Lanus", "Buenos Aires"), fuente = FuenteInformacion("GCBA", "CABA"))
          .conValidacion(ValidadorProvinciaStrategy)
          .esConsistenteStrategy should be (false)
      }
    }
  }
}
