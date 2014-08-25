package unq.objetos3.censoEconomico

import org.joda.time.LocalDate

class EmpresaSpec extends UnitSpec {
  "Una empresa" should "saber sus ventas por año" in {
    new Empresa()
      .agregarRegistro(new Registro(new LocalDate(2013, 12, 30), 70000, 50000))
      .agregarRegistro(new Registro(new LocalDate(2014, 12, 30), 100000, 80000))
      .totalVentas(2013) should be(70000)
  }
}
