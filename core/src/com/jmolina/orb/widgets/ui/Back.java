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
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.jmolina.orb.managers.AssetManager;
import com.jmolina.orb.var.Asset;
import com.jmolina.orb.widgets.BaseGroup;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.alpha;

/**
 * Titulo de pantalla con boton atras
 */
public class Back extends BaseGroup {

    /** Imagen del botonn y marco*/
    private Image image, frame;

    /**
     * Constructor
     *
     * @param am AssetManager
     */
    public Back(AssetManager am) {
        super(am);

        image = new Image(getAsset(Asset.UI_BACK, Texture.class));
        image.setPosition(0, 0);
        frame = new Image(getAsset(Asset.UI_BACK_FRAME, Texture.class));
        frame.setPosition(0, 0);
        frame.addAction(alpha(0));
        frame.act(0);

        setFrame(frame);
        addActor(image);
        addActor(frame);
    }

}
