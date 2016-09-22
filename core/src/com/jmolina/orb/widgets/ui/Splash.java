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

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.jmolina.orb.managers.AssetManager;
import com.jmolina.orb.var.Asset;
import com.jmolina.orb.var.Utils;
import com.jmolina.orb.widgets.BaseGroup;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.forever;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

/**
 * Splash del logo, presentacion para la pantalla de carga
 */
public class Splash extends BaseGroup {

    /** Logo y reflejos del lojo */
    private Image splashBody, splashReflections;

    /**
     * Constructor
     *
     * @param am AssetManager
     */
    public Splash(AssetManager am) {
        super(am);

        splashBody = new Image(getAsset(Asset.UI_SPLASH_BODY, Texture.class));
        splashBody.setSize(Utils.cell(8), Utils.cell(8));
        splashBody.setOrigin(Utils.cell(4), Utils.cell(4));
        splashBody.setPosition(Utils.cell(0), Utils.cell(0));
        splashBody.addAction(forever(sequence(
                Actions.delay(0.75f),
                Actions.rotateBy(2 * 360, 2, Interpolation.pow3)
        )));

        splashReflections = new Image(getAsset(Asset.UI_SPLASH_REFLECTIONS, Texture.class));
        splashReflections.setPosition(Utils.cell(0), Utils.cell(0));

        setSize(Utils.cell(8), Utils.cell(8));

        addActor(splashBody);
        addActor(splashReflections);
    }

}
