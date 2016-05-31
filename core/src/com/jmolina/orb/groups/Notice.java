package com.jmolina.orb.groups;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Disposable;
import com.jmolina.orb.utils.Grid;
import com.jmolina.orb.var.Var;

public class Notice extends BaseGroup implements Disposable {

    private Label author;
    private Label version;
    private BitmapFont font;

    public Notice() {

        font = new BitmapFont(Gdx.files.internal("font/roboto_regular_30.fnt"));
        font.setColor(Color.WHITE);

        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.fontColor = new Color(Var.COLOR_BLUE);
        labelStyle.font = font;

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
        font.dispose();
    }
}
