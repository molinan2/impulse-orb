package com.jmolina.orb.widgets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.jmolina.orb.var.Vars;

public class BackButtonTableWidget extends Table {

    private Button button;
    private Label label;
    private BitmapFont font;
    private Image image;
    private Texture imageTexture;

    // todo Se le debe pasar una referencia a la pantalla anterior, y el texto del titulo

    public BackButtonTableWidget(String text) {
        super();

        button = new Button();

        imageTexture = new Texture(Gdx.files.internal("splash.png"));
        image = new Image(imageTexture);

        font = new BitmapFont(Gdx.files.internal("layout/font.fnt"));

        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = font;
        labelStyle.fontColor = Color.WHITE;

        label = new Label(text, labelStyle);

        this.row();
        this.add(image).height(100f).width(100f).expand();
        this.add(label).height(100f).width(Vars.VIEWPORT_WIDTH * 0.6f).expand();
        this.debug();
        this.setPosition(0.1f * Vars.VIEWPORT_WIDTH, 0.1f * Vars.VIEWPORT_HEIGHT);
        this.setWidth(Vars.VIEWPORT_WIDTH * 0.8f);
        this.setHeight(0.1f * Vars.VIEWPORT_HEIGHT);
    }

}
