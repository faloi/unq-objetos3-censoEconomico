package unq.objetos3.censoEconomico.domain

class Anio(val anioElegido: Int) extends EstadisticaCompleja {
  override val criterio = (registro: Registro) => registro.esDeAnio(anioElegido)
}
