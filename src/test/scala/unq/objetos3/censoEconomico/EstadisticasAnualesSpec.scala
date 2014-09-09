package unq.objetos3.censoEconomico

import org.joda.time.LocalDate

class EstadisticasAnualesSpec extends UnitSpec {
  var camaraIndustriaLitoral: FuenteInformacion = _

  before {
    camaraIndustriaLitoral = "Camara de Industria del Litoral"

    HomeEmpresas.add(
      new Empresa("Sapucay S.A", camaraIndustriaLitoral, new Departamento("Esquina", "Corrientes"))
        .agregarRegistro(new Registro(new LocalDate(2013, 12, 30), 70000, 50000)) //tasa: 71,43
        .agregarRegistro(new Registro(new LocalDate(2014, 12, 30), 100000, 80000)), //tasa: 80

      new EmpresaAnonima(new Departamento("Floresta", "CABA"))
        .agregarRegistro(new Registro(new LocalDate(2013, 12, 30), 50000, 30000)) //tasa: 60
        .agregarRegistro(new Registro(new LocalDate(2014, 12, 30), 40000, 20000)), //tasa: 50

      new EmpresaAnonima(new Departamento("Almagro", "CABA"))
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
    new EstadisticasAnuales(2013).registrosConVentasMayoresA(60000) should be (1)
  }

  it should "saber cuantas empresas superan un monto X de tasa de ganancias" in {
    new EstadisticasAnuales(2014).registrosConTasaGananciaMayoresA(30) should be (2)
  }

  it should "saber la cantidad de ventas por provincia" in {
    HomeEmpresas.add(
      new EmpresaAnonima(new Departamento("Almagro", "CABA"))
      .agregarRegistro(new Registro(new LocalDate(2014, 12, 30), 100000, 100000))
    )

    new EstadisticasAnuales(2014).ventasPorProvincia should be (Map(Provincia("Corrientes") -> 100000, Provincia("CABA") -> 140000))
  }

  it should "saber los nombres de las empresas que supera un monto X de ventas" in {
    HomeEmpresas.add(
      new Empresa("SZnet S.A.", "Gremio del codigo de barras", new Departamento("Villa Luro", "CABA"))
        .agregarRegistro(new Registro(new LocalDate(2014, 12, 30), 160000, 100000))
    )

    new EstadisticasAnuales(2014).nombreEmpresasConVentasMayoresA(100000) should be (Seq("SZnet S.A."))
  }

  it should "saber que fuentes aportaron datos" in {
    val gremioCodigoBarras = new FuenteInformacion("Gremio del codigo de barras")

    HomeEmpresas.add(
      new Empresa("SZnet S.A.", gremioCodigoBarras, new Departamento("Villa Luro", "CABA"))
        .agregarRegistro(new Registro(new LocalDate(2014, 12, 30), 160000, 100000)),

      new Empresa("Netpoint SRL", gremioCodigoBarras, new Departamento("Chacarita", "CABA"))
        .agregarRegistro(new Registro(new LocalDate(2014, 12, 30), 260000, 180000)),

      new Empresa("Willie Dixon Bar", "SADAIC", new Departamento("Rosario", "Santa Fe"))
        .agregarRegistro(new Registro(new LocalDate(2013, 12, 30), 80000, 50000))
    )

    new EstadisticasAnuales(2014).fuentesQueAportaron should be (Seq(camaraIndustriaLitoral, gremioCodigoBarras))
  }

  it should "saber que empresa tuvo mas ganancias" in {
    HomeEmpresas.add(
      new Empresa("Globant", "Union Informatica", new Departamento("Puerto Madero", "CABA"))
        .agregarRegistro(new Registro(new LocalDate(2014, 12, 30), 1600000, 1000000))
    )

    new EstadisticasAnuales(2014).empresaConMasGanancias should be ("Globant")
  }

  after {
    HomeEmpresas.clear()
  }
}
