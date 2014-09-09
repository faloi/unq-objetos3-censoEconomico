package unq.objetos3.censoEconomico

case class Provincia(nombre: String)

object Provincia {
  implicit def stringToProvincia(value: String) = new Provincia(value)
}
