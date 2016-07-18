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
                3.5f, 5, 10, 1, 45,
                Element.Flavor.GREY, Element.Geometry.SQUARE,
                getPixelsPerMeter()
        ));

        // Red stripe
        addElement(new Element(
                getAssetManager(), getWorld(),
                8.5f, 13, 10, 1, -45,
                Element.Flavor.RED, Element.Geometry.SQUARE,
                getPixelsPerMeter()
        ));
    }

}
