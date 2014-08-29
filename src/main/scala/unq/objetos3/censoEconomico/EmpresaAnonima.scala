package unq.objetos3.censoEconomico

import scala.collection.mutable

class EmpresaAnonima(val departamento: Departamento) {
  val registros = mutable.Buffer[Registro]()

  def agregarRegistro(registro: Registro) = {
    registros += registro
    this
  }

  def totalVentas(anio: Int) = registroDe(anio).ventas
  def totalGanancias(anio: Int) = registroDe(anio).ganancias
  def tasaGanancias(anio: Int) = registroDe(anio).tasaGanancias

  private def registroDe(anio: Int) = registros.find(_.esDeAnio(anio)).get
}
