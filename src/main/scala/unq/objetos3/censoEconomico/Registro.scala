package unq.objetos3.censoEconomico

import org.joda.time.LocalDate

case class Registro(fecha: LocalDate, ventas: Int, ganancias: Int, departamento: Departamento,
                    fuente: FuenteInformacion = new FuenteInformacion("Anonima"), empresa: Option[Empresa] = None) {
  val tasaGanancias = (ganancias.toFloat / ventas) * 100

  def esDeAnio(anio: Int): Boolean = fecha.year.get == anio
  def esSolido: Boolean = tasaGanancias > 10
  def esSospechoso: Boolean = tasaGanancias > 85
  def esDeProvincia(provincia: Provincia) = departamento.provincia == provincia

  HomeRegistros.add(this)
}

object Registro {
  def apply(fecha: LocalDate, ventas: Int, ganancias: Int, empresa: Empresa) =
    new Registro(fecha, ventas, ganancias, empresa.departamento, empresa.fuente, Some(empresa))
}
