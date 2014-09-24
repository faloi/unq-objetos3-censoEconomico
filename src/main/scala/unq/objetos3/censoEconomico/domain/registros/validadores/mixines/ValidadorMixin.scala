package unq.objetos3.censoEconomico.domain.registros.validadores.mixines

trait ValidadorMixin {
  def esConsistenteMixin: Boolean
}

trait StackableValidador extends ValidadorMixin {
  def esConsistente: Boolean

  abstract override def esConsistenteMixin = super.esConsistenteMixin && esConsistente
}
