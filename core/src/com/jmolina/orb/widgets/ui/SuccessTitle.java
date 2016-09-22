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
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.jmolina.orb.managers.AssetManager;
import com.jmolina.orb.var.Asset;
import com.jmolina.orb.var.Atlas;
import com.jmolina.orb.var.Font;
import com.jmolina.orb.var.Utils;
import com.jmolina.orb.var.Var;
import com.jmolina.orb.widgets.BaseGroup;

/**
 * Titulo de una pantalla exito
 */
public class SuccessTitle extends BaseGroup {

    /** Texto */
    private Label label;

    /** Fondo */
    private Image background;

    /**
     * Constructor
     *
     * @param am AssetManager
     * @param title Titulo
     */
    public SuccessTitle(AssetManager am, String title) {
        super(am);

        background = new Image(getAssetManager().get(Asset.UI_SUCCESS_TITLE_BACKGROUND, Texture.class));
        background.setPosition(0f, 0f);

        Label.LabelStyle style = new Label.LabelStyle();
        style.fontColor = new Color(Var.COLOR_WHITE);
        style.font = new BitmapFont(
                Gdx.files.internal(Font.FONT_ROBOTO_BOLD_45),
                findRegion(Atlas.FONT_ROBOTO_BOLD_45)
        );

        label = new Label(title.toUpperCase(), style);
        label.setSize(Utils.cell(10), Utils.cell(1));
        label.setPosition(Utils.cell(0), Utils.cell(0));
        label.setAlignment(Align.center);

        addActor(background);
        addActor(label);

        setSize(Utils.cell(10), Utils.cell(1));
    }

}
