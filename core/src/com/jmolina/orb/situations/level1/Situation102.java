package com.jmolina.orb.situations.level1;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.physics.box2d.World;
import com.jmolina.orb.elements.Element;
import com.jmolina.orb.situations.SideWalledSituation;

import static com.jmolina.orb.elements.Element.*;


public class Situation102 extends SideWalledSituation {

    public Situation102(AssetManager am, World world, float ratioMeterPixel) {
        super(am, world, ratioMeterPixel);
    }

    protected void createInnerElements () {
        // Lateral boxes
        addElement(new Element(
                getAssetManager(), getWorld(),
                getPixelsPerMeter(), 0, 7, 7, 7, 45,
                Geometry.SQUARE, Flavor.GREY
        ));

        addElement(new Element(
                getAssetManager(), getWorld(),
                getPixelsPerMeter(), 2.5f, 9.5f, 7, 0.5f, -45,
                Geometry.SQUARE, Flavor.RED
        ));

        addElement(new Element(
                getAssetManager(), getWorld(),
                getPixelsPerMeter(), 12, 12, 7, 7, -45,
                Geometry.SQUARE, Flavor.GREY
        ));

        addElement(new Element(
                getAssetManager(), getWorld(),
                getPixelsPerMeter(), 9.5f, 14.5f, 7, 0.5f, 45,
                Geometry.SQUARE, Flavor.RED
        ));
    }

}
