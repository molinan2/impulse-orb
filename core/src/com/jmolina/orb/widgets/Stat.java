package com.jmolina.orb.widgets;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.jmolina.orb.utils.Grid;
import com.jmolina.orb.assets.Asset;

// No se encuentra en GWT (HTML), por lo que no se puede compilar en GWT (HTML)
import java.text.DecimalFormat;

public class Stat extends BaseGroup {

    private Label name;
    private Label stat;

    public Stat (AssetManager am, String name, float value) {
        this(am, name, value, "");
    }

    public Stat(AssetManager am, String name, float value, String unit) {
        super(am);

        Label.LabelStyle nameStyle = new Label.LabelStyle();
        nameStyle.fontColor = new Color(BaseGroup.COLOR_BLUE);
        nameStyle.font = getAsset(Asset.FONT_ROBOTO_REGULAR_45, BitmapFont.class);

        this.name = new Label(name, nameStyle);
        this.name.setTouchable(Touchable.disabled);
        this.name.setPosition(0f, 0f);
        this.name.setHeight(Grid.unit(1));
        this.name.setWidth(Grid.unit(6));
        this.name.setAlignment(Align.left);

        Label.LabelStyle numberStyle = new Label.LabelStyle();
        numberStyle.fontColor = new Color(BaseGroup.COLOR_BLACK);
        numberStyle.font = getAsset(Asset.FONT_ROBOTO_REGULAR_45, BitmapFont.class);

        this.stat = new Label(formatStat(value, unit), numberStyle);

        this.stat.setTouchable(Touchable.disabled);
        this.stat.setPosition(Grid.unit(6), 0f);
        this.stat.setHeight(Grid.unit(1));
        this.stat.setWidth(Grid.unit(4));
        this.stat.setAlignment(Align.right);

        addActor(this.name);
        addActor(this.stat);
        setHeight(Grid.unit(1));
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