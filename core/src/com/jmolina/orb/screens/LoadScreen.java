package com.jmolina.orb.screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.jmolina.orb.var.Utils;

/**
 * Descartada la barra de progreso
 */
public class LoadScreen extends BaseScreen {

    private Image splash;

    public LoadScreen(Texture splashTexture) {
        super();

        splash = new Image(splashTexture);
        splash.setPosition(Utils.xGrid(2), Utils.yGrid(11));
        getStage().addActor(splash);
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
