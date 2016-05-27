package com.jmolina.orb.widgets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Disposable;
import com.jmolina.orb.groups.BaseGroup;
import com.jmolina.orb.var.Var;

public class CheckWidget extends BaseGroup implements Disposable {

    private Image text;
    private Image check;
    private Texture checkTexture;

    public CheckWidget(Texture textTexture) {
        checkTexture = new Texture(Gdx.files.internal("check.png"));

        text = new Image(textTexture);
        check = new Image(checkTexture);

        text.setPosition(0f, 0f);
        check.setPosition(8.5f * Var.GRID_UNIT, 0f);
        addActor(text);
        addActor(check);
        setHeight(1.5f * Var.GRID_UNIT);
    }

    @Override
    public void dispose() {
        checkTexture.dispose();
    }
}
