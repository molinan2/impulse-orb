# Anotaciones #

### Creación de .jar ejecutable ###

Desde la línea de comandos, moverse a la carpeta del proyecto y ejecutar (en Windows):

```
gradlew desktop:dist
```

El ejecutable colgará de `desktop\build\libs\`. Ejemplo de salida:

```
c:\ProgramsDev\cygwin64\home\Juanma\orb2>gradlew desktop:dist
Starting a new Gradle Daemon for this build (subsequent builds will be faster).
Configuration on demand is an incubating feature.
:core:compileJava UP-TO-DATE
:core:processResources UP-TO-DATE
:core:classes UP-TO-DATE
:core:jar UP-TO-DATE
:desktop:compileJava UP-TO-DATE
:desktop:processResources UP-TO-DATE
:desktop:classes UP-TO-DATE
:desktop:dist

BUILD SUCCESSFUL

Total time: 7.378 secs
```

### Eventos drag y click en actores ###

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

### Box2D ###

[Existe una velocidad máxima](http://www.iforce2d.net/b2dtut/gotchas). Hay que ajustar las medidas del entorno para trabajar en metros. Si utilizamos las dimensiones naturales del dispositivo, Box2D tiende a simular objetos enormes moviéndose a velocidades también enormes, lo que incurre en limitaciones de simulación y físicas.