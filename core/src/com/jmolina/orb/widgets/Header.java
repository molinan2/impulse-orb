package com.jmolina.orb.widgets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Disposable;
import com.jmolina.orb.groups.BaseGroup;
import com.jmolina.orb.var.Var;

public class Header extends BaseGroup implements Disposable {

    public enum Weight {
        Regular, Medium, Bold
    }

    private Label label;
    private BitmapFont font;

    public Header (String name) {
        this(name, Align.left, Weight.Medium);
    }

    public Header (String name, int align) {
        this(name, align, Weight.Medium);
    }

    public Header (String name, int align, Weight weight) {
        font = newBitmapFont(weight);
        font.setColor(Color.WHITE);

        Label.LabelStyle ls = new Label.LabelStyle();
        ls.fontColor = new Color(Var.COLOR_BLUE);
        ls.font = font;

        label = new Label(name, ls);
        label.setTouchable(Touchable.disabled);
        label.setPosition(0f, 0f);
        label.setHeight(1f * Var.GRID_UNIT);
        label.setWidth(10f * Var.GRID_UNIT);
        label.setAlignment(align);

        addActor(label);
        setHeight(1.0f * Var.GRID_UNIT);
    }

    @Override
    public void dispose() {
        font.dispose();
    }

    public BitmapFont newBitmapFont (Weight weight) {
        switch (weight) {
            case Regular:
                return new BitmapFont(Gdx.files.internal("font/roboto_regular_45.fnt"));
            case Medium:
                return new BitmapFont(Gdx.files.internal("font/roboto_medium_45.fnt"));
            case Bold:
                return new BitmapFont(Gdx.files.internal("font/roboto_bold_45.fnt"));
            default:
                return new BitmapFont(Gdx.files.internal("font/roboto_medium_45.fnt"));
        }
    }
}