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

package com.jmolina.orb.widgets.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.jmolina.orb.managers.AssetManager;
import com.jmolina.orb.var.Atlas;
import com.jmolina.orb.var.Font;
import com.jmolina.orb.var.Utils;
import com.jmolina.orb.var.Var;
import com.jmolina.orb.widgets.BaseGroup;

/**
 * Una fila de la clasificacion
 */
public class LadderRow extends BaseGroup {

    /** Etiquetas de rango, tiempo y usuario */
    private Label rankLabel, timeLabel, userLabel;

    /** Rating (medallas) del tiempo */
    private Rating rating;

    /**
     * Constructor
     *
     * @param am AssetManager
     * @param rank Rango
     * @param time Tiempo
     * @param numericRating Rating numerico (medallas)
     */
    public LadderRow(AssetManager am, int rank, float time, int numericRating) {
        super(am);

        Label.LabelStyle regular = new Label.LabelStyle();
        Label.LabelStyle strong = new Label.LabelStyle();
        regular.fontColor = new Color(Var.COLOR_LILAC_DARK);
        strong.fontColor = new Color(Var.COLOR_LILAC_DARK);
        regular.font = new BitmapFont(
                Gdx.files.internal(Font.FONT_ROBOTO_REGULAR_30),
                findRegion(Atlas.FONT_ROBOTO_REGULAR_30)
        );
        strong.font = new BitmapFont(
                Gdx.files.internal(Font.FONT_ROBOTO_BOLD_30),
                findRegion(Atlas.FONT_ROBOTO_BOLD_30)
        );

        rankLabel = new Label(String.valueOf(rank), strong);
        rankLabel.setPosition(Utils.cell(0), Utils.cell(0));
        rankLabel.setSize(Utils.cell(1), Utils.cell(0.5f));
        rankLabel.setAlignment(Align.left);

        timeLabel = new Label(formatTime(time), regular);
        timeLabel.setPosition(Utils.cell(1), Utils.cell(0));
        timeLabel.setSize(Utils.cell(2), Utils.cell(0.5f));
        timeLabel.setAlignment(Align.right);

        userLabel = new Label("You", strong);
        userLabel.setPosition(Utils.cell(4), Utils.cell(0));
        userLabel.setSize(Utils.cell(3.5f), Utils.cell(0.5f));
        userLabel.setAlignment(Align.left);
        userLabel.setEllipsis(true);

        rating = new Rating(getAssetManager(), numericRating);
        rating.setPositionGrid(5, 0);
        rating.setScale(0.5f);
        rating.setHeadingVisibility(false);

        addActor(rankLabel);
        addActor(timeLabel);

        if (time > 0) {
            addActor(userLabel);
            addActor(rating);
        }

        setHeight(Utils.cell(0.5f));
    }

    /**
     * Devuelve el tiempo formateado
     *
     * @param time Tiempo
     */
    private String formatTime(float time) {
        if (time > 0)
            return Utils.formatTime(time);
        else
            return "--";
    }

}