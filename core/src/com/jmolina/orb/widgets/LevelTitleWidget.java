package com.jmolina.orb.widgets;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Disposable;
import com.jmolina.orb.groups.BaseGroup;
import com.jmolina.orb.var.Vars;

public class LevelTitleWidget extends BaseGroup implements Disposable {

    private Image title;
    // private Image background;
    // private Texture backgroundTexture;

    public LevelTitleWidget(Texture titleTexture) {
        title = new Image(titleTexture);
        addActor(title);
        setHeight(1f * Vars.GRID_UNIT);
    }

    @Override
    public void dispose() {
    }
}
