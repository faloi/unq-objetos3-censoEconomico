package unq.objetos3.censoEconomico.domain

class EstadisticasAnuales(val anioElegido: Int) extends Estadistica {
  override val criterio = (registro: Registro) => registro.esDeAnio(anioElegido)
}
