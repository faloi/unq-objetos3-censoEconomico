package unq.objetos3.censoEconomico.domain

case class FuenteInformacion(nombre: String) extends Estadistica {
  override val criterio: (Registro) => Boolean = _.fuente == this
}

object FuenteInformacion {
  implicit def stringToFuente(value: String) = new FuenteInformacion(value)
}
