package com.jmolina.orb.widgets.ui;

import com.badlogic.gdx.Gdx;
import com.jmolina.orb.managers.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.jmolina.orb.var.Utils;
import com.jmolina.orb.var.Atlas;
import com.jmolina.orb.var.Font;
import com.jmolina.orb.var.Var;
import com.jmolina.orb.widgets.BaseGroup;

public class Option extends BaseGroup {

    private Label label;
    private Checkbox checkbox;

    public Option(AssetManager am, String name) {
        this(am, name, true);
    }

    public Option(AssetManager am, String name, boolean checked) {
        super(am);

        Label.LabelStyle style = new Label.LabelStyle();
        style.fontColor = new Color(Var.COLOR_LILAC_DARK);
        style.font = new BitmapFont(
                Gdx.files.internal(Font.FONT_ROBOTO_MEDIUM_45),
                findRegion(Atlas.FONT_ROBOTO_MEDIUM_45)
        );

        label = new Label(name, style);
        label.setTouchable(Touchable.disabled);
        label.setPosition(0f, 0f);
        label.setHeight(Utils.cell(1.5f));

        checkbox = new Checkbox(getAssetManager(), checked);
        checkbox.setPosition(Utils.cell(8.5f), 0f);

        addActor(label);
        addActor(checkbox);
        setHeight(Utils.cell(1.5f));
        setTouchable(Touchable.childrenOnly);
    }

    public void toggle() {
        checkbox.toggle();
    }

    public boolean isChecked() {
        return checkbox.isChecked();
    }

    public void setChecked(boolean checked) {
        if (checked) checkbox.check();
        else checkbox.uncheck();
    }

}
