package com.jmolina.orb.widgets.ui;

import com.badlogic.gdx.scenes.scene2d.utils.TiledDrawable;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.jmolina.orb.managers.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.jmolina.orb.var.Asset;
import com.jmolina.orb.var.Atlas;
import com.jmolina.orb.widgets.BaseGroup;

/**
 * Background tilable ajustable y estatico
 */
public class StaticBackground extends BaseGroup {

    /** Imagen del background */
    private Image image;

    /** Imagen tilada */
    private TiledDrawable tiledDrawable;

    /**
     * Constructor
     *
     * @param am AssetManager
     * @param viewport Viewport
     */
    public StaticBackground(AssetManager am, Viewport viewport) {
        super(am);

        TextureRegion region = findRegion(Atlas.UI_BACKGROUND);
        tiledDrawable = new TiledDrawable(region);
        tiledDrawable.setMinWidth(viewport.getWorldWidth());
        tiledDrawable.setMinHeight(viewport.getWorldHeight());

        image = new Image(tiledDrawable);
        image.setPosition(0, 0);

        addActor(image);
    }

}
