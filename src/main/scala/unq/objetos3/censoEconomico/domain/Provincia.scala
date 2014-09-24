package unq.objetos3.censoEconomico.domain

case class Provincia(nombre: String) extends EstadisticaCompleja {
  override val criterio = (registro: Registro) => registro.esDeProvincia(this)
}

object Provincia {
  implicit def stringToProvincia(value: String) = new Provincia(value)
}
