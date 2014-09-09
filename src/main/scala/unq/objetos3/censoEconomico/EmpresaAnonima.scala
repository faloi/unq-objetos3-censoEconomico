package unq.objetos3.censoEconomico

import scala.collection.mutable

class EmpresaAnonima(val departamento: Departamento) {
  val registros = mutable.Buffer[Registro]()

  def agregarRegistro(registro: Registro) = {
    registros += registro
    this
  }

  def totalVentasSegun(criterio: (Registro) => Boolean) = atributoDe(criterio, _.ventas, 0)
  def totalGananciasSegun(criterio: (Registro) => Boolean) = atributoDe(criterio, _.ganancias, 0)
  def tasaGananciasSegun(criterio: (Registro) => Boolean) = atributoDe(criterio, _.tasaGanancias, 0f)
  def registraActividadSegun(criterio: (Registro) => Boolean) = registroDe(criterio).isDefined

  def esSolida = registros.forall(_.esSolido)
  def esSospechosa = registros.exists(_.esSospechoso)

  private def atributoDe[A](criterio: (Registro) => Boolean, propiedad: (Registro) => A, default: A) =
    registroDe(criterio).map(propiedad).getOrElse(default)

  private def registroDe(criterio: (Registro) => Boolean) = registros.find(criterio)
}
