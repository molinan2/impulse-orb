package com.jmolina.orb.widgets.game;

import com.badlogic.gdx.scenes.scene2d.utils.TiledDrawable;
import com.jmolina.orb.managers.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.jmolina.orb.widgets.BaseGroup;

/**
 * Dibuja una capa basada en tiles, del tamaño del nivel. Actualmente, se dibuja una dimensión
 * constante que cubre todos los niveles (en lugar de adaptarse a su tamaño).
 */
public class TiledLayer extends BaseGroup {

    private final int TIMES_X = 2;
    private final int TIMES_Y = 16;

    private Image image;
    private TiledDrawable tiledDrawable;

    public TiledLayer(AssetManager am, TextureRegion region, float viewportWidth, float viewportHeight) {
        super(am);

        int viewportTilesX = MathUtils.round(viewportWidth / region.getRegionWidth());
        int viewportTilesY = MathUtils.round(viewportHeight / region.getRegionHeight());

        tiledDrawable = new TiledDrawable(region);
        tiledDrawable.setMinWidth(TIMES_X * viewportTilesX * region.getRegionWidth());
        tiledDrawable.setMinHeight(TIMES_Y * viewportTilesY * region.getRegionHeight());

        image = new Image(tiledDrawable);
        image.setPosition(0, 0);

        addActor(image);
    }

}
