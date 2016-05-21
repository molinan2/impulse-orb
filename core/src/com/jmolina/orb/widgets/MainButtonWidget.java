package com.jmolina.orb.widgets;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.jmolina.orb.groups.BaseGroup;

public class MainButtonWidget extends BaseGroup {

    private Image button;

    public MainButtonWidget(Texture texture) {
        button = new Image(texture);
        button.setPosition(0f, 0f);
        addActor(button);
    }

}
