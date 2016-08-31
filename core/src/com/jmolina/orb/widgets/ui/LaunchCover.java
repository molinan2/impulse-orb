package com.jmolina.orb.widgets.ui;

import com.jmolina.orb.managers.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.jmolina.orb.var.Utils;
import com.jmolina.orb.var.Asset;
import com.jmolina.orb.widgets.BaseGroup;

/**
 * Portada de la pantalla de lanzamiento de nivel
 */
public class LaunchCover extends BaseGroup {

    /** Portada y marco */
    private Image cover, frame;

    /**
     * Constructor
     *
     * @param am AssetManager
     * @param texture Textura de la portada
     */
    public LaunchCover(AssetManager am, Texture texture) {
        super(am);

        frame = new Image(getAsset(Asset.UI_LAUNCH_FRAME, Texture.class));
        cover = new Image(texture);

        addActor(cover);
        addActor(frame);

        setSize(Utils.cell(10), Utils.cell(6.5f));
    }

}
