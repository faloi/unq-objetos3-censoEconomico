package unq.objetos3.censoEconomico.domain.registros.validadores.strategy

import unq.objetos3.censoEconomico.domain.registros.Registro

trait ValidadorStrategy {
  def esConsistente(registro: Registro): Boolean
}
