package com.jmolina.orb.widgets;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.jmolina.orb.utils.Utils;

public class LaunchCover extends BaseGroup {

    private Image cover;

    public LaunchCover(AssetManager am, Texture coverTexture) {
        super(am);

        cover = new Image(coverTexture);
        addActor(cover);
        setHeight(Utils.cell(5));
    }

    @Override
    public void dispose() {
        super.dispose();
    }

}
