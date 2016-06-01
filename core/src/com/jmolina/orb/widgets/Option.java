package com.jmolina.orb.widgets;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.jmolina.orb.var.Asset;
import com.jmolina.orb.var.Var;

public class Option extends BaseWidget {

    private Label label;
    private Checkbox checkbox;

    public Option(AssetManager am, String name) {
        this(am, name, true);
    }

    public Option(AssetManager am, String name, boolean checked) {
        super(am);

        Label.LabelStyle style = new Label.LabelStyle();
        style.fontColor = new Color(Var.COLOR_BLUE);
        style.font = getAsset(Asset.FONT_ROBOTO_MEDIUM_45, BitmapFont.class);

        label = new Label(name, style);
        label.setTouchable(Touchable.disabled);
        label.setPosition(0f, 0f);
        label.setHeight(1.5f * Var.GRID_UNIT);

        checkbox = new Checkbox(checked);
        checkbox.setPosition(8.5f * Var.GRID_UNIT, 0f);

        addActor(label);
        addActor(checkbox);
        setHeight(1.5f * Var.GRID_UNIT);
        setTouchable(Touchable.childrenOnly);
    }

    public void toggleCheckbox() {
        checkbox.toggle();
    }

    public boolean isChecked() {
        return checkbox.isChecked();
    }

    public void setChecked(boolean checked) {
        if (checked) checkbox.check();
        else checkbox.uncheck();
    }

    @Override
    public void dispose() {
        checkbox.dispose();
        super.dispose();
    }
}
