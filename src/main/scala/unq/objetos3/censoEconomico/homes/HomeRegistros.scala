package unq.objetos3.censoEconomico.homes

import unq.objetos3.censoEconomico.domain.registros.Registro

trait HomeRegistros {
  def all: Seq[Registro]
  def clear()
  def add(registros: Registro*)

  def deEmpresasConocidas = all.filter(_.empresa.isDefined)
}
