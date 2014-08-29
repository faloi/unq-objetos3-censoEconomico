package unq.objetos3.censoEconomico

import org.joda.time.LocalDate

class Registro(val fecha: LocalDate, val ventas: Int, val ganancias: Int) {
  val tasaGanancias = (ganancias.toFloat / ventas) * 100
  def esDeAnio(anio: Int): Boolean = fecha.year.get == anio
  def esSolido: Boolean = tasaGanancias > 10
  def esSospechoso: Boolean = tasaGanancias > 85
}
