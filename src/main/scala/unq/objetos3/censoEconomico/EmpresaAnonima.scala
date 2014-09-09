package unq.objetos3.censoEconomico

import scala.collection.mutable

class EmpresaAnonima(val departamento: Departamento) {
  val registros = mutable.Buffer[Registro]()

  def agregarRegistro(registro: Registro) = {
    registro.empresa = this
    registros += registro
    this
  }

  def totalVentasSegun(criterio: (Registro) => Boolean) = atributoDe(criterio, _.ventas)
  def totalGananciasSegun(criterio: (Registro) => Boolean) = atributoDe(criterio, _.ganancias)
  def tasaGananciasSegun(criterio: (Registro) => Boolean) = atributoDe(criterio, _.tasaGanancias)
  def registraActividadSegun(criterio: (Registro) => Boolean) = registrosSegun(criterio).nonEmpty

  def esSolida = registros.forall(_.esSolido)
  def esSospechosa = registros.exists(_.esSospechoso)

  private def atributoDe[A : Numeric](criterio: (Registro) => Boolean, propiedad: (Registro) => A) =
    registrosSegun(criterio).map(propiedad).sum

  private def registrosSegun(criterio: (Registro) => Boolean) = registros.filter(criterio)
}
