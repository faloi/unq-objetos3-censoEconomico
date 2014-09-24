package unq.objetos3.censoEconomico.domain.registros.validadores.strategy

import unq.objetos3.censoEconomico.domain.registros.Registro

object ValidadorFechaStrategy extends ValidadorStrategy {
  override def esConsistente(registro: Registro): Boolean = registro.anioObtencion > registro.fecha.year.get
}
