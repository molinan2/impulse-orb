package com.jmolina.orb.widgets;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.jmolina.orb.utils.Grid;
import com.jmolina.orb.assets.Asset;
import com.jmolina.orb.var.Var;

public class Notice extends OrbGroup {

    private Label author;
    private Label version;

    public Notice(AssetManager am) {
        super(am);

        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.fontColor = new Color(OrbGroup.COLOR_BLUE);
        labelStyle.font = getAsset(Asset.FONT_ROBOTO_REGULAR_30, BitmapFont.class);

        this.author = new Label(Var.AUTHOR, labelStyle);
        this.author.setTouchable(Touchable.disabled);
        this.author.setPosition(Grid.unit(0), Grid.unit(0));
        this.author.setHeight(Grid.unit(0.5f));
        this.author.setWidth(Grid.unit(10f));
        this.author.setAlignment(Align.left);

        this.version = new Label("v" + Var.VERSION, labelStyle);
        this.version.setTouchable(Touchable.disabled);
        this.version.setPosition(Grid.unit(0), Grid.unit(0));
        this.version.setHeight(Grid.unit(0.5f));
        this.version.setWidth(Grid.unit(10f));
        this.version.setAlignment(Align.right);

        addActor(this.author);
        addActor(this.version);
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
