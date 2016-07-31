package com.jmolina.orb.situations;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.physics.box2d.World;
import com.jmolina.orb.elements.Element;


public class BottomWalls extends Walls {

    public BottomWalls(AssetManager am, World world, float pixelsPerMeter) {
        super(am, world, pixelsPerMeter);
    }

    protected void createInnerElements () {
        // Bottom walls
        addElement(new Element(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                Element.Geometry.SQUARE, Element.Flavor.BLACK,
                12, 18, 6, -9 + 0.5f, 0
        ));

        addElement(new Element(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                Element.Geometry.SQUARE, Element.Flavor.BLACK,
                12, 18, -6 + 0.5f, -9 + 0.5f, 0
        ));

        addElement(new Element(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                Element.Geometry.SQUARE, Element.Flavor.BLACK,
                12, 18, 18 - 0.5f, -9 + 0.5f, 0
        ));
    }

}
