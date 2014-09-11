package unq.objetos3.censoEconomico

import scala.collection.mutable

object HomeRegistros {
  val registros = mutable.Buffer[Registro]()

  def all = registros.toSeq
  def deEmpresasConocidas = all.filter(_.empresa.isDefined)
  def de(empresa: Empresa) = all.filter(_.empresa.contains(empresa))
  def add(registros: Registro*) = this.registros ++= registros
  def clear() = registros.clear()
}
