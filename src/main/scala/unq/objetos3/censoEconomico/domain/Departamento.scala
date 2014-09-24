package unq.objetos3.censoEconomico.domain

import unq.objetos3.censoEconomico.domain.registros.Registro

case class Departamento(nombre: String, provincia: Provincia) extends EstadisticaCompleja {
  override val criterio: (Registro) => Boolean = _.departamento.equals(this)
}
