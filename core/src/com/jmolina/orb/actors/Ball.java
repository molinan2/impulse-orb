package com.jmolina.orb.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;

public class Ball extends Actor {

    private Texture texture;

    public Ball() {
        super();

        texture = new Texture(Gdx.files.internal("ball4.png"), true);
        texture.setFilter(Texture.TextureFilter.MipMap, Texture.TextureFilter.MipMap);

        // Inicializacion estandar del actor
        setPosition(0f, 0f);
        setSize(texture.getWidth() * 0.25f, texture.getHeight() * 0.25f);
        setBounds(getX(), getY(), getWidth(), getHeight());
        setOrigin(0.5f * texture.getWidth() * 0.25f, 0.5f * texture.getHeight() * 0.25f);
        setScale(1f, 1f);
        setVisible(true);
        setTouchable(Touchable.disabled);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        fullDraw(batch);
    }

    private void fullDraw(Batch batch) {
        /** Dibujado estandar del actor, con metodo completo
         *
         * Draws a rectangle with the bottom left corner at x,y having the given width and height in pixels. The rectangle is offset by
         * originX, originY relative to the origin. Scale specifies the scaling factor by which the rectangle should be scaled around
         * originX, originY. Rotation specifies the angle of counter clockwise rotation of the rectangle around originX, originY. The
         * portion of the {@link Texture} given by srcX, srcY and srcWidth, srcHeight is used. These coordinates and sizes are given in
         * texels. FlipX and flipY specify whether the texture portion should be flipped horizontally or vertically.
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
         * @param srcWidth the source width in texels
         * @param srcHeight the source height in texels
         * @param flipX whether to flip the sprite horizontally
         * @param flipY whether to flip the sprite vertically */
        batch.draw(texture,
                getX(), getY(),
                getOriginX(), getOriginY(),
                getWidth(), getHeight(),
                getScaleX(), getScaleY(),
                getRotation(),
                0, 0,
                texture.getWidth(), texture.getHeight(),
                false, false
        );

        // batch.draw(texture, getX(), getY());
    }
}
