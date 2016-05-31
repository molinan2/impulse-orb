package com.jmolina.orb.widgets;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.jmolina.orb.groups.BaseGroup;
import com.jmolina.orb.utils.Grid;
import com.jmolina.orb.var.Var;

public class LevelCover extends BaseGroup {

    private Image cover;

    public LevelCover(Texture coverTexture) {
        cover = new Image(coverTexture);
        addActor(cover);
        setHeight(Grid.unit(5));
    }

}
