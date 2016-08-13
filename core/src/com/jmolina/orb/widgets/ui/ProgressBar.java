package com.jmolina.orb.widgets.ui;

import com.badlogic.gdx.Gdx;
import com.jmolina.orb.managers.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.jmolina.orb.utils.Utils;
import com.jmolina.orb.var.Asset;
import com.jmolina.orb.var.Atlas;
import com.jmolina.orb.var.Font;
import com.jmolina.orb.var.Var;
import com.jmolina.orb.widgets.BaseGroup;

public class ProgressBar extends BaseGroup {

    private Image base, fill;
    private Label label;

    public ProgressBar(AssetManager am) {
        super(am);

        Label.LabelStyle style = new Label.LabelStyle();
        style.fontColor = new Color(Var.COLOR_BLACK);
        style.font = new BitmapFont(
                Gdx.files.internal(Font.FONT_ROBOTO_REGULAR_30),
                findRegion(Atlas.FONT_ROBOTO_REGULAR_30)
        );

        base = new Image(getAsset(Asset.UI_PROGRESS_BASE, Texture.class));
        base.setPosition(Utils.cell(0), Utils.cell(0));
        base.setSize(Utils.cell(8), Utils.cell(1));

        fill = new Image(getAsset(Asset.UI_PROGRESS_FILL, Texture.class));
        fill.setPosition(Utils.cell(0.125f), Utils.cell(0.125f));
        fill.setSize(Utils.cell(0), Utils.cell(0.75f));

        label = new Label("0%", style);
        label.setPosition(Utils.cell(0), Utils.cell(0));
        label.setSize(Utils.cell(8), Utils.cell(1));
        label.setAlignment(Align.center);

        addActor(base);
        addActor(fill);
        addActor(label);

        setHeight(Utils.cell(1));
    }

    public void updateProgress(float progress) {
        this.label.setText(Math.round(100 * progress) + "%");
        this.fill.setWidth(Utils.cell(7.75f) * progress);
    }

}
