package unq.objetos3.censoEconomico.domain

import unq.objetos3.censoEconomico.utils.IterableExtensions._

case class ConjuntoAniosPromedio(anios: Int*) extends EstadisticaConjuntoAnios {
  override def aggregate(values: Iterable[Int]): Int = values.average.toInt
}
