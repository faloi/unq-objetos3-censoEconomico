package unq.objetos3.censoEconomico.domain.registros.validadores

trait ValidadorVentas extends Validador {
  def ventas: Int
  abstract override def esConsistenteMixin = super.esConsistenteMixin && ventas >= 0
}
