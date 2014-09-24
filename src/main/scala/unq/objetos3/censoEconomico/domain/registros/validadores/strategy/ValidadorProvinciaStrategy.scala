package unq.objetos3.censoEconomico.domain.registros.validadores.strategy

import unq.objetos3.censoEconomico.domain.registros.Registro

object ValidadorProvinciaStrategy extends ValidadorStrategy {
   override def esConsistente(registro: Registro) = !registro.esAnonimo || registro.fuente.operaEn(registro.departamento)
}
