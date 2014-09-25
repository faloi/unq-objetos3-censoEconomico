package unq.objetos3.censoEconomico.domain

import unq.objetos3.censoEconomico.domain.registros.Registro

case class ConjuntoAniosSuma(anios: Int*) extends EstadisticaCompleja {
  override val criterio: (Registro) => Boolean = (registro) => !registro.esAnonimo && anios.exists(registro.esDeAnio)

  override protected def registros: Seq[Registro] =
    super.registros
      .filter(!_.esAnonimo)
      .groupBy(_.empresa.get)
      .map { case (empresa, registros) => makeRegistroCompuesto(empresa, registros) }
      .toSeq

  private def makeRegistroCompuesto(empresa: Empresa, registros: Seq[Registro]) =
    Registro(registros.head.fecha, registros.map(_.ventas).sum, registros.map(_.ganancias).sum, empresa)
}
