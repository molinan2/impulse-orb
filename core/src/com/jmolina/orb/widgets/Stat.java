package com.jmolina.orb.widgets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Disposable;
import com.jmolina.orb.var.Asset;
import com.jmolina.orb.var.Var;

import java.text.DecimalFormat;

public class Stat extends BaseWidget {

    private Label name;
    private Label stat;

    public Stat (AssetManager am, String name, float value) {
        this(am, name, value, "");
    }

    public Stat(AssetManager am, String name, float value, String unit) {
        super(am);

        Label.LabelStyle nameStyle = new Label.LabelStyle();
        nameStyle.fontColor = new Color(Var.COLOR_BLUE);
        nameStyle.font = getAsset(Asset.FONT_ROBOTO_REGULAR_45, BitmapFont.class);

        this.name = new Label(name, nameStyle);
        this.name.setTouchable(Touchable.disabled);
        this.name.setPosition(0f, 0f);
        this.name.setHeight(1f * Var.GRID_UNIT);
        this.name.setWidth(6f * Var.GRID_UNIT);
        this.name.setAlignment(Align.left);

        Label.LabelStyle numberStyle = new Label.LabelStyle();
        numberStyle.fontColor = new Color(Var.COLOR_BLACK);
        numberStyle.font = getAsset(Asset.FONT_ROBOTO_REGULAR_45, BitmapFont.class);

        this.stat = new Label(formatStat(value, unit), numberStyle);

        this.stat.setTouchable(Touchable.disabled);
        this.stat.setPosition(6f * Var.GRID_UNIT, 0f);
        this.stat.setHeight(1f * Var.GRID_UNIT);
        this.stat.setWidth(4f * Var.GRID_UNIT);
        this.stat.setAlignment(Align.right);

        addActor(this.name);
        addActor(this.stat);
        setHeight(1.0f * Var.GRID_UNIT);
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    private String formatStat(float value, String unit) {
        DecimalFormat df = new DecimalFormat("###.#");
        String stat;

        if (unit.length() > 0)
            stat = df.format(value) + " " + unit;
        else
            stat = df.format(value);

        return stat;
    }

    public void setValue(float value) {
        this.stat.setText(formatStat(value, ""));
    }

    public void setValue(float value, String unit) {
        this.stat.setText(formatStat(value, unit));
    }

    public void setName(String name) {
        this.name.setText(name);
    }

    public String getName() {
        return this.name.getText().toString();
    }

}