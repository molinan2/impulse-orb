package com.jmolina.orb.situations;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.physics.box2d.World;
import com.jmolina.orb.elements.BaseElement;


public abstract class SideWalledSituation extends Situation {

    public SideWalledSituation(AssetManager am, World world, float pixelsPerMeter) {
        super(am, world, pixelsPerMeter);
    }

    protected abstract void createInnerElements();

    protected void createElements () {
        createInnerElements();

        // Side walls
        addElement(new BaseElement(
                getAssetManager(), getWorld(),
                getPixelsPerMeter(), BaseElement.Geometry.SQUARE, BaseElement.Flavor.BLACK,
                -6 + 0.5f, 9, 12, 18, 0
        ));

        addElement(new BaseElement(
                getAssetManager(), getWorld(),
                getPixelsPerMeter(), BaseElement.Geometry.SQUARE, BaseElement.Flavor.BLACK,
                12 + 6 - 0.5f, 9, 12, 18, 0
        ));
    }

}
