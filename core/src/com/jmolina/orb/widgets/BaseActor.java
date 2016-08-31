package com.jmolina.orb.widgets;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;

/**
 * Actor base que implementa los métodos necesarios para que sean efectivas rotaciones, escalado,
 * posicionado, alpha blending, etc.
 */
public class BaseActor extends Actor {

    /** Region de textura del actor */
    private TextureRegion region;

    /**
     * Constructor
     */
    public BaseActor() {
        setPosition(0f, 0f);
        setScale(1.0f, 1.0f);
        setVisible(true);
        setTouchable(Touchable.disabled);
    }

    /**
     * Constructor
     *
     * @param region Region de textura del actor
     */
    public BaseActor(TextureRegion region) {
        this();
        setTextureRegion(region);
    }

    /**
     * Fija la region de textura del actor
     *
     * @param region Region de textura
     */
    public void setTextureRegion(TextureRegion region) {
        this.region = region;
        setSize(region.getRegionWidth(), region.getRegionHeight());
        setBounds(getX(), getY(), getWidth(), getHeight());
        setOrigin(0.5f * getWidth(), 0.5f * getHeight());
    }

    /**
     * Dibuja el actor
     *
     * @param batch Batch
     * @param parentAlpha Nivel alpha del padre
     */
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
