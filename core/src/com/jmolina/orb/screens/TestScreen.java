package com.jmolina.orb.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.jmolina.orb.var.Utils;
import com.jmolina.orb.widgets.BackButtonTableWidget;
import com.jmolina.orb.widgets.BackTitleGroup;

public class TestScreen extends MenuScreen {

    private BackButtonTableWidget backButtonTableWidget;
    private Image image;
    private BackTitleGroup backTitleGroup;

    public TestScreen() {
        super();

        image = new Image(new Texture(Gdx.files.internal("splash.png")));

        backButtonTableWidget = new BackButtonTableWidget("Titulo que se lee");

        // getTable().row();
        // getTable().add(backButtonTableWidget);

        // getStage().addActor(backButtonTableWidget);
        // getStage().addActor(image);

        Image back = new Image(new Texture(Gdx.files.internal("game_backbutton.png")));
        Image section = new Image(new Texture(Gdx.files.internal("game_section.png")));

        backTitleGroup = new BackTitleGroup();
        backTitleGroup.setPosition(Utils.xGrid(1), Utils.yGrid(3));
        getStage().addActor(backTitleGroup);
    }

    @Override
    public void dispose() {
        super.dispose();
        backTitleGroup.dispose();
    }

}
