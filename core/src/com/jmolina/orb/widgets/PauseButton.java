package com.jmolina.orb.widgets;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.jmolina.orb.assets.Asset;
import com.jmolina.orb.utils.Grid;

public class PauseButton extends BaseGroup {

    private Image image;

    public PauseButton(AssetManager am) {
        super(am);

        image = new Image(getAsset(Asset.HUD_PAUSE, Texture.class));
        image.setPosition(0f, 0f);

        addActor(image);

        setHeight(Grid.unit(1.5f));
        setOrigin(image.getWidth() * 0.5f, image.getHeight() * 0.5f);
    }

}
