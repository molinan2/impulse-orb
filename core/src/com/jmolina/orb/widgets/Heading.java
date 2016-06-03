package com.jmolina.orb.widgets;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Disposable;
import com.jmolina.orb.var.Asset;
import com.jmolina.orb.var.Var;

public class Heading extends BaseWidget {

    public enum Weight {
        Regular, Medium, Bold
    }

    private Label label;

    public Heading(AssetManager am, String name) {
        this(am, name, Align.left, Weight.Medium);
    }

    public Heading(AssetManager am, String name, int align) {
        this(am, name, align, Weight.Medium);
    }

    public Heading(AssetManager am, String name, int align, Weight weight) {
        super(am);

        Label.LabelStyle style = new Label.LabelStyle();
        style.fontColor = new Color(Var.COLOR_BLUE);
        style.font = newBitmapFont(weight);

        label = new Label(name, style);
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
        super.dispose();
    }

    public BitmapFont newBitmapFont (Weight weight) {
        switch (weight) {
            case Regular:
                return getAsset(Asset.FONT_ROBOTO_REGULAR_45, BitmapFont.class);
            case Medium:
                return getAsset(Asset.FONT_ROBOTO_MEDIUM_45, BitmapFont.class);
            case Bold:
                return getAsset(Asset.FONT_ROBOTO_BOLD_45, BitmapFont.class);
            default:
                return getAsset(Asset.FONT_ROBOTO_MEDIUM_45, BitmapFont.class);
        }
    }
}