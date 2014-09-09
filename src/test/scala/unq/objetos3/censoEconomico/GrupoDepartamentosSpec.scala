package unq.objetos3.censoEconomico

class GrupoDepartamentosSpec extends UnitSpec {
  "Un grupo de departamentos" should "ser homogeneo si todos los departamentos pertenecen a la misma provincia" in {
    new GrupoDepartamentos(Seq(new Departamento("Flores", "CABA"), new Departamento("Almagro", "CABA")))
      .esHomogeneo should be (true)
  }

  it should "no ser homogeneo si esta compuesto por departamentos de distintas provincias" in {
    new GrupoDepartamentos(Seq(new Departamento("Rosario", "Santa Fe"), new Departamento("Almagro", "CABA")))
      .esHomogeneo should be (false)
  }
}
