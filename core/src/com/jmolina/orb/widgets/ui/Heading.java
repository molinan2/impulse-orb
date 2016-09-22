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

public class Heading extends BaseGroup {

    public enum Weight {
        Regular, Medium, Bold
    }

    private Label label;

    public Heading(AssetManager am, String name) {
        this(am, name, Align.left, Weight.Medium, Var.COLOR_GREEN_DARK);
    }

    public Heading(AssetManager am, String name, int align) {
        this(am, name, align, Weight.Medium, Var.COLOR_GREEN_DARK);
    }

    public Heading(AssetManager am, String name, int align, Weight weight) {
        this(am, name, align, weight, Var.COLOR_GREEN_DARK);
    }

    public Heading(AssetManager am, String name, int align, Weight weight, int color) {
        super(am);

        Label.LabelStyle style = new Label.LabelStyle();
        style.fontColor = new Color(color);
        style.font = newBitmapFont(weight);

        label = new Label(name, style);
        label.setTouchable(Touchable.disabled);
        label.setPosition(0f, 0f);
        label.setHeight(Utils.cell(1));
        label.setWidth(Utils.cell(10f));
        label.setAlignment(align);

        addActor(label);
        setTransform(false);
        setHeight(Utils.cell(1));
    }

    public BitmapFont newBitmapFont (Weight weight) {
        switch (weight) {
            case Regular:
                return new BitmapFont(
                    Gdx.files.internal(Font.FONT_ROBOTO_REGULAR_45),
                    findRegion(Atlas.FONT_ROBOTO_REGULAR_45)
                );
            case Medium:
                return new BitmapFont(
                        Gdx.files.internal(Font.FONT_ROBOTO_MEDIUM_45),
                        findRegion(Atlas.FONT_ROBOTO_MEDIUM_45)
                );
            case Bold:
                return new BitmapFont(
                        Gdx.files.internal(Font.FONT_ROBOTO_BOLD_45),
                        findRegion(Atlas.FONT_ROBOTO_BOLD_45)
                );
            default:
                return new BitmapFont(
                        Gdx.files.internal(Font.FONT_ROBOTO_MEDIUM_45),
                        findRegion(Atlas.FONT_ROBOTO_MEDIUM_45)
                );
        }
    }
}