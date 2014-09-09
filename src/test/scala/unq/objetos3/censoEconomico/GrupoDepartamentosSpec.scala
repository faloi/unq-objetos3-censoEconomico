package unq.objetos3.censoEconomico

class GrupoDepartamentosSpec extends UnitSpec {
  val departamentosCaba = new GrupoDepartamentos(Seq(new Departamento("Flores", "CABA"), new Departamento("Almagro", "CABA")))

  "Un grupo de departamentos" should "ser homogeneo si todos los departamentos pertenecen a la misma provincia" in {
    departamentosCaba.esHomogeneo should be (true)
  }

  it should "no ser homogeneo si esta compuesto por departamentos de distintas provincias" in {
    new GrupoDepartamentos(Seq(new Departamento("Rosario", "Santa Fe"), new Departamento("Almagro", "CABA")))
      .esHomogeneo should be (false)
  }

  it should "poder decir si incluye una provincia" in {
    departamentosCaba.incluyeA("CABA") should be (true)
  }

  it should "poder decir si no incluye una provincia" in {
    departamentosCaba.incluyeA("Santa Fe") should be (false)
  }
}
