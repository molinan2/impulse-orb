package com.jmolina.orb.elements;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.World;
import com.jmolina.orb.var.Asset;

public class Exit extends Element {

    public Exit(AssetManager am, World world, float pixelsPerMeter, float x, float y) {
        super(am, world, am.get(Asset.GAME_EXIT, Texture.class), pixelsPerMeter,
                Geometry.SQUARE, Flavor.BLUE,
                10, 4, x, y, 0
        );

        setAsSensor(true);
        setEffect(Effect.EXIT);
    }

}
