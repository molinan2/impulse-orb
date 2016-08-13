package com.jmolina.orb.stages;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.jmolina.orb.managers.AssetManager;
import com.jmolina.orb.widgets.ui.StaticBackground;

public class BackgroundStage extends Stage {

    private StaticBackground staticBackground;

    public BackgroundStage(AssetManager am, Viewport vp) {
        super(vp);
        staticBackground = new StaticBackground(am, vp);
        addActor(staticBackground);
    }

}
