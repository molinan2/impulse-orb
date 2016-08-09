package com.jmolina.orb.widgets.ui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.jmolina.orb.managers.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.jmolina.orb.utils.Utils;
import com.jmolina.orb.var.Asset;
import com.jmolina.orb.var.Var;
import com.jmolina.orb.widgets.BaseGroup;

import java.util.ArrayList;

public class MultiOption extends BaseGroup {

    private final int DEFAULT_VALUE = 1;
    private final int OPTIONS = 3;

    private Label label;
    private int value;
    private ArrayList<Checkbox> checkboxes;
    private Image lever;

    public MultiOption(AssetManager am, String name) {
        super(am);

        Label.LabelStyle style = new Label.LabelStyle();
        style.fontColor = new Color(Var.COLOR_DARK_LILAC);
        style.font = getAsset(Asset.FONT_ROBOTO_MEDIUM_45, BitmapFont.class);

        label = new Label(name, style);
        label.setTouchable(Touchable.disabled);
        label.setPosition(0f, 0f);
        label.setHeight(Utils.cell(1.5f));

        checkboxes = new ArrayList<Checkbox>();
        for (int i=0; i<OPTIONS; i++) {
            checkboxes.add(new Checkbox(getAssetManager(), false));
            checkboxes.get(i).setPositionGrid(5.5f + i * 1.5f, 0);
            checkboxes.get(i).setName(String.valueOf(i));
        }

        lever = new Image(getAsset(Asset.UI_MULTICHECK_1, Texture.class));
        lever.setTouchable(Touchable.disabled);
        lever.setSize(Utils.cell(4.5f), Utils.cell(1.5f));
        lever.setPosition(Utils.cell(5.5f), Utils.cell(0));

        addActor(label);
        for (Actor actor : checkboxes) addActor(actor);
        addActor(lever);

        setHeight(Utils.cell(1.5f));
        setTouchable(Touchable.childrenOnly);
        setValue(DEFAULT_VALUE);
    }

    public void setValue (int value) {
        value = MathUtils.clamp(value, 0, OPTIONS);
        setCheckboxes(value);
        setLever(value);
        this.value = value;
    }

    private void setCheckboxes(int value) {
        for (Checkbox checkbox : checkboxes)
            checkbox.uncheck();

        checkboxes.get(value).check();
    }

    private void setLever(int value) {
        switch (value) {
            case 0: lever.setDrawable(new TextureRegionDrawable(new TextureRegion(getAsset(Asset.UI_MULTICHECK_0, Texture.class)))); break;
            case 1: lever.setDrawable(new TextureRegionDrawable(new TextureRegion(getAsset(Asset.UI_MULTICHECK_1, Texture.class)))); break;
            case 2: lever.setDrawable(new TextureRegionDrawable(new TextureRegion(getAsset(Asset.UI_MULTICHECK_2, Texture.class)))); break;
            default: lever.setDrawable(new TextureRegionDrawable(new TextureRegion(getAsset(Asset.UI_MULTICHECK_1, Texture.class))));
        }
    }

}

