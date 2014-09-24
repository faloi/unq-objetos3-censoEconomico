package unq.objetos3.censoEconomico.domain

import unq.objetos3.censoEconomico.domain.registros.Registro

class GrupoDepartamentos(val departamentos: Set[Departamento]) extends EstadisticaCompleja {
  def incluyeA(provincia: Provincia) = departamentos.exists(_.provincia == provincia)

  def esHomogeneo = departamentos.map(_.provincia).toSet.size == 1

  override val criterio: (Registro) => Boolean = departamentos contains _.departamento
}
