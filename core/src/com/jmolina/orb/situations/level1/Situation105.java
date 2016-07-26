package com.jmolina.orb.situations.level1;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.physics.box2d.World;
import com.jmolina.orb.elements.Element;
import com.jmolina.orb.situations.SideWalledSituation;


public class Situation105 extends SideWalledSituation {

    public Situation105(AssetManager am, World world, float ratioMeterPixel) {
        super(am, world, ratioMeterPixel);
    }

    protected void createInnerElements () {
        // Center box obstacle with red borders
        addElement(new Element(
                getAssetManager(), getWorld(),
                getPixelsPerMeter(), 6, 10, 4, 4, 45,
                Element.Geometry.SQUARE, Element.Flavor.GREY
        ));

        addElement(new Element(
                getAssetManager(), getWorld(),
                getPixelsPerMeter(), 7.5f, 11.5f, 4, 0.5f, -45,
                Element.Geometry.SQUARE, Element.Flavor.RED
        ));

        addElement(new Element(
                getAssetManager(), getWorld(),
                getPixelsPerMeter(), 4.5f, 8.5f, 4, 0.5f, -45,
                Element.Geometry.SQUARE, Element.Flavor.RED
        ));
    }

}
