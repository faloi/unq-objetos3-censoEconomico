package unq.objetos3.censoEconomico.utils

object IterableExtensions {
  implicit class ExtendedIterable[A](val iterable: Iterable[A]) {
    def average(implicit num: Numeric[A]) = num.toDouble(iterable.sum) / iterable.size
  }
}
