package com.jmolina.orb.widgets.ui;

import com.badlogic.gdx.Gdx;
import com.jmolina.orb.managers.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.jmolina.orb.utils.Utils;
import com.jmolina.orb.var.Asset;
import com.jmolina.orb.var.Atlas;
import com.jmolina.orb.var.Font;
import com.jmolina.orb.var.Var;
import com.jmolina.orb.widgets.BaseGroup;

public class BigText extends BaseGroup {

    private Label label;

    public BigText(AssetManager am, String text) {
        super(am);

        Label.LabelStyle style = new Label.LabelStyle();
        style.fontColor = new Color(Var.COLOR_LILAC);
        style.font = new BitmapFont(
                Gdx.files.internal(Font.FONT_ROBOTO_BOLD_90),
                findRegion(Atlas.FONT_ROBOTO_BOLD_90)
        );

        this.label = new Label(text, style);
        this.label.setTouchable(Touchable.disabled);
        this.label.setPosition(0f, 0f);
        this.label.setHeight(Utils.cell(1.5f));
        this.label.setWidth(Utils.cell(10f));
        this.label.setAlignment(Align.center);

        addActor(this.label);
        setHeight(Utils.cell(1.5f));
    }

}