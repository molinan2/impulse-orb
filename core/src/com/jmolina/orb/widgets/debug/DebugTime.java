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

package com.jmolina.orb.widgets.debug;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.jmolina.orb.managers.AssetManager;
import com.jmolina.orb.var.Atlas;
import com.jmolina.orb.var.Font;
import com.jmolina.orb.var.Utils;
import com.jmolina.orb.var.Var;

/**
 * Class for metering and displaying render time.
 */
public class DebugTime {

    private long nanotimeStart = 0;
    private long nanotimeEnd = 0;
    private long nanotime = 0;
    private long nanotimeAcc = 0;
    private float nanotimeAvg = 0;
    private int frames = 0;
    private SpriteBatch spriteBatch;
    private Label frametime;

    public DebugTime(AssetManager am) {
        spriteBatch = new SpriteBatch();

        Label.LabelStyle style = new Label.LabelStyle();
        style.fontColor = new Color(Var.COLOR_RED);
        style.font = new BitmapFont(
                Gdx.files.internal(Font.FONT_ROBOTO_BOLD_45),
                am.getGameAtlas().findRegion(Atlas.FONT_ROBOTO_BOLD_45)
        );

        frametime = new Label("", style);
        frametime.setPosition(Utils.cell(1), Utils.cell(1));
    }

    /**
     * Comienza a medir el tiempo.
     */
    public void start() {
        nanotimeStart = System.nanoTime();
    }

    /**
     * Termina de medir el tiempo y lo muestra en pantalla.
     */
    public void end() {
        nanotimeEnd = System.nanoTime();
        nanotime = nanotimeEnd - nanotimeStart;
        nanotimeAcc += nanotime;
        frames++;
        nanotimeAvg = (float) (nanotimeAcc) / (float) frames;
        frametime.setText(String.format("%.2f", nanotimeAvg / 1000000f));

        draw();
    }

    /**
     * Dibuja el tiempo en pantalla.
     */
    private void draw() {
        spriteBatch.begin();
        frametime.draw(spriteBatch, 1);
        spriteBatch.end();
    }

}
