package unq.objetos3.censoEconomico

import org.scalatest.{BeforeAndAfter, FlatSpec, Matchers}
import unq.objetos3.censoEconomico.homes.InMemoryHomeRegistros

trait UnitSpec extends FlatSpec with Matchers with BeforeAndAfter {
  implicit val homeRegistros = InMemoryHomeRegistros

  after {
    homeRegistros.clear()
  }
}
