package com.jmolina.orb.situations.level1;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.physics.box2d.World;
import com.jmolina.orb.elements.Element;
import com.jmolina.orb.situations.SideWalledSituation;


public class Situation106 extends SideWalledSituation {

    public Situation106(AssetManager am, World world, float ratioMeterPixel) {
        super(am, world, ratioMeterPixel);
    }

    protected void createInnerElements () {
        // Red boxes
        addElement(new Element(
                getAssetManager(), getWorld(),
                0, 7, 7, 7, 45,
                Element.Flavor.RED, Element.Geometry.SQUARE,
                getRatioMeterPixel()
        ));

        addElement(new Element(
                getAssetManager(), getWorld(),
                12, 12, 7, 7, 45,
                Element.Flavor.RED, Element.Geometry.SQUARE,
                getRatioMeterPixel()
        ));
    }

}
