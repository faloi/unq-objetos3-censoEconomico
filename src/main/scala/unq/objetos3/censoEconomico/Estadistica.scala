package unq.objetos3.censoEconomico

trait Estadistica {
  val criterio: (Registro) => Boolean

  def totalVentas = ventas.foldLeft(0)(_ + _)

  def totalVentasSinFold = {
    var acum = 0
    ventas.foreach(acum += _)
    acum
  }

  def totalGanancias = registros.map(_.ganancias).sum

  def registrosConVentasMayoresA(monto: Int) = ventas.count(_ > monto)

  def empresasConGananciasMayoresA(monto: Int) = ventas.count(_ > monto)

  def registrosConTasaGananciaMayoresA(monto: Int) = registros.map(_.tasaGanancias).count(_ > monto)

  def ventasPorProvincia =
    registros
      .groupBy(_.departamento.provincia)
      .mapValues(empresasDeProvincia => empresasDeProvincia.map(_.ventas).sum)

  def nombreEmpresasConVentasMayoresA(monto: Int) =
    registrosDeEmpresasConocidas.filter(_.ventas > monto).map(_.empresa.get.nombre).distinct

  def fuentesQueAportaron = registrosDeEmpresasConocidas.map(_.fuente).distinct

  def empresaConMasGanancias = registrosDeEmpresasConocidas.maxBy(_.ganancias).empresa.get.nombre

  private def registros = HomeRegistros.all.filter(criterio)
  private def registrosDeEmpresasConocidas = HomeRegistros.deEmpresasConocidas.filter(criterio)
  private def ventas = registros.map(_.ventas)
}
