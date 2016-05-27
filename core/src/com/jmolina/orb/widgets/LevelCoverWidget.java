package com.jmolina.orb.widgets;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.jmolina.orb.groups.BaseGroup;
import com.jmolina.orb.var.Var;

public class LevelCoverWidget extends BaseGroup {

    private Image cover;

    public LevelCoverWidget(Texture coverTexture) {
        cover = new Image(coverTexture);
        addActor(cover);
        setHeight(5f * Var.GRID_UNIT);
    }

}
