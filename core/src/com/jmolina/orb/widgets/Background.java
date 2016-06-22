package com.jmolina.orb.widgets;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.jmolina.orb.assets.Asset;

/**
 * Background tilable ajustable
 */
public class Background extends BaseGroup {

    private Image image;
    private float offset;
    private final float SPEED = 0f;

    public Background(AssetManager am, float viewportWidth, float viewportHeight) {
        super(am);

        this.offset = 0f;

        Texture texture = getAsset(Asset.UI_BACKGROUND, Texture.class);
        texture.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);

        float tilesX = MathUtils.round(viewportWidth / texture.getWidth());
        float tilesY = MathUtils.round(viewportHeight / texture.getHeight());

        TextureRegion region = new TextureRegion(texture);
        region.setRegion(0, 0, tilesX * texture.getWidth(), tilesY * texture.getHeight());

        this.image = new Image(region);
        image.setSize(region.getRegionWidth(), region.getRegionHeight());
        image.setPosition(0, 0);

        addActor(image);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        this.offset += SPEED;
        setPosition(0f, 0f - offset);
        super.draw(batch, parentAlpha);
    }

    @Override
    public void dispose() {
        super.dispose();
    }

}