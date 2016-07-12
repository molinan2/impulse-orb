package com.jmolina.orb.elements;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.jmolina.orb.assets.Asset;

public class Exit extends Element {

    public Exit(AssetManager am, World world, float x, float y, float ratioMeterPixel) {
        super(am, world, x, y, 10, 4, 0, Type.BLUE, ratioMeterPixel);
        setActorTexture(am.get(Asset.GAME_EXIT, Texture.class));
        getBody().getFixtureList().first().setSensor(true);
        setUserData(Type.BLUE, Effect.EXIT);
    }

}
