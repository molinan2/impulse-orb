package com.jmolina.orb.widgets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Disposable;
import com.jmolina.orb.groups.BaseGroup;
import com.jmolina.orb.utils.Grid;
import com.jmolina.orb.var.Var;

public class LevelTitle extends BaseGroup implements Disposable {

    private Label name;
    private Image bg;
    private Texture bgTexture;
    private BitmapFont font;

    public LevelTitle(String name) {
        bgTexture = new Texture(Gdx.files.internal("launch_title.png"));
        bg = new Image(bgTexture);
        bg.setPosition(0f, 0f);

        this.font = new BitmapFont(Gdx.files.internal("font/roboto_bold_45.fnt"));

        Label.LabelStyle style = new Label.LabelStyle();
        style.fontColor = new Color(Var.COLOR_WHITE);
        style.font = font;

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
        bgTexture.dispose();
    }
}
