package com.jmolina.orb.actors;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;

/**
 * Actor base que implementa los métodos necesarios para que sean efectivas rotaciones, escalado,
 * posicionado, etc.
 */
public class BaseActor extends Actor {

    private TextureRegion region;

    public BaseActor () {
        setPosition(0f, 0f);
        setScale(1.0f, 1.0f);
        setVisible(true);
        setTouchable(Touchable.disabled);
    }

    public BaseActor(TextureRegion region) {
        this();
        setTextureRegion(region);
    }

    public void setTextureRegion(TextureRegion region) {
        this.region = region;
        // texture.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat); // TODO: Desactivado. Ok?

        setSize(region.getRegionWidth(), region.getRegionHeight());
        setBounds(getX(), getY(), getWidth(), getHeight());
        setOrigin(0.5f * getWidth(), 0.5f * getHeight());
    }


    @Override
    public void draw(Batch batch, float parentAlpha) {
        Color color = getColor();
        batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);

        // Metodo completo de dibujado
        batch.draw(region.getTexture(),
                this.getX(), this.getY(),
                this.getOriginX(), this.getOriginY(),
                this.getWidth(), this.getHeight(),
                this.getScaleX(), this.getScaleY(),
                this.getRotation(),
                region.getRegionX(), region.getRegionY(),
                region.getRegionWidth(), region.getRegionHeight(),
                false, false
        );

        // Evita que en algunos casos se modifique el color de la stage
        batch.setColor(color.r, color.g, color.b, color.a);
    }

}
