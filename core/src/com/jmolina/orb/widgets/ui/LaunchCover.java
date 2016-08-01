package com.jmolina.orb.widgets.ui;

import com.jmolina.orb.managers.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.jmolina.orb.utils.Utils;
import com.jmolina.orb.widgets.BaseGroup;

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
