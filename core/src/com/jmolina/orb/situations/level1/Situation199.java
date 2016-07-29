package com.jmolina.orb.situations.level1;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.physics.box2d.World;
import com.jmolina.orb.elements.Element;
import com.jmolina.orb.elements.Exit;
import com.jmolina.orb.situations.SideWallSituation;


public class Situation199 extends SideWallSituation {

    public Situation199(AssetManager am, World world, float ratioMeterPixel) {
        super(am, world, ratioMeterPixel);
    }

    protected void createInnerElements () {
        // Exit
        addElement(new Exit(
                getAssetManager(), getWorld(),
                6, 13,
                getPixelsPerMeter()
        ));

        // Top walls
        addElement(new Element(
                getAssetManager(), getWorld(),
                getPixelsPerMeter(), Element.Geometry.SQUARE, Element.Flavor.BLACK, 6, 27, 12, 18, 0
        ));

        addElement(new Element(
                getAssetManager(), getWorld(),
                getPixelsPerMeter(), Element.Geometry.SQUARE, Element.Flavor.BLACK, -6 + 0.5f, 27, 12, 18, 0
        ));

        addElement(new Element(
                getAssetManager(), getWorld(),
                getPixelsPerMeter(), Element.Geometry.SQUARE, Element.Flavor.BLACK, 18 - 0.5f, 27, 12, 18, 0
        ));
    }

}
