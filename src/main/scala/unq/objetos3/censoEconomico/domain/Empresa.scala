package unq.objetos3.censoEconomico.domain

import unq.objetos3.censoEconomico.homes.HomeRegistros

class Empresa(val nombre: String, val fuente: FuenteInformacion, val departamento: Departamento)(implicit homeRegistros: HomeRegistros) {
  def registros = homeRegistros.de(this)

  def esSolida = registros.forall(_.esSolido)
  def esSospechosa = registros.exists(_.esSospechoso)
}
