package com.jmolina.orb.elements;

import com.jmolina.orb.managers.AssetManager;
import com.badlogic.gdx.physics.box2d.World;
import com.jmolina.orb.var.Atlas;

public class Exit extends Element {

    public Exit(AssetManager am, World world, float pixelsPerMeter, float x, float y) {
        super(am, world, am.getGameAtlas().findRegion(Atlas.GAME_EXIT), pixelsPerMeter,
                Geometry.SQUARE, Flavor.BLUE,
                10, 4, x, y, 0
        );

        setAsSensor(true);
        setEffect(Effect.EXIT);
    }

}
