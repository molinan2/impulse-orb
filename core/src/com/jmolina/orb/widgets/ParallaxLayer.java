package com.jmolina.orb.widgets;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.jmolina.orb.assets.Asset;

/**
 * TODO
 * Deberia recibir las dimensiones del Level para ajustarse a ellas. Si las Situation son finalmente
 * de altura fija (18u), bastaria con recibir el numero de situations (corresponderia al MULTIPLIER_Y).
 * Actualmente tiene un valor de Region suficientemente alto
 */
public class ParallaxLayer extends BaseGroup {

    private final int MULTIPLIER_X = 2;
    private final int MULTIPLIER_Y = 10;

    private Image image;

    public ParallaxLayer(AssetManager am, Texture texture, float viewportWidth, float viewportHeight) {
        super(am);

        texture.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        texture.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        int tilesX = MathUtils.round(viewportWidth / texture.getWidth());
        int tilesY = MathUtils.round(viewportHeight / texture.getHeight());

        TextureRegion region = new TextureRegion(texture);
        region.setRegion(
                0,
                0,
                MULTIPLIER_X * tilesX * texture.getWidth(),
                MULTIPLIER_Y * tilesY * texture.getHeight()
        );

        System.out.println("Tiles: " + tilesX + ", " + tilesY);
        System.out.println("Texture: " + texture.getWidth() + ", " + texture.getHeight());
        System.out.println("Region: " + region.getRegionWidth() + ", " + region.getRegionHeight());

        this.image = new Image(region);
        image.setSize(region.getRegionWidth(), region.getRegionHeight());
        image.setPosition(0, 0);

        addActor(image);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
    }

    @Override
    public void dispose() {
        super.dispose();
    }

}
