package com.jmolina.orb.situations.level1;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.physics.box2d.World;
import com.jmolina.orb.elements.BaseElement;
import com.jmolina.orb.situations.SideWalledSituation;


public class Situation106 extends SideWalledSituation {

    public Situation106(AssetManager am, World world, float ratioMeterPixel) {
        super(am, world, ratioMeterPixel);
    }

    protected void createInnerElements () {
        // Red boxes
        addElement(new BaseElement(
                getAssetManager(), getWorld(),
                getPixelsPerMeter(), BaseElement.Geometry.SQUARE, BaseElement.Flavor.RED, 0, 7, 7, 7, 45
        ));

        addElement(new BaseElement(
                getAssetManager(), getWorld(),
                getPixelsPerMeter(), BaseElement.Geometry.SQUARE, BaseElement.Flavor.RED, 12, 12, 7, 7, 45
        ));
    }

}
