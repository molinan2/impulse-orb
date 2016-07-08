package com.jmolina.orb.stages;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.jmolina.orb.managers.AssetManager;
import com.jmolina.orb.widgets.Overlay;
import com.jmolina.orb.widgets.StaticBackground;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;


public class OverlayStage extends Stage {

    private Overlay overlay;
    private StaticBackground staticBackground;

    public OverlayStage (AssetManager am, Viewport vp) {
        super(vp);

        overlay = new Overlay(am);
        overlay.setPositionGrid(0, 0);
        overlay.addAction(alpha(1)); // setVisible?
        overlay.setVisible(true);
        overlay.setTouchable(Touchable.disabled);

        staticBackground = new StaticBackground(am, vp.getWorldWidth(), vp.getWorldHeight());
        staticBackground.setPositionGrid(0, 0);
        staticBackground.setTouchable(Touchable.disabled);
        staticBackground.addAction(alpha(1));
        staticBackground.setVisible(true);

        // addActor(overlay);
        addActor(staticBackground);
    }

}
