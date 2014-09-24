package unq.objetos3.censoEconomico.domain.registros.validadores

import org.joda.time.LocalDate

trait ValidadorFecha extends Validador {
  def fecha: LocalDate
  def anioObtencion: Int

  override def esConsistenteMixin: Boolean = anioObtencion > fecha.year.get
}
