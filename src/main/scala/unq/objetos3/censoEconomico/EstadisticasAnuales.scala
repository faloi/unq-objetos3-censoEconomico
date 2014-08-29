package unq.objetos3.censoEconomico

class EstadisticasAnuales(val anio: Int) {
  def totalVentas = ventasPorAnio.foldLeft(0)(_ + _)

  def totalVentasSinFold = {
    var acum = 0
    ventasPorAnio.foreach(acum += _)
    acum
  }

  def totalGanancias = empresas.map(_.totalGanancias(anio)).foldLeft(0)(_ + _)

  def empresasConVentasMayoresA(monto: Int) = ventasPorAnio.count(_ > monto)
  def empresasConGananciasMayoresA(monto: Int) = ventasPorAnio.count(_ > monto)
  def empresasConTasaGananciaMayoresA(monto: Int) = empresas.map(_.tasaGanancias(anio)).count(_ > monto)

  def ventasPorProvincia =
    empresas
    .groupBy(_.departamento.provincia)
    .mapValues(empresasDeProvincia => empresasDeProvincia.map(_.totalVentas(anio)).sum)

  private def empresas = HomeEmpresas.all
  private def ventasPorAnio = empresas.map(_.totalVentas(anio))
}
