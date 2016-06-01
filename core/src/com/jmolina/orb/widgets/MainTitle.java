package com.jmolina.orb.widgets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Disposable;
import com.jmolina.orb.var.Asset;

public class MainTitle extends BaseWidget {

    private Image image;

    public MainTitle(AssetManager am) {
        super(am);

        image = new Image(getAsset(Asset.UI_MAIN_TITLE, Texture.class));
        image.setPosition(0f, 0f);
        addActor(image);
    }

    @Override
    public void dispose() {
        super.dispose();
    }

}
