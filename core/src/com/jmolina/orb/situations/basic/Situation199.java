package com.jmolina.orb.situations.basic;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.physics.box2d.World;
import com.jmolina.orb.elements.Exit;
import com.jmolina.orb.situations.TopWalls;


public class Situation199 extends TopWalls {

    public Situation199(AssetManager am, World world, float ratioMeterPixel) {
        super(am, world, ratioMeterPixel);
    }

    @Override
    protected void createInnerElements () {
        // Exit
        addElement(new Exit(
                getAssetManager(), getWorld(),
                6, 13,
                getPixelsPerMeter()
        ));

        super.createInnerElements();
    }

}
