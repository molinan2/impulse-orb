package com.jmolina.orb.elements;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;

/**
 * Actor base que implementa los métodos necesarios para que sean efectivas rotaciones, escalado,
 * posicionado, etc.
 */
public class BaseActor extends Actor {

    private Texture texture;

    public BaseActor () {
        setPosition(0f, 0f);
        setScale(1.0f, 1.0f);
        setVisible(true);
        setTouchable(Touchable.disabled);
    }

    public BaseActor(Texture t) {
        this();
        setTexture(t);
    }

    public void setTexture (Texture t) {
        texture = t;
        texture.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);

        setSize(texture.getWidth(), texture.getHeight());
        setBounds(getX(), getY(), getWidth(), getHeight());
        setOrigin(0.5f * texture.getWidth(), 0.5f * texture.getHeight());
    }


    @Override
    public void draw(Batch batch, float parentAlpha) {
        Color color = getColor();
        batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);

        /** Metodo completo de dibujado:
         * @param x the x-coordinate in screen space
         * @param y the y-coordinate in screen space
         * @param originX the x-coordinate of the scaling and rotation origin relative to the screen space coordinates
         * @param originY the y-coordinate of the scaling and rotation origin relative to the screen space coordinates
         * @param width the width in pixels
         * @param height the height in pixels
         * @param scaleX the scale of the rectangle around originX/originY in x
         * @param scaleY the scale of the rectangle around originX/originY in y
         * @param rotation the angle of counter clockwise rotation of the rectangle around originX/originY
         * @param srcX the x-coordinate in texel space
         * @param srcY the y-coordinate in texel space
         * @param srcWidth the source with in texels
         * @param srcHeight the source height in texels
         * @param flipX whether to flip the sprite horizontally
         * @param flipY whether to flip the sprite vertically */
        batch.draw(texture,
                this.getX(), this.getY(),
                this.getOriginX(), this.getOriginY(),
                this.getWidth(), this.getHeight(),
                this.getScaleX(), this.getScaleY(),
                this.getRotation(),
                0, 0,
                texture.getWidth(), texture.getHeight(),
                false, false
        );

        // Evita que en algunos casos se modifique el color de la stage
        batch.setColor(color.r, color.g, color.b, color.a);
    }

}
