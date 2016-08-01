package com.jmolina.orb.actors;

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

        // Metodo completo de dibujado
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
