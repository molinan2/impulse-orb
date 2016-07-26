package com.jmolina.orb.situations.level1;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.physics.box2d.World;
import com.jmolina.orb.elements.Element;
import com.jmolina.orb.situations.SideWalledSituation;


public class Situation107 extends SideWalledSituation {

    public Situation107(AssetManager am, World world, float ratioMeterPixel) {
        super(am, world, ratioMeterPixel);
    }

    protected void createInnerElements () {
        // Grey stripe
        addElement(new Element(
                getAssetManager(), getWorld(),
                getPixelsPerMeter(), 3.5f, 5, 10, 1, 45,
                Element.Geometry.SQUARE, Element.Flavor.GREY
        ));

        // Red stripe
        addElement(new Element(
                getAssetManager(), getWorld(),
                getPixelsPerMeter(), 8.5f, 13, 10, 1, -45,
                Element.Geometry.SQUARE, Element.Flavor.RED
        ));
    }

}
