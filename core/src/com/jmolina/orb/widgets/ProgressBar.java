package com.jmolina.orb.widgets;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Disposable;
import com.jmolina.orb.utils.Utils;
import com.jmolina.orb.var.Asset;
import com.jmolina.orb.var.Var;

public class ProgressBar extends BaseGroup implements Disposable {

    private Image base;
    private Image fill;
    private Label label;

    public ProgressBar(AssetManager am) {
        super(am);

        Label.LabelStyle style = new Label.LabelStyle();
        style.fontColor = new Color(Var.COLOR_BLUE);
        style.font = getAsset(Asset.FONT_ROBOTO_REGULAR_30, BitmapFont.class);

        base = new Image(getAsset(Asset.UI_PROGRESS_BASE, Texture.class));
        base.setPosition(Utils.cell(0), Utils.cell(0));
        base.setSize(Utils.cell(8), Utils.cell(1));

        fill = new Image(getAsset(Asset.UI_PROGRESS_FILL, Texture.class));
        fill.setPosition(Utils.cell(0), Utils.cell(0));
        fill.setSize(Utils.cell(0), Utils.cell(1));

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
        this.fill.setWidth(Utils.cell(8) * progress);
    }

    @Override
    public void dispose() {
        super.dispose();
    }

}
