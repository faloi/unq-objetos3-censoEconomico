package unq.objetos3.censoEconomico.domain

import org.joda.time.LocalDate
import unq.objetos3.censoEconomico.JasmineSpec
import unq.objetos3.censoEconomico.domain.registros.Registro
import unq.objetos3.censoEconomico.homes.InMemoryHomeRegistros

class ConjuntoAniosPromedioSpec extends JasmineSpec {
  implicit val homeRegistros = InMemoryHomeRegistros

  describe("A partir de un conjunto de anios y promediando los valores") {
    afterEach {
      homeRegistros.clear()
    }

    beforeEach {
      val sapucay = Empresa("Sapucay S.A.", "Camara de Industria del Litoral", new Departamento("Esquina", "Corrientes"))
      Registro(new LocalDate(2009, 12, 12), 35000, 25000, sapucay)
      Registro(new LocalDate(2010, 12, 12), 50000, 40000, sapucay)
      Registro(new LocalDate(2011, 12, 12), 58000, 48000, sapucay)
      Registro(new LocalDate(2013, 12, 12), 42000, 32000, sapucay)

      Registro(fecha = new LocalDate(2009, 12, 12), ventas = 1000, ganancias = 900)
    }

    it("should poder saberse el total de ventas") {
      ConjuntoAniosPromedio(2009, 2011, 2013).totalVentas should be(45000)
    }

    it("should poder saber las empresas con mas de X pesos en ventas") {
      Registro(new LocalDate(2013, 12, 12), 99000, 89000,
        Empresa("Microsoft", "Redmond Survey", Departamento("Redmond", "Washington")))

      ConjuntoAniosPromedio(2009, 2011, 2013).nombreEmpresasConVentasMayoresA(50000) should be(Seq("Microsoft"))
    }

    it("should poder saber cuantas empresas superan X pesos de ganancias") {
      Registro(new LocalDate(2013, 12, 12), 99000, 89000,
        Empresa("Microsoft", "Redmond Survey", Departamento("Redmond", "Washington")))

      ConjuntoAniosPromedio(2010, 2013).registrosConGananciasMayoresA(70000) should be(1)
    }
  }
}
