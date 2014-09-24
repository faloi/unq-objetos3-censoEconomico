package unq.objetos3.censoEconomico.domain.registros.validadores

import unq.objetos3.censoEconomico.domain.{Departamento, FuenteInformacion}

trait ValidadorProvincia extends StackableValidador {
  def fuente: FuenteInformacion
  def departamento: Departamento
  def esAnonimo: Boolean

  override def esConsistente = !esAnonimo || fuente.operaEn(departamento)
}
