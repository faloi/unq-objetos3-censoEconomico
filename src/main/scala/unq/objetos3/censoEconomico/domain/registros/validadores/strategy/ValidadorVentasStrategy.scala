package unq.objetos3.censoEconomico.domain.registros.validadores.strategy

import unq.objetos3.censoEconomico.domain.registros.Registro

object ValidadorVentasStrategy extends ValidadorStrategy {
   override def esConsistente(registro: Registro) = registro.ventas >= 0
}
