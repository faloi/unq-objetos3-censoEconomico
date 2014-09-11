package unq.objetos3.censoEconomico

case class FuenteInformacion(nombre: String)

object FuenteInformacion {
  implicit def stringToFuente(value: String) = new FuenteInformacion(value)
}
