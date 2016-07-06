package com.jmolina.orb.widgets;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.jmolina.orb.assets.Asset;
import com.jmolina.orb.screens.LevelScreen;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

public class Overlay extends BaseGroup {

    private Image image;

    public Overlay(AssetManager am) {
        super(am);

        Texture texture = getAsset(Asset.GAME_SQUARE_WHITE, Texture.class);
        texture.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        float scaleX = LevelScreen.VIEWPORT_WIDTH / texture.getWidth();
        float scaleY = LevelScreen.VIEWPORT_HEIGHT / texture.getHeight();

        image = new Image(texture);
        image.setPosition(0, 0);
        image.setScale(scaleX, scaleY);
        addActor(image);

        setSize(LevelScreen.VIEWPORT_WIDTH, LevelScreen.VIEWPORT_HEIGHT);
    }

}
