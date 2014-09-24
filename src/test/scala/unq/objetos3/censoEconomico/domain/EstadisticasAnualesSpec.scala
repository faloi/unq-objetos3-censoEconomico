package unq.objetos3.censoEconomico.domain

import org.joda.time.LocalDate
import unq.objetos3.censoEconomico.UnitSpec

class EstadisticasAnualesSpec extends UnitSpec {
  var camaraIndustriaLitoral: FuenteInformacion = _

  before {
    camaraIndustriaLitoral = "Camara de Industria del Litoral"

    val sapucay = new Empresa("Sapucay S.A", camaraIndustriaLitoral, new Departamento("Esquina", "Corrientes"))
    Registro(new LocalDate(2013, 12, 30), 70000, 50000, sapucay)  //tasa: 71,43
    Registro(new LocalDate(2014, 12, 30), 100000, 80000, sapucay) //tasa: 80

    val floresta = new Departamento("Floresta", "CABA")
    Registro(new LocalDate(2013, 12, 30), 50000, 30000, floresta) //tasa: 60
    Registro(new LocalDate(2014, 12, 30), 40000, 20000, floresta) //tasa: 50
  }

  "Una estadistica anual" should "saber las ventas por año" in {
    new Anio(2013).totalVentas should be(120000)
  }

  it should "saber las ventas por anio, sin usar fold" in {
    new Anio(2013).totalVentasSinFold should be(120000)
  }

  it should "saber las ganancias por anio" in {
    new Anio(2014).totalGanancias should be(100000)
  }

  it should "saber cuantas empresas superan un monto X de ventas" in {
    new Anio(2013).registrosConVentasMayoresA(60000) should be (1)
  }

  it should "saber cuantas empresas superan un monto X de tasa de ganancias" in {
    new Anio(2014).registrosConTasaGananciaMayoresA(30) should be (2)
  }

  it should "saber la cantidad de ventas por provincia" in {
    Registro(new LocalDate(2014, 12, 30), 100000, 100000, new Departamento("Almagro", "CABA"))

    new Anio(2014).ventasPorProvincia should be (Map(Provincia("Corrientes") -> 100000, Provincia("CABA") -> 140000))
  }

  it should "saber los nombres de las empresas que supera un monto X de ventas" in {
    Registro(new LocalDate(2014, 12, 30), 160000, 100000,
      new Empresa("SZnet S.A.", "Gremio del codigo de barras", new Departamento("Villa Luro", "CABA")))

    new Anio(2014).nombreEmpresasConVentasMayoresA(100000) should be (Seq("SZnet S.A."))
  }

  it should "saber que fuentes aportaron datos" in {
    val gremioCodigoBarras = new FuenteInformacion("Gremio del codigo de barras")

    Registro(new LocalDate(2014, 12, 30), 160000, 100000,
      new Empresa("SZnet S.A.", gremioCodigoBarras, new Departamento("Villa Luro", "CABA")))

    Registro(new LocalDate(2014, 12, 30), 260000, 180000,
      new Empresa("Netpoint SRL", gremioCodigoBarras, new Departamento("Chacarita", "CABA")))

    Registro(new LocalDate(2013, 12, 30), 80000, 50000,
      new Empresa("Willie Dixon Bar", "SADAIC", new Departamento("Rosario", "Santa Fe")))

    new Anio(2014).fuentesQueAportaron should be (Seq(camaraIndustriaLitoral, gremioCodigoBarras))
  }

  it should "saber que empresa tuvo mas ganancias" in {
    Registro(new LocalDate(2014, 12, 30), 1600000, 1000000,
      new Empresa("Globant", "Union Informatica", new Departamento("Puerto Madero", "CABA")))

    new Anio(2014).empresaConMasGanancias should be ("Globant")
  }
}
