package unq.objetos3.censoEconomico

import scala.collection.mutable

object HomeEmpresas {
  val empresas = mutable.Buffer[Empresa]()

  def all = empresas.toSeq
  def add(empresas: Empresa*) = this.empresas ++= empresas
  def clear() = empresas.clear()
}
