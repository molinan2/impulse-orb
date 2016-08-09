package com.jmolina.orb.widgets.ui;

import com.jmolina.orb.managers.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.jmolina.orb.utils.Utils;
import com.jmolina.orb.var.Asset;
import com.jmolina.orb.var.Var;
import com.jmolina.orb.widgets.BaseGroup;

public class SuccessTitle extends BaseGroup {

    private Label label;
    private Image background;

    public SuccessTitle(AssetManager am, String title) {
        super(am);

        background = new Image(getAssetManager().get(Asset.UI_SUCCESS_TITLE_BACKGROUND, Texture.class));
        background.setPosition(0f, 0f);

        Label.LabelStyle style = new Label.LabelStyle();
        style.fontColor = new Color(Var.COLOR_WHITE);
        style.font = getAssetManager().get(Asset.FONT_ROBOTO_BOLD_45, BitmapFont.class);

        label = new Label(title.toUpperCase(), style);
        label.setSize(Utils.cell(10), Utils.cell(1));
        label.setPosition(Utils.cell(0), Utils.cell(0));
        label.setAlignment(Align.center);

        addActor(background);
        addActor(label);

        setSize(Utils.cell(10), Utils.cell(1));
    }

}
