package com.jmolina.orb.situations;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.physics.box2d.World;
import com.jmolina.orb.elements.Ball;
import com.jmolina.orb.elements.Box;
import com.jmolina.orb.elements.Element;


/**
 * Una lista de elementos creados y configurados, posicionados respecto a (0,0)
 *
 * Inicialmente, todas de 18 unidades de alto y 12 de ancho. Mas adelante:
 *
 * - Que carguen de JSON
 * - Que guarde su altura y se consulte cuando se vayan a apilar las situaciones
 * - Que guarde su anchura e idem (mas complicado)
 */
public class Situation104 extends Situation {

    public Situation104(AssetManager am, World world, float worldGridUnit) {
        super(am, world, worldGridUnit);
    }

    protected void createElements () {
        Ball.BallConfig ballConfig = new Ball.BallConfig(getAssetManager(), getWorld(), getPixelsPerMeter());
        Box.BoxConfig boxConfig = new Box.BoxConfig(getAssetManager(), getWorld(), getPixelsPerMeter());

        // Bolas
        ballConfig.setPosition(4, 5f);
        ballConfig.setDiameter(4);
        ballConfig.rotation = 0;
        ballConfig.type = Element.Type.GREY;
        addElement(new Ball(ballConfig));

        ballConfig.setPosition(8, 12f);
        ballConfig.setDiameter(4);
        ballConfig.rotation = 0;
        ballConfig.type = Element.Type.GREY;
        addElement(new Ball(ballConfig));


        // Left+Right walls
        boxConfig.setPosition(-6 + 0.5f, 9);
        boxConfig.setSize(12, 18);
        boxConfig.rotation = 0;
        boxConfig.type = Element.Type.BLACK;
        addElement(new Box(boxConfig));

        boxConfig.setPosition(12 + 6 - 0.5f, 9);
        boxConfig.setSize(12, 18);
        boxConfig.rotation = 0;
        boxConfig.type = Element.Type.BLACK;
        addElement(new Box(boxConfig));
    }

}
