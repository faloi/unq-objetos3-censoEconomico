package unq.objetos3.censoEconomico

class GrupoDepartamentos(val departamentos: Iterable[Departamento]) {
  def incluyeA(provincia: Provincia) = departamentos.exists(_.provincia == provincia)

  def esHomogeneo = departamentos.map(_.provincia).toSet.size == 1
}
