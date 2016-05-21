package com.jmolina.orb.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.jmolina.orb.var.Vars;
import com.jmolina.orb.widgets.BackButtonWidget;

public class TestScreen extends MenuScreen {

    private BackButtonWidget backButtonWidget;
    private Image image;
    private Group group;

    public TestScreen() {
        super();

        image = new Image(new Texture(Gdx.files.internal("splash.png")));

        backButtonWidget = new BackButtonWidget("Titulo que se lee");

        // getTable().row();
        // getTable().add(backButtonWidget);

        // getStage().addActor(backButtonWidget);
        // getStage().addActor(image);

        Image back = new Image(new Texture(Gdx.files.internal("game_backbutton.png")));
        Image section = new Image(new Texture(Gdx.files.internal("game_section.png")));

        group = new Group();
        getStage().addActor(group);

        group.addActor(back);
        group.addActor(section);
        back.setPosition(0f, 0f);
        //back.setSize(100f, 100f);
        section.setPosition(Vars.gridx(3), 0f);
        //back.setSize(100f, 100f);

        group.setPosition(Vars.gridx(1), Vars.gridy(3));
    }

    @Override
    public void dispose() {
        super.dispose();
    }

}
