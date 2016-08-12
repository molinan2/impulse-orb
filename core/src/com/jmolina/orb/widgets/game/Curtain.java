package com.jmolina.orb.widgets.game;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.jmolina.orb.managers.AssetManager;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.jmolina.orb.screens.BaseScreen;
import com.jmolina.orb.var.Atlas;
import com.jmolina.orb.widgets.BaseGroup;

public class Curtain extends BaseGroup {

    private Image image;

    public Curtain(AssetManager am) {
        super(am);

        TextureRegion region = findRegion(Atlas.GAME_SQUARE_WHITE);
        float scaleX = BaseScreen.VIEWPORT_WIDTH / region.getRegionWidth();
        float scaleY = BaseScreen.VIEWPORT_HEIGHT / region.getRegionHeight();

        image = new Image(region);
        image.setPosition(0, 0);
        image.setScale(scaleX, scaleY);

        setTransform(false);
        addActor(image);
        setSize(BaseScreen.VIEWPORT_WIDTH, BaseScreen.VIEWPORT_HEIGHT);
    }

}
