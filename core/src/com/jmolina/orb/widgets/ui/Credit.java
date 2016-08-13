package com.jmolina.orb.widgets.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.jmolina.orb.managers.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.jmolina.orb.utils.Utils;
import com.jmolina.orb.var.Asset;
import com.jmolina.orb.var.Atlas;
import com.jmolina.orb.var.Font;
import com.jmolina.orb.var.Var;
import com.jmolina.orb.widgets.BaseGroup;

import java.util.ArrayList;

public class Credit extends BaseGroup {

    private Label header;
    private Label body;
    private ArrayList<Label> links;

    public Credit(AssetManager am, String header, String body) {
        super(am);

        links = new ArrayList<Label>();

        Label.LabelStyle bodyStyle = new Label.LabelStyle();
        bodyStyle.fontColor = new Color(Var.COLOR_BLACK);
        bodyStyle.font = new BitmapFont(
                Gdx.files.internal(Font.FONT_ROBOTO_REGULAR_30),
                findRegion(Atlas.FONT_ROBOTO_REGULAR_30)
        );

        Label.LabelStyle headerStyle = new Label.LabelStyle();
        headerStyle.fontColor = new Color(Var.COLOR_LILAC);
        headerStyle.font = new BitmapFont(
                Gdx.files.internal(Font.FONT_ROBOTO_MEDIUM_45),
                findRegion(Atlas.FONT_ROBOTO_MEDIUM_45)
        );

        this.body = new Label(body, bodyStyle);
        this.body.setTouchable(Touchable.disabled);
        this.body.setPosition(0f, 0f);
        this.body.setWidth(Utils.cell(10));
        this.body.setWrap(true);
        this.body.setAlignment(Align.topLeft);
        this.body.setHeight(this.body.getPrefHeight());

        this.header = new Label(header, headerStyle);
        this.header.setTouchable(Touchable.disabled);
        this.header.setPosition(0f, this.body.getPrefHeight());
        this.header.setSize(Utils.cell(10), Utils.cell(1));
        this.header.setAlignment(Align.topLeft);

        addActor(this.header);
        addActor(this.body);

        setHeight(this.header.getPrefHeight() + this.body.getPrefHeight());
    }

    public void addLink(String text, final String uri) {
        Label.LabelStyle style = new Label.LabelStyle();
        style.fontColor = new Color(Var.COLOR_DARK_LILAC);
        style.font = new BitmapFont(
                Gdx.files.internal(Font.FONT_ROBOTO_REGULAR_30),
                findRegion(Atlas.FONT_ROBOTO_REGULAR_30)
        );

        int linkIndex = links.size() + 1;
        Label link = new Label("[" + linkIndex + "]: " + text, style);
        link.setTouchable(Touchable.enabled);
        link.setPosition(0f, 0f);
        link.setAlignment(Align.left);
        link.setHeight(link.getPrefHeight());
        link.setWidth(link.getPrefWidth());
        link.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.net.openURI(uri);
            }
        });

        links.add(link);
        addActor(link);

        header.setY(body.getPrefHeight() + getLinksPrefHeight() + Utils.cell(1));
        body.setY(getLinksPrefHeight() + Utils.cell(1));

        for (Label previouslink : links) {
            previouslink.setY(previouslink.getY() + link.getPrefHeight());
        }

        setHeight(header.getPrefHeight() + body.getPrefHeight() + getLinksPrefHeight() + Utils.cell(1));
    }

    private float getLinksPrefHeight() {
        float height = 0f;

        for(Label link : links) {
            height += link.getPrefHeight();
        }

        return height;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
    }
}
