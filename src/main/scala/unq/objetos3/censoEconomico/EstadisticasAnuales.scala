package unq.objetos3.censoEconomico

class EstadisticasAnuales(anio: Int) {
  def totalVentas = HomeEmpresas.all().map(_.ventasTotales(anio)).foldLeft(0)(_ + _)
}
