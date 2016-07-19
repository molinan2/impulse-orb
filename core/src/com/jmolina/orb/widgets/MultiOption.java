package com.jmolina.orb.widgets;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.jmolina.orb.utils.Grid;
import com.jmolina.orb.var.Asset;
import com.jmolina.orb.var.Var;

import java.util.ArrayList;

public class MultiOption extends BaseGroup {

    private Label label;
    private int value;
    private ArrayList<Checkbox> checkboxes;

    public MultiOption(AssetManager am, String name) {
        this(am, name, 2);
    }

    public MultiOption(AssetManager am, String name, int value) {
        super(am);

        Label.LabelStyle style = new Label.LabelStyle();
        style.fontColor = new Color(Var.COLOR_BLUE);
        style.font = getAsset(Asset.FONT_ROBOTO_MEDIUM_45, BitmapFont.class);

        label = new Label(name, style);
        label.setTouchable(Touchable.disabled);
        label.setPosition(0f, 0f);
        label.setHeight(Grid.unit(1.5f));

        checkboxes = new ArrayList<Checkbox>();
        checkboxes.add(new Checkbox(getAssetManager(), false));
        checkboxes.add(new Checkbox(getAssetManager(), false));
        checkboxes.add(new Checkbox(getAssetManager(), false));

        setValue(value);

        checkboxes.get(0).setPosition(Grid.unit(5.5f), 0f);
        checkboxes.get(0).setName("0");
        checkboxes.get(1).setPosition(Grid.unit(7), 0f);
        checkboxes.get(1).setName("1");
        checkboxes.get(2).setPosition(Grid.unit(8.5f), 0f);
        checkboxes.get(2).setName("2");

        addActor(label);
        addActor(checkboxes.get(0));
        addActor(checkboxes.get(1));
        addActor(checkboxes.get(2));
        setHeight(Grid.unit(1.5f));
        setTouchable(Touchable.childrenOnly);
    }

    @Override
    public void dispose () {
        for (Checkbox checkbox : checkboxes)
            checkbox.dispose();

        super.dispose();
    }

    public void setValue (int value) {
        this.value = MathUtils.clamp(value, 0, 2);

        for (Checkbox checkbox : checkboxes)
            checkbox.uncheck();

        checkboxes.get(this.value).check();
    }

}

