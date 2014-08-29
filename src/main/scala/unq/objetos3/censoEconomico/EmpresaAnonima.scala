package unq.objetos3.censoEconomico

import scala.collection.mutable

class EmpresaAnonima(val departamento: Departamento) {
  val registros = mutable.Buffer[Registro]()

  def agregarRegistro(registro: Registro) = {
    registros += registro
    this
  }

  def totalVentas(anio: Int) = atributoDe(anio, _.ventas, 0)
  def totalGanancias(anio: Int) = atributoDe(anio, _.ganancias, 0)
  def tasaGanancias(anio: Int) = atributoDe(anio, _.tasaGanancias, 0f)

  def registraActividadEn(anio: Int) = registroDe(anio).isDefined

  private def atributoDe[A](anio: Int, propiedad: (Registro) => A, default: A) = registroDe(anio).map(propiedad).getOrElse(default)
  private def registroDe(anio: Int) = registros.find(_.esDeAnio(anio))
}
