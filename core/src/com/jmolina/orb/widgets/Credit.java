package com.jmolina.orb.widgets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Disposable;
import com.jmolina.orb.Orb;
import com.jmolina.orb.groups.BaseGroup;
import com.jmolina.orb.var.Var;

public class Credit extends BaseGroup implements Disposable {

    private Label header;
    private Label body;
    private BitmapFont headerFont;
    private BitmapFont bodyFont;

    public Credit(String header, String body) {
        headerFont = new BitmapFont(Gdx.files.internal("font/roboto_medium_45.fnt"));
        headerFont.setColor(Color.WHITE);

        bodyFont = new BitmapFont(Gdx.files.internal("font/roboto_regular_30.fnt"));
        bodyFont.setColor(Color.WHITE);

        Label.LabelStyle bodyLabelStyle = new Label.LabelStyle();
        bodyLabelStyle.fontColor = new Color(Var.COLOR_BLACK);
        bodyLabelStyle.font = bodyFont;

        Label.LabelStyle headerLabelStyle = new Label.LabelStyle();
        headerLabelStyle.fontColor = new Color(Var.COLOR_BLUE);
        headerLabelStyle.font = headerFont;

        this.body = new Label(body, bodyLabelStyle);
        this.body.setTouchable(Touchable.disabled);
        this.body.setPosition(0f, 0f);
        this.body.setWidth(10f * Var.GRID_UNIT);
        this.body.setWrap(true);
        this.body.setAlignment(Align.topLeft);
        this.body.setHeight(this.body.getPrefHeight());

        this.header = new Label(header, headerLabelStyle);
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
        headerFont.dispose();
        bodyFont.dispose();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.setShader(null);
        super.draw(batch, parentAlpha);
    }
}
