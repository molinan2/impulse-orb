package com.jmolina.orb.widgets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Disposable;
import com.jmolina.orb.groups.BaseGroup;
import com.jmolina.orb.var.Var;

import java.text.DecimalFormat;

public class Stat extends BaseGroup implements Disposable {

    private Label name;
    private Label number;
    private BitmapFont font;
    private String unit;

    public Stat (String name, float value) {
        this(name, value, "");
    }

    public Stat(String name, float value, String unit) {
        this.unit = unit;

        font = new BitmapFont(Gdx.files.internal("font/roboto_regular_45.fnt"));
        font.setColor(Color.WHITE);

        Label.LabelStyle nameLabelStyle = new Label.LabelStyle();
        nameLabelStyle.fontColor = new Color(Var.COLOR_BLUE);
        nameLabelStyle.font = font;

        this.name = new Label(name, nameLabelStyle);
        this.name.setTouchable(Touchable.disabled);
        this.name.setPosition(0f, 0f);
        this.name.setHeight(1f * Var.GRID_UNIT);
        this.name.setWidth(6f * Var.GRID_UNIT);
        this.name.setAlignment(Align.left);


        Label.LabelStyle numberLabelStyle = new Label.LabelStyle();
        numberLabelStyle.fontColor = new Color(Var.COLOR_BLACK);
        numberLabelStyle.font = font;

        DecimalFormat df = new DecimalFormat("###.#");

        if (unit.length() > 0)
            this.number = new Label(df.format(value) + unit, numberLabelStyle);
        else
            this.number = new Label(df.format(value), numberLabelStyle);

        this.number.setTouchable(Touchable.disabled);
        this.number.setPosition(6f * Var.GRID_UNIT, 0f);
        this.number.setHeight(1f * Var.GRID_UNIT);
        this.number.setWidth(4f * Var.GRID_UNIT);
        this.number.setAlignment(Align.right);


        addActor(this.name);
        addActor(this.number);
        setHeight(1.0f * Var.GRID_UNIT);
    }

    @Override
    public void dispose() {
        font.dispose();
    }

}