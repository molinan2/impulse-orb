package com.jmolina.orb.situations.level1;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.physics.box2d.World;
import com.jmolina.orb.elements.BaseElement;
import com.jmolina.orb.situations.SideWallSituation;


public class Situation107 extends SideWallSituation {

    public Situation107(AssetManager am, World world, float ratioMeterPixel) {
        super(am, world, ratioMeterPixel);
    }

    protected void createInnerElements () {
        // Grey stripe
        addElement(new BaseElement(
                getAssetManager(), getWorld(),
                getPixelsPerMeter(), BaseElement.Geometry.SQUARE, BaseElement.Flavor.GREY, 3.5f, 5, 10, 1, 45
        ));

        // Red stripe
        addElement(new BaseElement(
                getAssetManager(), getWorld(),
                getPixelsPerMeter(), BaseElement.Geometry.SQUARE, BaseElement.Flavor.RED, 8.5f, 13, 10, 1, -45
        ));
    }

}
