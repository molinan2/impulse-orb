package com.jmolina.orb.widgets.ui;

import com.jmolina.orb.managers.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.jmolina.orb.var.Asset;
import com.jmolina.orb.widgets.BaseGroup;

public class GameTitle extends BaseGroup {

    private Image image;

    public GameTitle(AssetManager am) {
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
