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

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.jmolina.orb.managers.AssetManager;
import com.jmolina.orb.var.Atlas;
import com.jmolina.orb.var.Font;
import com.jmolina.orb.var.Utils;
import com.jmolina.orb.var.Var;
import com.jmolina.orb.widgets.BaseGroup;

/**
 * Cronometro
 */
public class Timer extends BaseGroup {

    /** Texto del cronometro */
    private Label label;

    /** Tiempo */
    private float time;

    /**
     * Constructor
     *
     * @param am AssetManager
     */
    public Timer(AssetManager am) {
        super(am);

        Label.LabelStyle style = new Label.LabelStyle();
        style.fontColor = new Color(Var.COLOR_WHITE);
        style.font = new BitmapFont(
                Gdx.files.internal(Font.FONT_ROBOTO_BOLD_90),
                findRegion(Atlas.FONT_ROBOTO_BOLD_90)
        );

        label = new Label("", style);
        label.setTouchable(Touchable.disabled);
        label.setPosition(0, 0);
        label.setHeight(Utils.cell(1.5f));
        label.setWidth(Utils.cell(6));
        label.setAlignment(Align.center);

        addActor(label);

        setTransform(false);
        setHeight(Utils.cell(1.5f));
        reset();
    }

    /**
     * Actualiza el cronometro
     */
    public void update() {
        time += Gdx.graphics.getRawDeltaTime();
        updateLabel();
    }

    /**
     * Devuelve el tiempo contabilizado por el cronometro
     */
    public float getTime () {
        return time;
    }

    /**
     * Reinicia el cronometro
     */
    public void reset() {
        time = 0f;
        updateLabel();
    }

    /**
     * Actualiza el texto del cronometro
     */
    private void updateLabel() {
        label.setText(Utils.formatTime(time));
    }

}
