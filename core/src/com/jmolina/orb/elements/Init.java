package com.jmolina.orb.elements;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.World;
import com.jmolina.orb.assets.Asset;

public class Init extends Element {

    public Init(AssetManager am, World world, float x, float y) {
        super(am, world, x, y, 10, 4, 0, Type.BLUE);
        setActorTexture(am.get(Asset.GAME_INIT, Texture.class));
        getBody().getFixtureList().first().setSensor(true);
        setUserData(Type.BLUE, Effect.NONE);
    }

}