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

package com.jmolina.orb.widgets.game;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.jmolina.orb.elements.Magnetic;
import com.jmolina.orb.managers.AssetManager;
import com.jmolina.orb.widgets.BaseGroup;

/**
 * Clase base para dibujar el campo magnetico de los elementos magneticos
 */
public abstract class Field extends BaseGroup {

    protected final float PERIOD = 1f;
    protected final float MAX_ALPHA = 0.5f;
    protected final float SHORT_TIME = 0.1f;

    /** Umbral */
    private float threshold;

    /** Polaridad */
    private Magnetic.Polarity polarity;

    /**
     * Constructor
     *
     * @param am AssetManager
     * @param threshold Umbral
     * @param polarity Polaridad
     */
    public Field(AssetManager am, float threshold, Magnetic.Polarity polarity) {
        super(am);
        setThreshold(threshold);
        setPolarity(polarity);
    }

    /**
     * Crea la acción de atracción o repulsión de una partícula.
     */
    protected Action getParticleAction() {
        switch (getPolarity()) {
            case ATTRACTIVE: return getAttraction();
            case REPULSIVE: return getRepulsion();
            default: return getAttraction();
        }
    }

    /**
     * Crea una acción de atracción repetida infinitamente, para ser aplicada a una partícula.
     */
    protected abstract Action getAttraction();

    /**
     * Crea una acción de repulsión repetida infinitamente, para ser aplicada a una partícula.
     */
    protected abstract Action getRepulsion();

    /**
     * Define el umbral
     *
     * @param threshold Umbral
     */
    private void setThreshold(float threshold) {
        this.threshold = threshold;
    }

    /**
     * Devuelve el umbral
     */
    protected float getThreshold() {
        return threshold;
    }

    /**
     * Define la polaridad
     *
     * @param polarity Polaridad
     */
    private void setPolarity(Magnetic.Polarity polarity) {
        this.polarity = polarity;
    }

    /**
     * Devuelve la polaridad
     */
    protected Magnetic.Polarity getPolarity() {
        return polarity;
    }

}
