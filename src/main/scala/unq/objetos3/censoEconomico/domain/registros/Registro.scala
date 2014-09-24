package unq.objetos3.censoEconomico.domain.registros

import org.joda.time.LocalDate
import unq.objetos3.censoEconomico.domain.registros.validadores.Validador
import unq.objetos3.censoEconomico.domain.{Departamento, Empresa, FuenteInformacion, Provincia}
import unq.objetos3.censoEconomico.homes.HomeRegistros

case class Registro(
    fecha: LocalDate = LocalDate.now,
    ventas: Int = 0,
    ganancias: Int = 0,
    departamento: Departamento = Departamento("Desconocido", "Desconocido"),
    fuente: FuenteInformacion = new FuenteInformacion("Anonima"),
    anioObtencion: Int = LocalDate.now.year.get + 1,
    empresa: Option[Empresa] = None
  )(implicit homeRegistros: HomeRegistros) extends Validador {

  val tasaGanancias = (ganancias.toFloat / ventas) * 100

  def esDeAnio(anio: Int): Boolean = fecha.year.get == anio
  def esSolido: Boolean = tasaGanancias > 10
  def esSospechoso: Boolean = tasaGanancias > 85
  def esDeProvincia(provincia: Provincia) = departamento.provincia == provincia

  homeRegistros.add(this)

  override def esConsistenteMixin: Boolean = true
}

object Registro {
  def apply(fecha: LocalDate, ventas: Int, ganancias: Int, empresa: Empresa)(implicit home: HomeRegistros) =
    new Registro(fecha, ventas, ganancias, empresa.departamento, empresa.fuente, empresa = Some(empresa))
}
