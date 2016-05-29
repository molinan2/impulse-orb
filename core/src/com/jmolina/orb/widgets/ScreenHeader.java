package com.jmolina.orb.widgets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Disposable;
import com.jmolina.orb.groups.BaseGroup;
import com.jmolina.orb.var.Var;

public class ScreenHeader extends BaseGroup implements Disposable {

    private Image button;
    private Texture buttonTexture;
    private Label label;
    private BitmapFont font;

    public ScreenHeader(String name) {
        font = new BitmapFont(Gdx.files.internal("font/roboto_medium_90.fnt"));
        font.setColor(Color.WHITE);

        Label.LabelStyle ls = new Label.LabelStyle();
        ls.fontColor = new Color(Var.COLOR_BLUE);
        ls.font = font;

        label = new Label(name, ls);
        label.setTouchable(Touchable.disabled);
        label.setPosition(3f * Var.GRID_UNIT, 0f);
        label.setHeight(2f * Var.GRID_UNIT);
        label.setWidth(7f * Var.GRID_UNIT);
        label.setAlignment(Align.left);

        buttonTexture = new Texture(Gdx.files.internal("back.png"));
        button = new Image(buttonTexture);
        button.setPosition(0f, 0f);

        addActor(button);
        addActor(label);
    }

    @Override
    public void dispose() {
        font.dispose();
        buttonTexture.dispose();
    }

    /**
     * Set a new listener for back button. There can be only one
     * @param listener EventListener
     */
    public void setListener(EventListener listener) {
        button.clearListeners();
        button.addListener(listener);
    }

    public void setLabel (String name) {
        label.setText(name);
    }

}
