package com.jmolina.orb.widgets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.jmolina.orb.groups.BaseGroup;
import com.jmolina.orb.var.Var;

public class StatWidget extends BaseGroup {

    private Image name;
    private Image number;
    private Texture numberTexture;

    public StatWidget(Texture nameTexture) {
        name = new Image(nameTexture);

        numberTexture = new Texture(Gdx.files.internal("stats_number.png"));
        number = new Image(numberTexture);

        name.setPosition(0f, 0f);
        number.setPosition(8.0f * Var.GRID_UNIT, 0f);
        addActor(name);
        addActor(number);
        setHeight(1.0f * Var.GRID_UNIT);
    }

}