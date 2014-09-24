package unq.objetos3.censoEconomico.domain

import org.joda.time.LocalDate
import unq.objetos3.censoEconomico.UnitSpec
import unq.objetos3.censoEconomico.domain.registros.Registro

class EmpresaSpec extends UnitSpec {
  var unaEmpresa: Empresa = _

  before {
    unaEmpresa = new Empresa("Arcor", "Golosinas", new Departamento("La Plata", "Buenos Aires"))
  }

  "Una empresa" should "ser solida si todos sus registros tienen tasa > a 10" in {
    Registro(new LocalDate(2012, 12, 30), 70000, 7100, unaEmpresa)   //tasa: 10.1
    Registro(new LocalDate(2013, 12, 30), 70000, 50000, unaEmpresa)  //tasa: 71
    Registro(new LocalDate(2014, 12, 30), 100000, 80000, unaEmpresa) //tasa: 80

    unaEmpresa.esSolida should be (true)
  }

  it should "no ser solida si alguno de sus registros tiene tasa <= a 10" in {
    Registro(new LocalDate(2012, 12, 30), 70000, 7000, unaEmpresa)  //tasa: 10
    Registro(new LocalDate(2013, 12, 30), 70000, 50000, unaEmpresa) //tasa: 71

    unaEmpresa.esSolida should be (false)
  }

  it should "ser sospechosa si al menos un registro tiene tasa > a 85" in {
    Registro(new LocalDate(2012, 12, 30), 70000, 60200, unaEmpresa) //tasa: 86
    Registro(new LocalDate(2013, 12, 30), 70000, 50000, unaEmpresa) //tasa: 71

    unaEmpresa.esSospechosa should be (true)
  }

  it should "no ser sospechosa si ningun registro tiene tasa > a 85" in {
    Registro(new LocalDate(2012, 12, 30), 70000, 7000, unaEmpresa)  //tasa: 10
    Registro(new LocalDate(2013, 12, 30), 70000, 50000, unaEmpresa) //tasa: 71

    unaEmpresa.esSospechosa should be (false)
  }

  it should "saber calcular el total de sus ventas" in {
    Registro(new LocalDate(2012, 12, 30), 70000, 7000, unaEmpresa)
    Registro(new LocalDate(2012, 12, 30), 70000, 7000,
      Empresa("Molinos Rio de la Plata", FuenteInformacion("Alimenticia"), Departamento("San Fernando", "Buenos Aires")))

    unaEmpresa.totalVentas should be (70000)
  }
}
