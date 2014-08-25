package unq.objetos3.censoEconomico

class EstadisticasAnuales(val anio: Int) {
  private def ventasPorAnio = HomeEmpresas.all.map(_.ventasTotales(anio))

  def totalVentas = ventasPorAnio.foldLeft(0)(_ + _)

  def totalVentasSinFold = {
    var acum = 0
    ventasPorAnio.foreach(acum += _)
    acum
  }
}
