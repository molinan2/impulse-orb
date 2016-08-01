package com.jmolina.orb.widgets.ui;


import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.jmolina.orb.utils.Utils;
import com.jmolina.orb.var.Asset;
import com.jmolina.orb.var.Var;
import com.jmolina.orb.widgets.BaseGroup;

// No se encuentra en GWT (HTML), por lo que no se puede compilar en GWT (HTML)
import java.text.DecimalFormat;

/**
 * TODO: Que conserve la unidad para no tener que especificarla en cada setValue
 */
public class Stat extends BaseGroup {

    private Label name;
    private Label data;
    private Label.LabelStyle nameStyle;
    private Label.LabelStyle dataStyle;

    public Stat (AssetManager am, String name) {
        this(am, name, 0);
    }

    public Stat (AssetManager am, String name, float value) {
        this(am, name, value, "");
    }

    public Stat(AssetManager am, String name, float value, String unit) {
        super(am);

        nameStyle = new Label.LabelStyle();
        dataStyle = new Label.LabelStyle();

        nameStyle.fontColor = new Color(Var.COLOR_BLUE);
        dataStyle.fontColor = new Color(Var.COLOR_BLACK);
        nameStyle.font = getAsset(Asset.FONT_ROBOTO_REGULAR_45, BitmapFont.class);
        dataStyle.font = getAsset(Asset.FONT_ROBOTO_REGULAR_45, BitmapFont.class);

        this.name = new Label(name, nameStyle);
        this.name.setTouchable(Touchable.disabled);
        this.name.setPosition(0f, 0f);
        this.name.setHeight(Utils.cell(1));
        this.name.setWidth(Utils.cell(6));
        this.name.setAlignment(Align.left);

        this.data = new Label(formatStat(value, unit), dataStyle);
        this.data.setTouchable(Touchable.disabled);
        this.data.setPosition(Utils.cell(6), 0f);
        this.data.setHeight(Utils.cell(1));
        this.data.setWidth(Utils.cell(4));
        this.data.setAlignment(Align.right);

        addActor(this.name);
        addActor(this.data);
        setTransform(false);
        setHeight(Utils.cell(1));
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    private String formatStat(float value, String unit) {
        return formatStat(value, unit, true);
    }

    private String formatStat(float value, String unit, boolean withDecimals) {
        DecimalFormat df;

        if (withDecimals)
            df = new DecimalFormat("###.##");
        else
            df = new DecimalFormat("###");

        String stat;

        if (unit.length() > 0)
            stat = df.format(value) + " " + unit;
        else
            stat = df.format(value);

        return stat;
    }

    public void setValue(float value) {
        setValue(value, "");
    }

    public void setValue(float value, String unit) {
        setValue(value, unit, true);
    }

    public void setValue(float value, String unit, boolean decimals) {
        this.data.setText(formatStat(value, unit, decimals));
    }

    public void setNullValue(String unit) {
        this.data.setText("-- " + unit);
    }

    public void setName(String name) {
        this.name.setText(name);
    }

    public String getName() {
        return this.name.getText().toString();
    }

    public void setLabelColor (int color) {
        nameStyle.fontColor = new Color(color);
        dataStyle.fontColor = new Color(color);
        name.setStyle(nameStyle);
        data.setStyle(dataStyle);
    }

}