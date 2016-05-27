package com.jmolina.orb.widgets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Disposable;
import com.jmolina.orb.groups.BaseGroup;
import com.jmolina.orb.var.Var;

public class LevelCardWidget extends BaseGroup implements Disposable {

    private Image cover;
    private Image title;
    private Image best;
    private Image world;
    private Image background;
    private Image lock;

    private Texture backgroundTexture;
    private Texture lockTexture;

    public LevelCardWidget(Texture coverTexture, Texture titleTexture, Texture bestTexture, Texture worldTexture) {
        super();

        backgroundTexture = new Texture(Gdx.files.internal("card_background.png"));

        cover = new Image(coverTexture);
        title = new Image(titleTexture);
        best = new Image(bestTexture);
        world = new Image(worldTexture);
        background = new Image(backgroundTexture);

        background.setPosition(0f, 0f);
        cover.setPosition(0f, 0F);
        title.setPosition(5.0f * Var.GRID_UNIT, 3.0f * Var.GRID_UNIT);
        best.setPosition(5.0f * Var.GRID_UNIT, 0.75f * Var.GRID_UNIT);
        world.setPosition(5.0f * Var.GRID_UNIT, 0.0f);

        addActor(background);
        addActor(cover);
        addActor(title);
        addActor(best);
        addActor(world);

        setHeight(4.0f * Var.GRID_UNIT);
    }

    @Override
    public void dispose() {
        backgroundTexture.dispose();
    }
}