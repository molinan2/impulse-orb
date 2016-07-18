package com.jmolina.orb.elements;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.World;
import com.jmolina.orb.assets.Asset;

public class Exit extends Element {

    public Exit(AssetManager am, World world, float x, float y, float ratioMeterPixel) {
        super(am, world, x, y, 10, 4, 0, Flavor.BLUE, ratioMeterPixel);
        setTexture(getAsset(Asset.GAME_EXIT, Texture.class));
        setAsSensor(true);
        setUserData(Flavor.BLUE, Effect.EXIT);
    }

}
