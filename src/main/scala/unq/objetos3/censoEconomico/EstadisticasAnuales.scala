package unq.objetos3.censoEconomico

class EstadisticasAnuales(val anioElegido: Int) {
  def totalVentas = ventasPorAnio.foldLeft(0)(_ + _)

  def totalVentasSinFold = {
    var acum = 0
    ventasPorAnio.foreach(acum += _)
    acum
  }

  def totalGanancias = empresas.map(_.totalGananciasSegun(criterio)).foldLeft(0)(_ + _)

  def empresasConVentasMayoresA(monto: Int) = ventasPorAnio.count(_ > monto)
  def empresasConGananciasMayoresA(monto: Int) = ventasPorAnio.count(_ > monto)
  def empresasConTasaGananciaMayoresA(monto: Int) = empresas.map(_.tasaGananciasSegun(criterio)).count(_ > monto)

  def ventasPorProvincia =
    empresas
    .groupBy(_.departamento.provincia)
    .mapValues(empresasDeProvincia => empresasDeProvincia.map(_.totalVentasSegun(criterio)).sum)

  def nombreEmpresasConVentasMayoresA(monto: Int) = empresasConocidas.filter(_.totalVentasSegun(criterio) > monto).map(_.nombre)

  def fuentesQueAportaron = empresasConocidas.filter(_.registraActividadSegun(criterio)).map(_.fuente).distinct

  def empresaConMasGanancias = empresasConocidas.maxBy(_.totalGananciasSegun(criterio)).nombre

  private def empresas = HomeEmpresas.all
  private def empresasConocidas = HomeEmpresas.conocidas
  private def ventasPorAnio = empresas.map(_.totalVentasSegun(criterio))

  private def criterio = (registro: Registro) => registro.esDeAnio(anioElegido)
}
