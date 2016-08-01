package com.jmolina.orb.situations.advanced;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.physics.box2d.World;
import com.jmolina.orb.elements.Exit;
import com.jmolina.orb.elements.Up;
import com.jmolina.orb.situations.TopWalls;


public class Situation299 extends TopWalls {

    public Situation299(AssetManager am, World world, float ratioMeterPixel) {
        super(am, world, ratioMeterPixel);
    }

    @Override
    protected void createInnerElements () {
        addElement(new Up(getAssetManager(), getWorld(), getPixelsPerMeter(),
                6, 2
        ));

        addElement(new Exit(getAssetManager(), getWorld(), getPixelsPerMeter(),
                6, 15
        ));

        super.createInnerElements();
    }

}