package com.jmolina.orb.widgets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.jmolina.orb.assets.Asset;

public class Back extends BaseGroup {

    private Image image;

    public Back(AssetManager am) {
        super(am);

        image = new Image(getAsset(Asset.UI_BACK, Texture.class));
        image.setPosition(0f, 0f);
        addActor(image);
    }

    @Override
    public void dispose() {
        super.dispose();
    }

}
