package com.jmolina.orb.widgets;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.jmolina.orb.utils.Utils;
import com.jmolina.orb.var.Asset;
import com.jmolina.orb.var.Var;

public class BigText extends BaseGroup {

    private Label label;

    public BigText(AssetManager am, String text) {
        super(am);

        Label.LabelStyle style = new Label.LabelStyle();
        style.fontColor = new Color(Var.COLOR_BLUE);
        style.font = getAsset(Asset.FONT_ROBOTO_BOLD_90, BitmapFont.class);

        this.label = new Label(text, style);
        this.label.setTouchable(Touchable.disabled);
        this.label.setPosition(0f, 0f);
        this.label.setHeight(Utils.cell(1.5f));
        this.label.setWidth(Utils.cell(10f));
        this.label.setAlignment(Align.center);

        addActor(this.label);
        setHeight(Utils.cell(1.5f));
    }

    @Override
    public void dispose() {
        super.dispose();
    }

}