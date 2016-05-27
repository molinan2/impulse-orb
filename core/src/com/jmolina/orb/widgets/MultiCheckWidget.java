package com.jmolina.orb.widgets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Disposable;
import com.jmolina.orb.groups.BaseGroup;
import com.jmolina.orb.var.Var;

public class MultiCheckWidget extends BaseGroup implements Disposable {

    private Image text;
    private Image multicheck;
    private Texture multiCheckTexture;

    public MultiCheckWidget(Texture textTexture) {
        multiCheckTexture = new Texture(Gdx.files.internal("multicheck.png"));

        text = new Image(textTexture);
        multicheck = new Image(multiCheckTexture);

        text.setPosition(0f, 0f);
        multicheck.setPosition(5.5f * Var.GRID_UNIT, 0f);
        addActor(text);
        addActor(multicheck);
        setHeight(1.5f * Var.GRID_UNIT);
    }

    @Override
    public void dispose() {
        multiCheckTexture.dispose();
    }
}
