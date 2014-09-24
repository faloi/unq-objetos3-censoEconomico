package unq.objetos3.censoEconomico.domain.registros.validadores.mixines

trait ValidadorVentasMixin extends StackableValidador {
  def ventas: Int

  override def esConsistente = ventas >= 0
}
