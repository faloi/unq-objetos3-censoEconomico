package unq.objetos3.censoEconomico

class EstadisticasAnuales(val anio: Int) {
  def totalVentas = ventasPorAnio.foldLeft(0)(_ + _)

  def totalVentasSinFold = {
    var acum = 0
    ventasPorAnio.foreach(acum += _)
    acum
  }

  def totalGanancias = empresas.map(_.totalGanancias(anio)).foldLeft(0)(_ + _)

  private def empresas = HomeEmpresas.all
  private def ventasPorAnio = empresas.map(_.totalVentas(anio))
}
