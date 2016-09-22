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

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.jmolina.orb.managers.AssetManager;
import com.jmolina.orb.var.Atlas;
import com.jmolina.orb.var.Utils;
import com.jmolina.orb.widgets.BaseGroup;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.alpha;

/**
 * Boton de pausa del HUD
 */
public class PauseButton extends BaseGroup {

    /** Imagen del boton y marco */
    private Image image, frame;

    /** Imagenes de pausa y reanudacion */
    private TextureRegionDrawable pauseDrawable, resumeDrawable;

    /**
     * Constructor
     *
     * @param am AssetManager
     */
    public PauseButton(AssetManager am) {
        super(am);

        pauseDrawable = new TextureRegionDrawable(findRegion(Atlas.HUD_BUTTON_PAUSE));
        resumeDrawable = new TextureRegionDrawable(findRegion(Atlas.HUD_BUTTON_RESUME));
        image = new Image(pauseDrawable);
        image.setPosition(0f, 0f);

        frame = new Image(findRegion(Atlas.HUD_BUTTON_FRAME));
        frame.setPosition(0, 0);
        frame.addAction(alpha(0));
        frame.act(0);

        addActor(image);
        addActor(frame);

        setFrame(frame);

        setTransform(false);
        setHeight(Utils.cell(1.5f));
        setOrigin(image.getWidth() * 0.5f, image.getHeight() * 0.5f);
    }

    /**
     * Pausa el boton
     */
    public void pause () {
        image.setDrawable(resumeDrawable);
    }

    /**
     * Reanuda el boton
     */
    public void resume () {
        image.setDrawable(pauseDrawable);
    }

}
