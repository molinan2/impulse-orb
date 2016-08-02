package com.jmolina.orb.situations.god;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.physics.box2d.World;
import com.jmolina.orb.elements.Exit;
import com.jmolina.orb.elements.Movable;
import com.jmolina.orb.elements.Up;
import com.jmolina.orb.elements.WorldElement;
import com.jmolina.orb.situations.TopWalls;


public class Situation599 extends TopWalls {

    public Situation599(AssetManager am, World world, float ratioMeterPixel) {
        super(am, world, ratioMeterPixel);
    }

    @Override
    protected void createInnerElements () {
        Movable destroyer = new Movable(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.TRIANGLE, WorldElement.Flavor.RED,
                4, 4, 3, 10, 0
        );

        destroyer.addRotation(0.4f, false);
        destroyer.addDisplacement(0.16f, 6);

        addElement(destroyer);

        addElement(new Up(getAssetManager(), getWorld(), getPixelsPerMeter(),
                6, 2
        ));

        addElement(new Exit(getAssetManager(), getWorld(), getPixelsPerMeter(),
                6, 15
        ));

        super.createInnerElements();
    }

}
