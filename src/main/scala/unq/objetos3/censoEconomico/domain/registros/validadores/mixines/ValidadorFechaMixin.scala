package unq.objetos3.censoEconomico.domain.registros.validadores.mixines

import org.joda.time.LocalDate

trait ValidadorFechaMixin extends StackableValidador {
  def fecha: LocalDate
  def anioObtencion: Int

  override def esConsistente: Boolean = anioObtencion > fecha.year.get
}
