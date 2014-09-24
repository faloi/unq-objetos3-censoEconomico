package unq.objetos3.censoEconomico.domain

import unq.objetos3.censoEconomico.homes.HomeRegistros

case class Empresa(val nombre: String, val fuente: FuenteInformacion, val departamento: Departamento)
             (implicit homeRegistros: HomeRegistros) extends EstadisticaSimple {
  def esSolida = registros.forall(_.esSolido)
  def esSospechosa = registros.exists(_.esSospechoso)

  override val criterio: (Registro) => Boolean = _.empresa.contains(this)
}
