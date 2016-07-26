package com.jmolina.orb.elements;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.jmolina.orb.var.Asset;

public class Exit extends Element {

    public Exit(AssetManager am, World world, float x, float y, float pixelsPerMeter) {
        super(am, world, am.get(Asset.GAME_EXIT, Texture.class), pixelsPerMeter,
                Geometry.SQUARE, Flavor.BLUE, BodyDef.BodyType.KinematicBody, x, y, 10, 4, 0
        );

        setAsSensor(true);
        setEffect(Effect.EXIT);
    }

}
