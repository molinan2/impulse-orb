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
 * Nota de pie de pagina con autor y version
 */
public class Notice extends BaseGroup {

    /** Autor y version */
    private Label author, version;

    /**
     * Constructor
     *
     * @param am AssetManager
     */
    public Notice(AssetManager am) {
        super(am);

        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.fontColor = new Color(Var.COLOR_LILAC_DARK);
        labelStyle.font = new BitmapFont(
                Gdx.files.internal(Font.FONT_ROBOTO_REGULAR_30),
                findRegion(Atlas.FONT_ROBOTO_REGULAR_30)
        );

        this.author = new Label(Var.APP_AUTHOR, labelStyle);
        this.author.setTouchable(Touchable.disabled);
        this.author.setPosition(Utils.cell(0), Utils.cell(0));
        this.author.setHeight(Utils.cell(0.5f));
        this.author.setWidth(Utils.cell(10f));
        this.author.setAlignment(Align.left);

        this.version = new Label("v" + Var.APP_VERSION, labelStyle);
        this.version.setTouchable(Touchable.disabled);
        this.version.setPosition(Utils.cell(0), Utils.cell(0));
        this.version.setHeight(Utils.cell(0.5f));
        this.version.setWidth(Utils.cell(10f));
        this.version.setAlignment(Align.right);

        addActor(this.author);
        addActor(this.version);
    }

}
