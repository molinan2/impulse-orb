package com.jmolina.orb.widgets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Disposable;
import com.jmolina.orb.actors.Checkbox;
import com.jmolina.orb.groups.BaseGroup;
import com.jmolina.orb.var.Var;

public class Option extends BaseGroup implements Disposable {

    private Label label;
    private BitmapFont font;
    private Checkbox checkbox;

    public Option(String name) {
        this(name, true);
    }

    public Option(String name, boolean checked) {
        font = new BitmapFont(Gdx.files.internal("font/roboto_medium_45.fnt"));
        font.setColor(Color.WHITE);

        Label.LabelStyle ls = new Label.LabelStyle();
        ls.fontColor = new Color(Var.COLOR_BLUE);
        ls.font = font;

        label = new Label(name, ls);
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
        font.dispose();
    }
}
