package com.jmolina.orb.widgets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Disposable;
import com.jmolina.orb.actors.Checkbox;
import com.jmolina.orb.groups.BaseGroup;
import com.jmolina.orb.var.Var;

import java.util.ArrayList;

public class MultiOptionWidget extends BaseGroup implements Disposable {

    private Image name;

    private ArrayList<Checkbox> checkboxes;
    private int value;

    public MultiOptionWidget (Texture nameTexture) {
        this(nameTexture, 2);
    }

    public MultiOptionWidget (Texture nameTexture, int value) {
        name = new Image(nameTexture);
        name.setPosition(0f, 0f);

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

        addActor(name);
        addActor(checkboxes.get(0));
        addActor(checkboxes.get(1));
        addActor(checkboxes.get(2));
        setHeight(1.5f * Var.GRID_UNIT);
    }

    @Override
    public void dispose () {
        for (Checkbox checkbox : checkboxes) {
            checkbox.dispose();
        }
    }

    public void setValue (int value) {
        this.value = MathUtils.clamp(value, Var.OPTION_ZOOM_MIN, Var.OPTION_ZOOM_MAX);
        uncheckAll();
        check(value);
    }

    private void uncheckAll () {
        for (int i=0; i<checkboxes.size(); i++) {
            checkboxes.get(i).uncheck();
        }
    }

    private void check (int value) {
        checkboxes.get(value).check();
    }

}
