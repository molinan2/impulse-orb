/*
 * IMPULSE ORB
 * Copyright (C) 2016 Juan M. Molina
 *
 * This file is part of the IMPULSE ORB source code.
 *
 * IMPULSE ORB is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * IMPULSE ORB is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.jmolina.orb.widgets;

import com.badlogic.gdx.graphics.Color;
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
