package unq.objetos3.censoEconomico.domain

import unq.objetos3.censoEconomico.domain.registros.Registro
import unq.objetos3.censoEconomico.utils.IterableExtensions._

class ConjuntoAnios(val anios: Seq[Int], val aggregator: (Iterable[Int]) => Int) extends EstadisticaCompleja {
  override val criterio: (Registro) => Boolean = (registro) => !registro.esAnonimo && anios.exists(registro.esDeAnio)

  override protected def registros: Seq[Registro] =
    super.registros
      .filter(!_.esAnonimo)
      .groupBy(_.empresa.get)
      .map { case (empresa, registros) => makeRegistroCompuesto(empresa, registros) }
      .toSeq

  private def makeRegistroCompuesto(empresa: Empresa, registros: Seq[Registro]) =
    Registro(registros.head.fecha, aggregator(registros.map(_.ventas)), aggregator(registros.map(_.ganancias)), empresa)
}

object ConjuntoAnios {
  def porSuma(anios: Int*) = new ConjuntoAnios(anios, _.sum)
  def porPromedio(anios: Int*) = new ConjuntoAnios(anios, _.average.toInt)
}
