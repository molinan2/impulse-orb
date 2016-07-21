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

public class Notice extends BaseGroup {

    private Label author;
    private Label version;

    public Notice(AssetManager am) {
        super(am);

        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.fontColor = new Color(Var.COLOR_BLUE);
        labelStyle.font = getAsset(Asset.FONT_ROBOTO_REGULAR_30, BitmapFont.class);

        this.author = new Label(Var.APP_AUTHOR, labelStyle);
        this.author.setTouchable(Touchable.disabled);
        this.author.setPosition(Utils.cell(0), Utils.cell(0));
        this.author.setHeight(Utils.cell(0.5f));
        this.author.setWidth(Utils.cell(10f));
        this.author.setAlignment(Align.left);

        this.version = new Label("v" + Var.APP_VERSION, labelStyle);
        this.version.setTouchable(Touchable.disabled);
        this.version.setPosition(Utils.cell(0), Utils.cell(0));
        this.version.setHeight(Utils.cell(0.5f));
        this.version.setWidth(Utils.cell(10f));
        this.version.setAlignment(Align.right);

        addActor(this.author);
        addActor(this.version);
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
