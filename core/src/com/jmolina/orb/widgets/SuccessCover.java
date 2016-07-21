package com.jmolina.orb.widgets;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.jmolina.orb.utils.Utils;

public class SuccessCover extends BaseGroup {

    private Image greeting;

    public SuccessCover(AssetManager am, Texture greetingTexture) {
        super(am);

        greeting = new Image(greetingTexture);
        addActor(greeting);
        setSize(Utils.cell(10), Utils.cell(4));
    }

    @Override
    public void dispose() {
        super.dispose();
    }

}
