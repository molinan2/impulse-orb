package com.jmolina.orb.widgets;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Disposable;
import com.jmolina.orb.groups.BaseGroup;
import com.jmolina.orb.var.Var;

public class LadderWidget extends BaseGroup implements Disposable {

    private Image title;
    // Array<LadderRowWidget> rows;
    // private Image background;
    // private Texture backgroundTexture;

    public LadderWidget(Texture titleTexture) {
        title = new Image(titleTexture);
        addActor(title);
        setHeight(3.75f * Var.GRID_UNIT);
    }

    @Override
    public void dispose() {
    }
}