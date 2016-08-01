package com.jmolina.orb.widgets.game;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.jmolina.orb.managers.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.jmolina.orb.widgets.BaseGroup;

/**
 * Dibuja una capa basada en tiles, del tamaño del nivel
 *
 * TODO: Recibir las dimensiones del Level y ajustarse a ellas.
 */
public class TiledLayer extends BaseGroup {

    private final int TIMES_X = 4;
    private final int TIMES_Y = 20;

    private Image image;

    public TiledLayer(AssetManager am, TextureRegion region, float viewportWidth, float viewportHeight) {
        super(am);

        // region.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        // region.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        int tilesX = MathUtils.round(viewportWidth / region.getRegionWidth());
        int tilesY = MathUtils.round(viewportHeight / region.getRegionHeight());

        TextureRegion expandedRegion = new TextureRegion(region);
        expandedRegion.setRegion(
                0, 0,
                TIMES_X * tilesX * region.getRegionWidth(),
                TIMES_Y * tilesY * region.getRegionHeight()
        );

        image = new Image(expandedRegion);
        image.setSize(expandedRegion.getRegionWidth(), expandedRegion.getRegionHeight());
        image.setPosition(0, 0);

        addActor(image);
    }

    @Override
    public void draw (Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
    }

}
