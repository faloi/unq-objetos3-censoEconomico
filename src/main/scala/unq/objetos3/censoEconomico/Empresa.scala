package unq.objetos3.censoEconomico

class Empresa(val nombre: String, val fuente: FuenteInformacion, val departamento: Departamento) {
  def registros = HomeRegistros.de(this)

  def esSolida = registros.forall(_.esSolido)
  def esSospechosa = registros.exists(_.esSospechoso)
}
