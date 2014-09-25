package unq.objetos3.censoEconomico.domain

case class ConjuntoAniosSuma(anios: Int*) extends EstadisticaConjuntoAnios {
  override def aggregate(values: Iterable[Int]): Int = values.sum
}
