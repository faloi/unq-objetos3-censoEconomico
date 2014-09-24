unq-objetos3-censoEconomico
===========================

Se desea informatizar el procesamiento de información generada por un censo económico permanente de la Argentina. La información es recabada por distintas fuentes. Para más información, [ver el enunciado](https://docs.google.com/viewer?a=v&pid=sites&srcid=ZGVmYXVsdGRvbWFpbnxwcm9ncmFtYWNpb25obXxneDo0NmNmMWI5NjU0N2MyODFk).

### Comparativa entre las distintas soluciones para Registro.esConsistente

**Estrategia (?):** la _solución basada en mixines_ soluciona el problema jugando con el _method lookup_, mientras que la otra resuelve simplemente _delegando_ en objetos polimórficos. Sin embargo, en cierta forma ambos approachs son similares en tanto existe un contrato que deben cumplir todos los validadores

**Acoplamiento entre los validadores y el registro:** la _solución basada en mixines_ presenta un menor acoplamiento, ya que desde el `Registro` no es posible saber si fue "decorado" o no. En cambio con los _strategies_ el control del flujo está en el `Registro`, ya que es él quien se encarga de computar el valor final (en definitiva alguien tiene que hacer el forall)

**Facilidad para agregar/quitar validaciones dinámicamente:** si bien la especificación no dice nada al respecto, podría ser interesante agregar o quitar validadores en tiempo de ejecución. Con la _solución basada en mixines_ esto es imposible, ya que estos se incorporan sí o sí en tiempo de compilación, mientras que en la otra solución simplemente habría que agregar/quitar un validador a la lista
