package com.jmolina.orb.widgets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Disposable;
import com.jmolina.orb.groups.BaseGroup;
import com.jmolina.orb.var.Var;

public class Credit extends BaseGroup implements Disposable {

    private Label header;
    private Label body;
    private BitmapFont font;

    public Credit(String header, String body) {
        font = new BitmapFont(Gdx.files.internal("font/roboto_regular_45.fnt"));
        font.setColor(Color.WHITE);

        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.fontColor = new Color(Var.COLOR_BLUE);
        labelStyle.font = font;

        this.body = new Label(body, labelStyle);
        this.body.setTouchable(Touchable.disabled);
        this.body.setPosition(0f, 0f);
        this.body.setWidth(10f * Var.GRID_UNIT);
        this.body.setWrap(true);
        this.body.setAlignment(Align.topLeft);
        this.body.setFontScale(0.5f);
        this.body.setHeight(this.body.getPrefHeight());

        this.header = new Label(header, labelStyle);
        this.header.setTouchable(Touchable.disabled);
        this.header.setPosition(0f, this.body.getPrefHeight());
        this.header.setHeight(1f * Var.GRID_UNIT);
        this.header.setWidth(10f * Var.GRID_UNIT);
        this.header.setAlignment(Align.topLeft);

        addActor(this.header);
        addActor(this.body);
        setHeight(this.header.getPrefHeight() + this.body.getPrefHeight());
    }

    @Override
    public void dispose() {
        font.dispose();
    }
}
