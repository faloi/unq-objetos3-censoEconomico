package unq.objetos3.censoEconomico.domain

import unq.objetos3.censoEconomico.domain.registros.Registro

case class FuenteInformacion(nombre: String, provinciasDondeOpera: Provincia*) extends EstadisticaCompleja {
  def operaEn(departamento: Departamento): Boolean = provinciasDondeOpera.contains(departamento.provincia)

  override val criterio: (Registro) => Boolean = _.fuente == this
}

object FuenteInformacion {
  implicit def stringToFuente(value: String) = new FuenteInformacion(value)
}
