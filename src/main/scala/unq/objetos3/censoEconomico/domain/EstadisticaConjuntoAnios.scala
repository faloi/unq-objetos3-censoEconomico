package unq.objetos3.censoEconomico.domain

import unq.objetos3.censoEconomico.domain.registros.Registro

trait EstadisticaConjuntoAnios extends EstadisticaCompleja {
  def anios: Seq[Int]
  def aggregate(values: Iterable[Int]): Int

  override val criterio: (Registro) => Boolean = (registro) => !registro.esAnonimo && anios.exists(registro.esDeAnio)

  override protected def registros: Seq[Registro] =
    super.registros
      .filter(!_.esAnonimo)
      .groupBy(_.empresa.get)
      .map { case (empresa, registros) => makeRegistroCompuesto(empresa, registros) }
      .toSeq

  private def makeRegistroCompuesto(empresa: Empresa, registros: Seq[Registro]) =
    Registro(registros.head.fecha, aggregate(registros.map(_.ventas)), aggregate(registros.map(_.ganancias)), empresa)
}
