package unq.objetos3.censoEconomico

class GrupoDepartamentos(val departamentos: Iterable[Departamento]) {
  def esHomogeneo = departamentos.map(_.provincia).toSet.size == 1
}
