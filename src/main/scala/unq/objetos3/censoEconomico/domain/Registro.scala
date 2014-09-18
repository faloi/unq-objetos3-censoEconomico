package unq.objetos3.censoEconomico.domain

import org.joda.time.LocalDate
import unq.objetos3.censoEconomico.homes.HomeRegistros

case class Registro(fecha: LocalDate, ventas: Int, ganancias: Int, departamento: Departamento,
                    fuente: FuenteInformacion = new FuenteInformacion("Anonima"), empresa: Option[Empresa] = None)(implicit homeRegistros: HomeRegistros) {
  val tasaGanancias = (ganancias.toFloat / ventas) * 100

  def esDeAnio(anio: Int): Boolean = fecha.year.get == anio
  def esSolido: Boolean = tasaGanancias > 10
  def esSospechoso: Boolean = tasaGanancias > 85
  def esDeProvincia(provincia: Provincia) = departamento.provincia == provincia

  homeRegistros.add(this)
}

object Registro {
  def apply(fecha: LocalDate, ventas: Int, ganancias: Int, empresa: Empresa)(implicit home: HomeRegistros) =
    new Registro(fecha, ventas, ganancias, empresa.departamento, empresa.fuente, Some(empresa))
}
