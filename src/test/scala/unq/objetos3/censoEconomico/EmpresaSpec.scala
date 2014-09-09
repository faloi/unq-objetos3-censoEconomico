package unq.objetos3.censoEconomico

import org.joda.time.LocalDate

class EmpresaSpec extends UnitSpec {
  "Una empresa" should "ser solida si todos sus registros tienen tasa > a 10" in {
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

  it should "ser sospechosa si al menos un registro tiene tasa > a 85" in {
    new Empresa("Papel Prensa", "Clarin", new Departamento("La Plata", "Buenos Aires"))
      .agregarRegistro(new Registro(new LocalDate(2012, 12, 30), 70000, 60200))  //tasa: 86
      .agregarRegistro(new Registro(new LocalDate(2013, 12, 30), 70000, 50000))  //tasa: 71
      .esSospechosa should be (true)
  }

  it should "no ser sospechosa si ningun registro tiene tasa > a 85" in {
    new EmpresaAnonima(new Departamento("La Plata", "Buenos Aires"))
      .agregarRegistro(new Registro(new LocalDate(2012, 12, 30), 70000, 7000))  //tasa: 10
      .agregarRegistro(new Registro(new LocalDate(2013, 12, 30), 70000, 50000))  //tasa: 71
      .esSospechosa should be (false)
  }
}
