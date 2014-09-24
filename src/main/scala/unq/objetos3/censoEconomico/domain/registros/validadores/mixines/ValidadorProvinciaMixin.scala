package unq.objetos3.censoEconomico.domain.registros.validadores.mixines

import unq.objetos3.censoEconomico.domain.{Departamento, FuenteInformacion}

trait ValidadorProvinciaMixin extends StackableValidador {
  def fuente: FuenteInformacion
  def departamento: Departamento
  def esAnonimo: Boolean

  override def esConsistente = !esAnonimo || fuente.operaEn(departamento)
}
