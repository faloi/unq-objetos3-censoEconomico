package unq.objetos3.censoEconomico.domain

import unq.objetos3.censoEconomico.domain.registros.Registro

class Anio(val anioElegido: Int) extends EstadisticaCompleja {
  override val criterio = (registro: Registro) => registro.esDeAnio(anioElegido)
}
