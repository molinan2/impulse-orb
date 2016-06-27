package com.jmolina.orb.situations;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.physics.box2d.World;
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
public class Situation100 extends Situation {

    public Situation100(AssetManager am, World world, float worldGridUnit) {
        super(am, world, worldGridUnit);
    }

    protected void createElements () {
        Box.BoxConfig boxConfig = new Box.BoxConfig(getAssetManager(), getWorld(), getPixelsPerMeter());

        // Bottom wall
        boxConfig.setPosition(6, -9 + 0.5f);
        boxConfig.setSize(12, 18);
        boxConfig.rotation = 0;
        boxConfig.type = Element.Type.BLACK;
        addElement(new Box(boxConfig));

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

        // Left+Right bottom walls
        boxConfig.setPosition(-6 + 0.5f, -9 + 0.5f);
        boxConfig.setSize(12, 18);
        boxConfig.rotation = 0;
        boxConfig.type = Element.Type.BLACK;
        addElement(new Box(boxConfig));

        boxConfig.setPosition(18 - 0.5f, -9 + 0.5f);
        boxConfig.setSize(12, 18);
        boxConfig.rotation = 0;
        boxConfig.type = Element.Type.BLACK;
        addElement(new Box(boxConfig));
    }

}
