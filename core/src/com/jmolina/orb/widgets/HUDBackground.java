package com.jmolina.orb.widgets;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.jmolina.orb.assets.Asset;
import com.jmolina.orb.utils.Grid;

public class HUDBackground extends BaseGroup {

    private Image image;

    public HUDBackground(AssetManager am) {
        super(am);

        image = new Image(getAsset(Asset.HUD_BACKGROUND, Texture.class));
        image.setPosition(0, 0);

        addActor(image);

        setHeight(Grid.unit(2.5f));
        setOrigin(image.getWidth() * 0.5f, image.getHeight() * 0.5f);
    }

}
