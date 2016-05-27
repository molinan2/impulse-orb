package com.jmolina.orb.widgets;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.jmolina.orb.groups.BaseGroup;
import com.jmolina.orb.var.Var;

public class MainButtonWidget extends BaseGroup {

    private Image button;

    public MainButtonWidget(Texture texture) {
        button = new Image(texture);
        button.setPosition(0f, 0f);
        addActor(button);
        setHeight(1.5f * Var.GRID_UNIT);
        setOrigin(texture.getWidth() * 0.5f, texture.getHeight() * 0.5f);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
    }

}
