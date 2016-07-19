package com.jmolina.orb.stages;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.jmolina.orb.managers.AssetManager;
import com.jmolina.orb.widgets.StaticBackground;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

/**
 * Contiene el mismo background de Menu.
 * Para hacer un fundido Menu->Level o Level->Menu.
 */
public class OverlayStage extends Stage {

    private StaticBackground staticBackground;

    public OverlayStage (AssetManager am, Viewport vp) {
        super(vp);

        staticBackground = new StaticBackground(am, vp.getWorldWidth(), vp.getWorldHeight());
        staticBackground.setPositionGrid(0, 0);
        staticBackground.setTouchable(Touchable.disabled);
        staticBackground.addAction(alpha(1));
        staticBackground.setVisible(true);

        addActor(staticBackground);
    }

}
