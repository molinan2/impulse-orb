package com.jmolina.orb.widgets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Disposable;
import com.jmolina.orb.actors.Checkbox;
import com.jmolina.orb.groups.BaseGroup;
import com.jmolina.orb.var.Var;

import java.util.ArrayList;

public class MultiOptionWidget extends BaseGroup implements Disposable {

    private Label label;
    private BitmapFont font;
    private int value;
    private ArrayList<Checkbox> checkboxes;

    public MultiOptionWidget (String name) {
        this(name, 2);
    }

    public MultiOptionWidget (String name, int value) {
        font = new BitmapFont(Gdx.files.internal("font/roboto_medium_45.fnt"));
        font.setColor(Color.WHITE);

        Label.LabelStyle ls = new Label.LabelStyle();
        ls.fontColor = new Color(Var.COLOR_BLUE);
        ls.font = font;

        label = new Label(name, ls);
        label.setTouchable(Touchable.disabled);
        label.setPosition(0f, 0f);
        label.setHeight(1.5f * Var.GRID_UNIT);

        checkboxes = new ArrayList<Checkbox>();
        checkboxes.add(new Checkbox(false));
        checkboxes.add(new Checkbox(false));
        checkboxes.add(new Checkbox(false));

        setValue(value);

        checkboxes.get(0).setPosition(5.5f * Var.GRID_UNIT, 0f);
        checkboxes.get(0).setName("0");
        checkboxes.get(1).setPosition(7f * Var.GRID_UNIT, 0f);
        checkboxes.get(1).setName("1");
        checkboxes.get(2).setPosition(8.5f * Var.GRID_UNIT, 0f);
        checkboxes.get(2).setName("2");

        addActor(label);
        addActor(checkboxes.get(0));
        addActor(checkboxes.get(1));
        addActor(checkboxes.get(2));
        setHeight(1.5f * Var.GRID_UNIT);
        setTouchable(Touchable.childrenOnly);
    }

    @Override
    public void dispose () {
        font.dispose();
        for (Checkbox checkbox : checkboxes) {
            checkbox.dispose();
        }
    }

    public void setValue (int value) {
        this.value = MathUtils.clamp(value, Var.OPTION_ZOOM_MIN, Var.OPTION_ZOOM_MAX);

        for (int i=0; i<checkboxes.size(); i++) {
            checkboxes.get(i).uncheck();
        }

        checkboxes.get(this.value).check();
    }

}

