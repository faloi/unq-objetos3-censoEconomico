package unq.objetos3.censoEconomico.domain

trait EstadisticaCompleja extends EstadisticaSimple {
  def ventasPorProvincia =
    registros
      .groupBy(_.departamento.provincia)
      .mapValues(empresasDeProvincia => empresasDeProvincia.map(_.ventas).sum)

  def nombreEmpresasConVentasMayoresA(monto: Int) =
    registrosDeEmpresasConocidas.filter(_.ventas > monto).map(_.empresa.get.nombre).distinct

  def fuentesQueAportaron = registrosDeEmpresasConocidas.map(_.fuente).distinct

  def empresaConMasGanancias = registrosDeEmpresasConocidas.maxBy(_.ganancias).empresa.get.nombre

  protected def registrosDeEmpresasConocidas = registros.filter(!_.esAnonimo)
}
