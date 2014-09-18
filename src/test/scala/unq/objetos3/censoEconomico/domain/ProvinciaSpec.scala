package unq.objetos3.censoEconomico.domain

import org.joda.time.LocalDate
import unq.objetos3.censoEconomico.UnitSpec

class ProvinciaSpec extends UnitSpec {
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

  "Una provincia" should "saber las ventas totales" in {
    Provincia("CABA").totalVentas should be(90000)
  }

  it should "saber las ventas totales, sin usar fold" in {
    Provincia("CABA").totalVentasSinFold should be(90000)
  }

  it should "saber las ganancias totales" in {
    Provincia("Corrientes").totalGanancias should be(130000)
  }

  it should "saber cuantos registros superan un monto X de ventas" in {
    Provincia("CABA").registrosConVentasMayoresA(45000) should be (1)
  }

  it should "saber cuantos registros superan un monto X de tasa de ganancias" in {
    Provincia("Corrientes").registrosConTasaGananciaMayoresA(75) should be (1)
  }

  it should "saber la cantidad de ventas por provincia" in {
    Registro(new LocalDate(2014, 12, 30), 100000, 100000, new Departamento("Almagro", "CABA"))

    Provincia("CABA").ventasPorProvincia should be (Map(Provincia("CABA") -> 190000))
  }

  it should "saber los nombres de las empresas que supera un monto X de ventas" in {
    val sznet = new Empresa("SZnet S.A.", "Gremio del codigo de barras", new Departamento("Villa Luro", "CABA"))

    Registro(new LocalDate(2013, 12, 30), 160000, 100000, sznet)
    Registro(new LocalDate(2014, 12, 30), 160000, 100000, sznet)

    Provincia("CABA").nombreEmpresasConVentasMayoresA(100000) should be (Seq("SZnet S.A."))
  }

  it should "saber que fuentes aportaron datos" in {
    val cabb = FuenteInformacion("CABB")
    Registro(new LocalDate(2013, 12, 30), 80000, 50000,
      new Empresa("Club Pinguinos", cabb, new Departamento("Corrientes", "Corrientes")))

    Provincia("Corrientes").fuentesQueAportaron should be (Seq(camaraIndustriaLitoral, cabb))
  }

  it should "saber que empresa tuvo mas ganancias" in {
    Registro(new LocalDate(2014, 12, 30), 1600000, 1000000,
      new Empresa("Globant", "Union Informatica", new Departamento("Puerto Madero", "CABA")))

    Provincia("CABA").empresaConMasGanancias should be ("Globant")
  }
}
