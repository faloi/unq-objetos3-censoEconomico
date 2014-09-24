package unq.objetos3.censoEconomico.domain.registros.validadores

trait ValidadorVentas extends StackableValidador {
  def ventas: Int

  override def esConsistente = ventas >= 0
}
