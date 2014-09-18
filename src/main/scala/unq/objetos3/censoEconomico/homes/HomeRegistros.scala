package unq.objetos3.censoEconomico.homes

import unq.objetos3.censoEconomico.domain.{Registro, Empresa}

trait HomeRegistros {
  def all: Seq[Registro]
  def clear()
  def add(registros: Registro*)

  def deEmpresasConocidas = all.filter(_.empresa.isDefined)
  def de(empresa: Empresa) = all.filter(_.empresa.contains(empresa))
}
