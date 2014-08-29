package unq.objetos3.censoEconomico

import org.joda.time.LocalDate

class EmpresaSpec extends UnitSpec {
  "Una empresa" should "saber sus ventas por año" in {
    new EmpresaAnonima(new Departamento("Esquina", "Corrientes"))
      .agregarRegistro(new Registro(new LocalDate(2013, 12, 30), 70000, 50000))
      .agregarRegistro(new Registro(new LocalDate(2014, 12, 30), 100000, 80000))
      .totalVentas(2013) should be(70000)
  }

  it should "ser solida si todos sus registros tienen tasa > a 10" in {
    new EmpresaAnonima(new Departamento("La Plata", "Buenos Aires"))
      .agregarRegistro(new Registro(new LocalDate(2012, 12, 30), 70000, 7100))  //tasa: 10.1
      .agregarRegistro(new Registro(new LocalDate(2013, 12, 30), 70000, 50000))  //tasa: 71
      .agregarRegistro(new Registro(new LocalDate(2014, 12, 30), 100000, 80000)) //tasa: 80
      .esSolida should be (true)
  }

  it should "no ser solida si alguno de sus registros tiene tasa <= a 10" in {
    new EmpresaAnonima(new Departamento("La Plata", "Buenos Aires"))
      .agregarRegistro(new Registro(new LocalDate(2012, 12, 30), 70000, 7000))  //tasa: 10
      .agregarRegistro(new Registro(new LocalDate(2013, 12, 30), 70000, 50000))  //tasa: 71
      .esSolida should be (false)
  }
}
