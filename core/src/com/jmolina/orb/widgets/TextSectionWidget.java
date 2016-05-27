package com.jmolina.orb.widgets;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.jmolina.orb.groups.BaseGroup;
import com.jmolina.orb.var.Var;

public class TextSectionWidget extends BaseGroup {

    private Image header;
    private Image body;

    public TextSectionWidget(Texture headerTexture, Texture bodyTexture) {
        header = new Image(headerTexture);
        body = new Image(bodyTexture);

        header.setPosition(0f, 3.75f * Var.GRID_UNIT);
        body.setPosition(0f, 0f);
        addActor(header);
        addActor(body);
        setHeight(4.5f * Var.GRID_UNIT);
    }

}
