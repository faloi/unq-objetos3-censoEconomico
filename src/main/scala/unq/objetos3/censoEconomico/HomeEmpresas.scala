package unq.objetos3.censoEconomico

import scala.collection.mutable

object HomeEmpresas {
  val empresas = mutable.Buffer[Empresa]()

  def agregar(empresas: Empresa*) = this.empresas ++= empresas
  def all() = empresas
}
