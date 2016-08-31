package com.jmolina.orb.stages;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.jmolina.orb.managers.AssetManager;
import com.jmolina.orb.widgets.ui.StaticBackground;

/**
 * Stage de fondo estatico, utilizada en las pantallas de menu y en el fundido de las pantallas de
 * menu con las de nivel.
 */
public class BackgroundStage extends Stage {

    /** Fondo estatico */
    private StaticBackground staticBackground;

    /**
     * Constructor
     *
     * @param assetManager AssetManager
     * @param viewport Viewport de pantalla
     */
    public BackgroundStage(AssetManager assetManager, Viewport viewport) {
        super(viewport);
        staticBackground = new StaticBackground(assetManager, viewport);
        addActor(staticBackground);
    }

}
