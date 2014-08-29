package unq.objetos3.censoEconomico

import scala.collection.mutable

class EmpresaAnonima(val departamento: Departamento) {
  val registros = mutable.Buffer[Registro]()

  def agregarRegistro(registro: Registro) = {
    registros += registro
    this
  }

  def totalVentas(anio: Int) = registroDe(anio).map(_.ventas).getOrElse(0)
  def totalGanancias(anio: Int) = registroDe(anio).map(_.ganancias).getOrElse(0)
  def tasaGanancias(anio: Int) = registroDe(anio).map(_.tasaGanancias).getOrElse(0f)

  def registraActividadEn(anio: Int): Boolean = registroDe(anio).isDefined

  private def registroDe(anio: Int) = registros.find(_.esDeAnio(anio))
}
