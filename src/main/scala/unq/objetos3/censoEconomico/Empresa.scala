package unq.objetos3.censoEconomico

import scala.collection.mutable

class Empresa() {
  val registros = mutable.Buffer[Registro]()

  def agregarRegistro(registro: Registro) = {
    registros += registro
    this
  }

  def ventasTotales(anio: Int) = registros.find(_.esDeAnio(anio)).get.ventas
}
