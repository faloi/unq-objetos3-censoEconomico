package unq.objetos3.censoEconomico

trait Estadistica {
  val criterio: (Registro) => Boolean

  def totalVentas = ventasSegunCriterio.foldLeft(0)(_ + _)

  def totalVentasSinFold = {
    var acum = 0
    ventasSegunCriterio.foreach(acum += _)
    acum
  }

  def totalGanancias = empresas.map(_.totalGananciasSegun(criterio)).foldLeft(0)(_ + _)

  def registrosConVentasMayoresA(monto: Int) = ventasSegunCriterio.count(_ > monto)

  def empresasConGananciasMayoresA(monto: Int) = ventasSegunCriterio.count(_ > monto)

  def registrosConTasaGananciaMayoresA(monto: Int) = empresas.map(_.tasaGananciasSegun(criterio)).count(_ > monto)

  def ventasPorProvincia =
    empresas
      .groupBy(_.departamento.provincia)
      .mapValues(empresasDeProvincia => empresasDeProvincia.map(_.totalVentasSegun(criterio)).sum)

  def nombreEmpresasConVentasMayoresA(monto: Int) = empresasConocidas.filter(_.totalVentasSegun(criterio) > monto).map(_.nombre)

  def fuentesQueAportaron = empresasConocidas.filter(_.registraActividadSegun(criterio)).map(_.fuente).distinct

  def empresaConMasGanancias = empresasConocidas.maxBy(_.totalGananciasSegun(criterio)).nombre

  private def empresas = HomeEmpresas.all
  private def empresasConocidas = HomeEmpresas.conocidas
  private def ventasSegunCriterio = empresas.map(_.totalVentasSegun(criterio))
}
