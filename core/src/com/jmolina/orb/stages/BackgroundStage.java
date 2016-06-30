package com.jmolina.orb.stages;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.jmolina.orb.managers.AssetManager;
import com.jmolina.orb.widgets.ScreenBackground;

public class BackgroundStage extends Stage {

    private ScreenBackground screenBackground;

    public BackgroundStage(AssetManager am, Viewport vp) {
        super(vp);
        screenBackground = new ScreenBackground(am, vp.getWorldWidth(), vp.getWorldHeight());
        addActor(screenBackground);
    }

    @Override
    public void dispose() {
        screenBackground.dispose();
        super.dispose();
    }
}
