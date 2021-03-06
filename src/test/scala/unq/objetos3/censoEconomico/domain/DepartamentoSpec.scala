package unq.objetos3.censoEconomico.domain

import org.joda.time.LocalDate
import unq.objetos3.censoEconomico.UnitSpec
import unq.objetos3.censoEconomico.domain.registros.Registro

class DepartamentoSpec extends UnitSpec {
  val esquinaCorrientes = Departamento("Esquina", "Corrientes")
  val floresta = Departamento("Floresta", "CABA")

  var camaraIndustriaLitoral: FuenteInformacion = _

  before {
    camaraIndustriaLitoral = "Camara de Industria del Litoral"

    val sapucay = new Empresa("Sapucay S.A", camaraIndustriaLitoral, esquinaCorrientes)
    Registro(new LocalDate(2013, 12, 30), 70000, 50000, sapucay)  //tasa: 71,43
    Registro(new LocalDate(2014, 12, 30), 100000, 80000, sapucay) //tasa: 80

    Registro(new LocalDate(2013, 12, 30), 50000, 30000, floresta) //tasa: 60
    Registro(new LocalDate(2014, 12, 30), 40000, 20000, floresta) //tasa: 50
  }

  "Un departamento" should "saber las ventas totales" in {
    esquinaCorrientes.totalVentas should be(170000)
  }

  it should "saber las ventas totales, sin usar fold" in {
    esquinaCorrientes.totalVentasSinFold should be(170000)
  }

  it should "saber las ganancias totales" in {
    esquinaCorrientes.totalGanancias should be(130000)
  }

  it should "saber cuantos registros superan un monto X de ventas" in {
    floresta.registrosConVentasMayoresA(45000) should be (1)
  }

  it should "saber cuantos registros superan un monto X de tasa de ganancias" in {
    esquinaCorrientes.registrosConTasaGananciaMayoresA(80) should be (0)
  }

  it should "saber la cantidad de ventas por provincia" in {
    Registro(new LocalDate(2014, 12, 30), 110000, 100000, floresta)

    floresta.ventasPorProvincia should be (Map(Provincia("CABA") -> 200000))
  }

  it should "saber los nombres de las empresas que superan un monto X de ventas" in {
    val sznet = new Empresa("SZnet S.A.", "Gremio del codigo de barras", floresta)

    Registro(new LocalDate(2013, 12, 30), 160000, 100000, sznet)
    Registro(new LocalDate(2014, 12, 30), 160000, 100000, sznet)

    floresta.nombreEmpresasConVentasMayoresA(100000) should be (Seq("SZnet S.A."))
  }

  it should "saber que fuentes aportaron datos" in {
    val cabb = FuenteInformacion("CABB")
    Registro(new LocalDate(2013, 12, 30), 80000, 50000, new Empresa("Club Pinguinos", cabb, esquinaCorrientes))

    esquinaCorrientes.fuentesQueAportaron should be (Seq(camaraIndustriaLitoral, cabb))
  }

  it should "saber que empresa tuvo mas ganancias" in {
    Registro(new LocalDate(2014, 12, 30), 1600000, 1000000, new Empresa("Globant", "Union Informatica", esquinaCorrientes))

    esquinaCorrientes.empresaConMasGanancias should be ("Globant")
  }
}
