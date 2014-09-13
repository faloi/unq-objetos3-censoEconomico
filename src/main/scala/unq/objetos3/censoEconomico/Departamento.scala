package unq.objetos3.censoEconomico

case class Departamento(nombre: String, provincia: Provincia) extends Estadistica {
  override val criterio: (Registro) => Boolean = _.departamento.equals(this)
}
