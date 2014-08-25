package unq.objetos3.censoEconomico

import org.joda.time.LocalDate

class EstadisticasAnualesSpec extends UnitSpec {
  before {
    HomeEmpresas.add(
      new Empresa()
        .agregarRegistro(new Registro(new LocalDate(2013, 12, 30), 70000, 50000)) //tasa: 71,43
        .agregarRegistro(new Registro(new LocalDate(2014, 12, 30), 100000, 80000)), //tasa: 80

      new EmpresaAnonima()
        .agregarRegistro(new Registro(new LocalDate(2013, 12, 30), 50000, 30000)) //tasa: 60
        .agregarRegistro(new Registro(new LocalDate(2014, 12, 30), 40000, 20000)) //tasa: 50
    )
  }

  "Una estadistica anual" should "saber las ventas por año" in {
    new EstadisticasAnuales(2013).totalVentas should be(120000)
  }

  it should "saber las ventas por anio, sin usar fold" in {
    new EstadisticasAnuales(2013).totalVentasSinFold should be(120000)
  }

  it should "saber las ganancias por anio" in {
    new EstadisticasAnuales(2014).totalGanancias should be(100000)
  }

  it should "saber cuantas empresas superan un monto X de ventas" in {
    new EstadisticasAnuales(2013).empresasConVentasMayoresA(60000) should be (1)
  }

  it should "saber cuantas empresas superan un monto X de tasa de ganancias" in {
    new EstadisticasAnuales(2014).empresasConTasaGananciaMayoresA(30) should be (2)
  }

  after {
    HomeEmpresas.clear()
  }
}
