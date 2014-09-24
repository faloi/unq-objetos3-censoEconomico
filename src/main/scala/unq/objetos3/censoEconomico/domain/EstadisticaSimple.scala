package unq.objetos3.censoEconomico.domain
import unq.objetos3.censoEconomico.homes.{InMemoryHomeRegistros, HomeRegistros}

trait EstadisticaSimple {
  val homeRegistros: HomeRegistros = InMemoryHomeRegistros
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

  def registrosConTasaGananciaMayoresA(monto: Int) = registros.count(_.tasaGanancias > monto)

  protected def registros = homeRegistros.all.filter(criterio)
  protected def ventas = registros.map(_.ventas)
}
