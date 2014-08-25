package unq.objetos3.censoEconomico

import org.joda.time.LocalDate

class EstadisticasAnualesSpec extends UnitSpec {
  "Una estadistica anual" should "saber las ventas por año" in {
    HomeEmpresas.agregar(
      new Empresa()
        .agregarRegistro(new Registro(new LocalDate(2013, 12, 30), 70000, 50000))
        .agregarRegistro(new Registro(new LocalDate(2014, 12, 30), 100000, 80000)),

      new EmpresaAnonima()
        .agregarRegistro(new Registro(new LocalDate(2013, 12, 30), 50000, 30000))
        .agregarRegistro(new Registro(new LocalDate(2014, 12, 30), 40000, 20000))
    )

    new EstadisticasAnuales(2013).totalVentas should be(120000)
  }
}
