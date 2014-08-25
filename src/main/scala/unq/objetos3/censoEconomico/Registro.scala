package unq.objetos3.censoEconomico

import org.joda.time.LocalDate

class Registro(val fecha: LocalDate, val ventas: Int, val ganancias: Int) {
  def esDeAnio(anio: Int): Boolean = fecha.year.get == anio
}
