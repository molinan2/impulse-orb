package com.jmolina.orb.widgets.ui;

import com.jmolina.orb.managers.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.jmolina.orb.var.Asset;
import com.jmolina.orb.widgets.BaseGroup;

/**
 * Background tilable ajustable
 */
public class StaticBackground extends BaseGroup {

    private Image image;
    private float offset;
    private final float SPEED = 0f;

    public StaticBackground(AssetManager am, float viewportWidth, float viewportHeight) {
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

}
