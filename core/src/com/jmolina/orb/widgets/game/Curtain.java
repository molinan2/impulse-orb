package com.jmolina.orb.widgets.game;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.jmolina.orb.screens.BaseScreen;
import com.jmolina.orb.var.Asset;
import com.jmolina.orb.screens.Level;
import com.jmolina.orb.widgets.BaseGroup;

public class Curtain extends BaseGroup {

    private Image image;

    public Curtain(AssetManager am) {
        super(am);

        Texture texture = getAsset(Asset.GAME_SQUARE_WHITE, Texture.class);
        texture.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        float scaleX = BaseScreen.VIEWPORT_WIDTH / texture.getWidth();
        float scaleY = BaseScreen.VIEWPORT_HEIGHT / texture.getHeight();

        image = new Image(texture);
        image.setPosition(0, 0);
        image.setScale(scaleX, scaleY);

        setTransform(false);
        addActor(image);
        setSize(BaseScreen.VIEWPORT_WIDTH, BaseScreen.VIEWPORT_HEIGHT);
    }

}
