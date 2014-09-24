package unq.objetos3.censoEconomico.domain.registros

import unq.objetos3.censoEconomico.JasmineSpec
import unq.objetos3.censoEconomico.domain.registros.validadores.ValidadorVentas
import unq.objetos3.censoEconomico.homes.InMemoryHomeRegistros

class RegistroSpec extends JasmineSpec {
  implicit val homeRegistros = InMemoryHomeRegistros

  describe("Un registro") {
    afterEach {
      homeRegistros.clear()
    }

    describe("es consistente si") {
      it("sus ventas son positivas") {
        (new Registro(ventas = 500) with ValidadorVentas).esConsistenteMixin should be (true)
      }
    }

    describe("no es consistente si") {
      it("sus ventas son negativas") {
        (new Registro(ventas = -500) with ValidadorVentas).esConsistenteMixin should be (false)
      }
    }
  }

}
