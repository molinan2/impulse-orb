package com.jmolina.orb.widgets.game;

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

    private final int TIMES_X = 4;
    private final int TIMES_Y = 20;

    private Image image;

    public TiledLayer(AssetManager am, Texture texture, float viewportWidth, float viewportHeight) {
        super(am);

        texture.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        texture.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        int tilesX = MathUtils.round(viewportWidth / texture.getWidth());
        int tilesY = MathUtils.round(viewportHeight / texture.getHeight());

        TextureRegion region = new TextureRegion(texture);
        region.setRegion(
                0, 0,
                TIMES_X * tilesX * texture.getWidth(),
                TIMES_Y * tilesY * texture.getHeight()
        );

        image = new Image(region);
        image.setSize(region.getRegionWidth(), region.getRegionHeight());
        image.setPosition(0, 0);

        addActor(image);
    }

}
