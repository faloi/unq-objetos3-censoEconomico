package unq.objetos3.censoEconomico

class FuenteInformacion(val nombre: String)

object FuenteInformacion {
  implicit def stringToFuente(value: String) = new FuenteInformacion(value)
}
