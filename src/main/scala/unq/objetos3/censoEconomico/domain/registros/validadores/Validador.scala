package unq.objetos3.censoEconomico.domain.registros.validadores

trait Validador {
  def esConsistenteMixin: Boolean
}

trait StackableValidador extends Validador {
  def esConsistente: Boolean

  abstract override def esConsistenteMixin = super.esConsistenteMixin && esConsistente
}
