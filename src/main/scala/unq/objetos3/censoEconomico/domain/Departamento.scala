package unq.objetos3.censoEconomico.domain

case class Departamento(nombre: String, provincia: Provincia) extends EstadisticaCompleja {
  override val criterio: (Registro) => Boolean = _.departamento.equals(this)
}
