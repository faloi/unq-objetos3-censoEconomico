package unq.objetos3.censoEconomico.domain

import unq.objetos3.censoEconomico.domain.registros.Registro
import unq.objetos3.censoEconomico.homes.{InMemoryHomeRegistros, HomeRegistros}

trait EstadisticaSimple {
  implicit val homeRegistros: HomeRegistros = InMemoryHomeRegistros
  val criterio: (Registro) => Boolean

  def totalVentas = ventas.foldLeft(0)(_ + _)

  def totalVentasSinFold = {
    var acum = 0
    ventas.foreach(acum += _)
    acum
  }

  def totalGanancias = registros.map(_.ganancias).sum

  def registrosConVentasMayoresA(monto: Int) = registrosConXMayoresA(_.ventas, monto)

  def registrosConGananciasMayoresA(monto: Int) = registrosConXMayoresA(_.ganancias, monto)

  def registrosConTasaGananciaMayoresA(monto: Float) = registrosConXMayoresA(_.tasaGanancias, monto)

  protected def registros = homeRegistros.all.filter(criterio)
  protected def ventas = registros.map(_.ventas)

  private def registrosConXMayoresA[A <% Ordered[A]](property: (Registro) => A, monto: A) = registros.count(property(_) > monto)
}
