package unq.objetos3.censoEconomico.domain

import org.joda.time.LocalDate
import unq.objetos3.censoEconomico.UnitSpec
import unq.objetos3.censoEconomico.domain.registros.Registro

class GrupoDepartamentosSpec extends UnitSpec {
  val departamentosCaba = new GrupoDepartamentos(Set(new Departamento("Flores", "CABA"), new Departamento("Almagro", "CABA")))

  "Un grupo de departamentos" should "ser homogeneo si todos los departamentos pertenecen a la misma provincia" in {
    departamentosCaba.esHomogeneo should be (true)
  }

  it should "no ser homogeneo si esta compuesto por departamentos de distintas provincias" in {
    new GrupoDepartamentos(Set(new Departamento("Rosario", "Santa Fe"), new Departamento("Almagro", "CABA")))
      .esHomogeneo should be (false)
  }

  it should "poder decir si incluye una provincia" in {
    departamentosCaba.incluyeA("CABA") should be (true)
  }

  it should "poder decir si no incluye una provincia" in {
    departamentosCaba.incluyeA("Santa Fe") should be (false)
  }

  it should "poder ser usado como estadistica" in {
    Registro(new LocalDate(2013, 12, 30), 70000, 50000, Departamento("Flores", "CABA"))
    Registro(new LocalDate(2014, 12, 30), 100000, 80000, Departamento("Almagro", "CABA"))
    Registro(new LocalDate(2014, 12, 30), 40000, 30000, Departamento("Palermo", "CABA"))

    departamentosCaba.totalVentas should be(170000)
  }
}
