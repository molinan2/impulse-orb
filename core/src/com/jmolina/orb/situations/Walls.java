package com.jmolina.orb.situations;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.physics.box2d.World;
import com.jmolina.orb.elements.Element;


public abstract class Walls extends Situation {

    public Walls(AssetManager am, World world, float pixelsPerMeter) {
        super(am, world, pixelsPerMeter);
    }

    protected abstract void createInnerElements();

    protected void createElements () {
        createInnerElements();

        // Muro izquierdo
        addElement(new Element(
                getAssetManager(), getWorld(),
                getPixelsPerMeter(), Element.Geometry.SQUARE, Element.Flavor.BLACK,
                12, 19, -5.5f, 9, 0
        ));

        // Muro derecho
        addElement(new Element(
                getAssetManager(), getWorld(),
                getPixelsPerMeter(), Element.Geometry.SQUARE, Element.Flavor.BLACK,
                12, 19, 17.5f, 9, 0
        ));
    }

}
