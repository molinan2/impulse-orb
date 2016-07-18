package com.jmolina.orb.elements;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.World;
import com.jmolina.orb.assets.Asset;

public class Init extends Element {

    public Init(AssetManager am, World world, float x, float y, float ratioMeterPixel) {
        super(am, world, x, y, 10, 4, 0, Flavor.BLUE, ratioMeterPixel);
        setTexture(getAsset(Asset.GAME_INIT, Texture.class));
        setAsSensor(true);
        setUserData(Flavor.BLUE, Effect.NONE);
    }

}
