package unq.objetos3.censoEconomico

import scala.collection.mutable

class Empresa() {
  val registros = mutable.Buffer[Registro]()

  def agregarRegistro(registro: Registro) = {
    registros += registro
    this
  }

  def totalVentas(anio: Int) = registroDe(anio).ventas

  def totalGanancias(anio: Int) = registroDe(anio).ganancias

  private def registroDe(anio: Int) = registros.find(_.esDeAnio(anio)).get
}
