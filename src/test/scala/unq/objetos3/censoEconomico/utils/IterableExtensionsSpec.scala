package unq.objetos3.censoEconomico.utils

import unq.objetos3.censoEconomico.UnitSpec
import unq.objetos3.censoEconomico.utils.IterableExtensions._

class IterableExtensionsSpec extends UnitSpec {
  "Un iterable extendido" should "poder calcular el promedio" in {
    Seq(1, 2, 3).average should be(2)
  }
}
