package com.jmolina.orb.situations.level1;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.physics.box2d.World;
import com.jmolina.orb.elements.Element;
import com.jmolina.orb.situations.SideWalledSituation;


public class Situation104 extends SideWalledSituation {

    public Situation104(AssetManager am, World world, float ratioMeterPixel) {
        super(am, world, ratioMeterPixel);
    }

    protected void createInnerElements () {
        // Obstacle balls
        addElement(new Element(
                getAssetManager(), getWorld(),
                getPixelsPerMeter(), 4f, 5f, 4, 4, 0,
                Element.Geometry.CIRCLE, Element.Flavor.GREY
        ));

        addElement(new Element(
                getAssetManager(), getWorld(),
                getPixelsPerMeter(), 8f, 12f, 4, 4, 0,
                Element.Geometry.CIRCLE, Element.Flavor.GREY
        ));
    }

}
