package com.jmolina.orb.groups;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Disposable;
import com.jmolina.orb.var.Util;

public class ReturningTitleGroup extends com.jmolina.orb.groups.BaseGroup implements Disposable {

    private Image button;
    private Texture buttonTexture;
    private Image title;
    private Texture titleTexture;

    public ReturningTitleGroup() {
        buttonTexture = new Texture(Gdx.files.internal("back.png"));
        titleTexture = new Texture(Gdx.files.internal("title.png"));
        button = new Image(buttonTexture);
        title = new Image(titleTexture);
        button.setPosition(0f, 0f);
        title.setPosition(Util.xGrid(3), 0f);
        addActor(button);
        addActor(title);
    }

    @Override
    public void dispose() {
        buttonTexture.dispose();
        titleTexture.dispose();
    }

    /**
     * Set a new listener for back button. There can be only one
     * @param listener EventListener
     */
    public void setListener(EventListener listener) {
        button.clearListeners();
        button.addListener(listener);
    }

}
