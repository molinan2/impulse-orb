package com.jmolina.orb.widgets;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.jmolina.orb.utils.Grid;
import com.jmolina.orb.var.Asset;
import com.jmolina.orb.var.Var;

public class LevelTitle extends BaseWidget {

    private Label name;
    private Image bg;

    public LevelTitle(AssetManager am, String name) {
        super(am);

        bg = new Image(getAssetManager().get(Asset.UI_LAUNCH_TITLE, Texture.class));
        bg.setPosition(0f, 0f);

        Label.LabelStyle style = new Label.LabelStyle();
        style.fontColor = new Color(Var.COLOR_WHITE);
        style.font = getAssetManager().get(Asset.FONT_ROBOTO_BOLD_45, BitmapFont.class);

        this.name = new Label(name.toUpperCase(), style);
        this.name.setPosition(Grid.unit(0), Grid.unit(0));
        this.name.setSize(Grid.unit(10), Grid.unit(1));
        this.name.setAlignment(Align.center);

        addActor(this.bg);
        addActor(this.name);

        setHeight(Grid.unit(1));
        setWidth(Grid.unit(10));
    }

    @Override
    public void dispose() {
        super.dispose();
    }

}
