# README #

En lugar de explicar los pasos necesarios para instalar y ejecutar mi aplicación, voy a ir anotando por ahora las curiosidades que me estoy encontrando.

### Sobre eventos drag y click en actores ###

Es preferible utilizar DragListener antes que InputListener, ya que tiene métodos más específicos. DragListener ofrece más control al disponer de drag() y dragStart(). He tenido problemas para recolocar la posición del actor al usar touchDragged tanto en DragListener como en InputListener.

He encontrado problemas al modificar la posición de un actor directamente con setPosition, en un evento de tipo drag. El movimiento es errático si el actor tiene aplicada una rotación con Actor.rotateBy(). Cuanta más rotación existe, mayor es la desviación.

```
#!java

triangle.setPosition(triangle.getX() + x, triangle.getY() + y);
```

La alternativa es utilizar la posición de la Stage y guardar un offset, para luego restablecerlo. Esto permite coger un actor por cualquiera de sus puntos y arrastrarlo sin que ocurra un desplazamiento adicional.

```
#!java

@Override
public void dragStart(InputEvent event, float x, float y, int pointer) {
    super.dragStart(event, x, y, pointer);

    this.offsetX = event.getStageX() - event.getTarget().getX();
    this.offsetY = event.getStageY() - event.getTarget().getY();
}

@Override
public void drag(InputEvent event, float x, float y, int pointer) {
    super.drag(event, x, y, pointer);
    event.getTarget().setPosition(event.getStageX() - offsetX, event.getStageY() - offsetY);
    // NO USAR: Provoca movimiento erratico si el actor tiene aplicada rotacion
    // triangle.setPosition(triangle.getX() + x, triangle.getY() + y);
}
```

### Particularidades de Box2D ###

[Existe una velocidad máxima](http://www.iforce2d.net/b2dtut/gotchas). Hay que ajustar las medidas del entorno para trabajar en metros. Si utilizamos las dimensiones naturales del dispositivo, Box2D tiende a simular objetos enormes moviéndose a velocidades también enormes, lo que incurre en limitaciones de simulación y físicas.