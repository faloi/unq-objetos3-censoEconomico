package unq.objetos3.censoEconomico

import scala.collection.mutable

object HomeEmpresas {
  val empresas = mutable.Buffer[EmpresaAnonima]()

  def all = empresas.toSeq
  def conocidas = all.filter(_.isInstanceOf[Empresa]).map(_.asInstanceOf[Empresa])
  def add(empresas: EmpresaAnonima*) = this.empresas ++= empresas
  def clear() = empresas.clear()
}
