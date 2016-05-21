package com.jmolina.orb.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.jmolina.orb.var.Vars;

public class TableTestScreen extends MenuScreen {

    private Table table;
    private String text;
    private BitmapFont font;

    public TableTestScreen() {
        font = new BitmapFont(Gdx.files.internal("layout/font.fnt"));

        text = "Bacon ipsum dolor amet prosciutto tail cow pastrami meatball corned beef short ribs, brisket jerky. Hamburger porchetta kevin, tongue kielbasa turducken pig. Tri-tip cupim chuck pastrami, alcatra turkey ribeye chicken andouille hamburger ground round. Shankle bacon meatloaf pork rump pork chop filet mignon ham flank ground round.";

        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = font;
        labelStyle.fontColor = Color.BLACK;

        Label label1 = new Label(text, labelStyle);
        label1.setAlignment(Align.left);
        label1.setWrap(true);

        Label label2 = new Label(text, labelStyle);
        label2.setAlignment(Align.left);
        label2.setWrap(true);

        Texture imgTexture = new Texture(Gdx.files.internal("splash.png"));
        Image img1 = new Image(imgTexture);
        Image img2 = new Image(imgTexture);

        table = new Table();
        table.debug();

        table.row();
        table.add(img1).height(100f).width(100f).pad(30f).expand();
        table.add(img2).height(100f).width(100f).pad(30f).expand();
        table.row();



        table.setPosition(0f, 0f);
        table.setWidth(Vars.VIEWPORT_WIDTH);
        table.setHeight(1f * Vars.VIEWPORT_HEIGHT);
        // table.setFillParent(true);
        // table.pack();

        getStage().addActor(table);
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
