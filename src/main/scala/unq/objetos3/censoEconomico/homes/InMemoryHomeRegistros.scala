package unq.objetos3.censoEconomico.homes

import unq.objetos3.censoEconomico.domain.Registro

import scala.collection.mutable

object InMemoryHomeRegistros extends HomeRegistros {
  val registros = mutable.Buffer[Registro]()

  override def all = registros.toSeq
  override def clear() = registros.clear()
  override def add(registros: Registro*) = this.registros ++= registros
}
