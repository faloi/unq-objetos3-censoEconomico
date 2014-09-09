package unq.objetos3.censoEconomico

class GrupoDepartamentos(val departamentos: Iterable[Departamento]) {
  def incluyeA(provincia: String) = departamentos.exists(_.provincia == provincia)

  def esHomogeneo = departamentos.map(_.provincia).toSet.size == 1
}
