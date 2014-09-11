package unq.objetos3.censoEconomico

case class Provincia(nombre: String) extends Estadistica {
  override val criterio = (registro: Registro) => registro.esDeProvincia(this)
}

object Provincia {
  implicit def stringToProvincia(value: String) = new Provincia(value)
}
