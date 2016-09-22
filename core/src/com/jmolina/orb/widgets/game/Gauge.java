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

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.jmolina.orb.managers.AssetManager;
import com.jmolina.orb.var.Atlas;
import com.jmolina.orb.var.Utils;
import com.jmolina.orb.widgets.BaseGroup;

/**
 * Medidor de calor del orbe
 */
public class Gauge extends BaseGroup {

    private final float LEVEL_MIN = 0f;
    private final float LEVEL_MAX = 1f;

    /** Elementos del medidor: fondo, barra de nivel, barra de sobrecarga y marco */
    private Image background, bar, overload, frame;

    /** Indicador de sobrecarga */
    private boolean overloaded = false;

    /** Nivel de calor. Toma valores en [0,1] */
    private float level;

    /**
     * Constructor
     *
     * @param am AssetManager
     */
    public Gauge(AssetManager am) {
        super(am);

        background = new Image(findRegion(Atlas.HUD_GAUGE_BACKGROUND));
        bar = new Image(findRegion(Atlas.HUD_GAUGE_BAR));
        overload = new Image(findRegion(Atlas.HUD_GAUGE_OVERLOAD));
        frame = new Image(findRegion(Atlas.HUD_GAUGE_FRAME));

        background.setPosition(0f, 0f);
        bar.setPosition(0f, 0f);
        overload.setPosition(0f, 0f);
        frame.setPosition(0f, 0f);

        overload.addAction(Actions.alpha(0));

        //  addActor(background);
        addActor(bar);
        addActor(overload);
        addActor(frame);

        setTransform(false);
        setSize(Utils.cell(1), Utils.cell(1.5f));
        setOrigin(background.getWidth() * 0.5f, background.getHeight() * 0.5f);

        reset();
    }

    /**
     * Reinicia el nivel del medidor
     */
    public void reset () {
        level = 0f;
        bar.setScaleY(level);
        overload.addAction(Actions.alpha(0));
    }

    /**
     * Incrementa el nivel de medidor
     *
     * @param increment Incremento (entre 0 y 1)
     */
    public void increase(float increment) {
        level = MathUtils.clamp(level + increment, LEVEL_MIN, LEVEL_MAX);
        bar.setScaleY(level);
    }

    /**
     * Decrementa el nivel del medidor
     *
     * @param decrement Decremento
     */
    public void decrease (float decrement) {
        increase(-decrement);
    }

    /**
     * Fija el nivel de calor
     *
     * @param level Nivel
     */
    public void setLevel (float level) {
        this.level = MathUtils.clamp(level, LEVEL_MIN, LEVEL_MAX);
        bar.setScaleY(level);
    }

    /**
     * Fija la sobrecarga activada o desactivada
     *
     * @param overloaded Si esta activada
     */
    public void setOverloaded(boolean overloaded) {
        this.overloaded = overloaded;
        if (this.overloaded)
            overload.addAction(Actions.fadeIn(0.35f));
        else
            overload.addAction(Actions.fadeOut(0.35f));
    }

}
