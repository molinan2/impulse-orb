package com.jmolina.orb.widgets;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.jmolina.orb.groups.BaseGroup;
import com.jmolina.orb.var.Vars;

public class MainButtonWidget extends BaseGroup {

    private Image button;

    public MainButtonWidget(Texture texture) {
        button = new Image(texture);
        button.setPosition(0f, 0f);
        //button.setSize(texture.getWidth(), texture.getHeight());
        addActor(button);
        setHeight(1.5f * Vars.GRID_UNIT);
        setOrigin(texture.getWidth() * 0.5f, texture.getHeight() * 0.5f);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        // batch.draw(this.texture, this.getX(), this.getY());

        // button.draw(batch, parentAlpha);
        // title.draw(batch, parentAlpha);

        super.draw(batch, parentAlpha);
    }

}
