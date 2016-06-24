package com.jmolina.orb.widgets;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.jmolina.orb.utils.Grid;
import com.jmolina.orb.assets.Asset;

public class Credit extends BaseGroup {

    private Label header;
    private Label body;

    public Credit(AssetManager am, String header, String body) {
        super(am);

        Label.LabelStyle bodyStyle = new Label.LabelStyle();
        bodyStyle.fontColor = new Color(BaseGroup.COLOR_BLACK);
        bodyStyle.font = getAsset(Asset.FONT_ROBOTO_REGULAR_30, BitmapFont.class);

        Label.LabelStyle headerStyle = new Label.LabelStyle();
        headerStyle.fontColor = new Color(BaseGroup.COLOR_BLUE);
        headerStyle.font = getAsset(Asset.FONT_ROBOTO_MEDIUM_45, BitmapFont.class);

        this.body = new Label(body, bodyStyle);
        this.body.setTouchable(Touchable.disabled);
        this.body.setPosition(0f, 0f);
        this.body.setWidth(Grid.unit(10));
        this.body.setWrap(true);
        this.body.setAlignment(Align.topLeft);
        this.body.setHeight(this.body.getPrefHeight());

        this.header = new Label(header, headerStyle);
        this.header.setTouchable(Touchable.disabled);
        this.header.setPosition(0f, this.body.getPrefHeight());
        this.header.setSize(Grid.unit(10), Grid.unit(1));
        this.header.setAlignment(Align.topLeft);

        addActor(this.header);
        addActor(this.body);
        setHeight(this.header.getPrefHeight() + this.body.getPrefHeight());
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
    }
}
