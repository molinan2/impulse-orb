package com.jmolina.orb.groups;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Disposable;

public class MainTitleGroup extends com.jmolina.orb.groups.BaseGroup implements Disposable {

    private Image image;
    private Texture imageTexture;

    public MainTitleGroup() {
        imageTexture = new Texture(Gdx.files.internal("maintitle.png"));
        image = new Image(imageTexture);
        image.setPosition(0f, 0f);
        addActor(image);
    }

    @Override
    public void dispose() {
        imageTexture.dispose();
    }

}
