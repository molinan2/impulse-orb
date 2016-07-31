package com.jmolina.orb.situations;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.physics.box2d.World;
import com.jmolina.orb.elements.Element;


public class TopWalls extends Walls {

    public TopWalls(AssetManager am, World world, float ratioMeterPixel) {
        super(am, world, ratioMeterPixel);
    }

    protected void createInnerElements () {
        // Top walls
        addElement(new Element(
                getAssetManager(), getWorld(),
                getPixelsPerMeter(), Element.Geometry.SQUARE, Element.Flavor.BLACK, 12, 18, 6, 27, 0
        ));

        addElement(new Element(
                getAssetManager(), getWorld(),
                getPixelsPerMeter(), Element.Geometry.SQUARE, Element.Flavor.BLACK, 12, 18, -6 + 0.5f, 27, 0
        ));

        addElement(new Element(
                getAssetManager(), getWorld(),
                getPixelsPerMeter(), Element.Geometry.SQUARE, Element.Flavor.BLACK, 12, 18, 18 - 0.5f, 27, 0
        ));
    }

}
