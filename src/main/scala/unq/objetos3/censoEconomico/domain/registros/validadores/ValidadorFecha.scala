package unq.objetos3.censoEconomico.domain.registros.validadores

import org.joda.time.LocalDate

trait ValidadorFecha extends StackableValidador {
  def fecha: LocalDate
  def anioObtencion: Int

  override def esConsistente: Boolean = anioObtencion > fecha.year.get
}
