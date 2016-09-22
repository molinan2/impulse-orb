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
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Align;
import com.jmolina.orb.managers.AssetManager;
import com.jmolina.orb.var.Asset;
import com.jmolina.orb.var.Utils;
import com.jmolina.orb.var.Var;
import com.jmolina.orb.widgets.BaseGroup;

import java.util.ArrayList;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.alpha;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.delay;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.forever;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

/**
 * Puntuacion (medallas) de un tiempo
 */
public class Rating extends BaseGroup {

    public final static int MIN = 0;
    public final static int MAX = 4;

    private final String NICE_TRY = "Nice try";
    private final String BRONZE = "Bronze!";
    private final String SILVER = "Silver!";
    private final String GOLD = "Gold!";
    private final String DEVELOPER = "You have beaten the developer!";

    /** Medallas */
    private ArrayList<Image> medals;

    /** Conjunto de medallas (rating) */
    private Group rating;

    /** Encabezado de texto */
    private Heading heading;

    /**
     * Constructor
     *
     * @param am AssetManager
     * @param numericRating Rating numerico
     */
    public Rating(AssetManager am, int numericRating) {
        super(am);

        rating = new Group();
        heading = new Heading(getAssetManager(), getText(numericRating), Align.center, Heading.Weight.Regular, Var.COLOR_LILAC_DARK);
        medals = new ArrayList<Image>();

        for (int i=MIN; i<MAX; i++) {
            if (i < numericRating) {
                medals.add(new Image(getAsset(Asset.UI_RATING_YES, Texture.class)));
                medals.get(i).addAction(forever(sequence(
                        alpha(0.5f, 0.25f),
                        alpha(1f, 0.25f),
                        delay(1f)
                )));
            }
            else {
                medals.add(new Image(getAsset(Asset.UI_RATING_NO, Texture.class)));
            }

            medals.get(i).setPosition(i * Utils.cell(1.25f), Utils.cell(0));
            rating.addActor(medals.get(i));
        }

        rating.setPosition(Utils.cell(2.75f), Utils.cell(0));
        heading.setPosition(Utils.cell(0), Utils.cell(1));

        addActor(rating);
        addActor(heading);
        setSize(Utils.cell(10), Utils.cell(2));
    }

    /**
     * Devuelve el texto correspondiente a un valor de rating numero
     *
     * @param rating Rating numero
     */
    private String getText(int rating) {
        rating = MathUtils.clamp(rating, 0, 4);

        if (rating == 0) return NICE_TRY;
        else if (rating == 1) return BRONZE;
        else if (rating == 2) return SILVER;
        else if (rating == 3) return GOLD;
        else if (rating == 4) return DEVELOPER;
        else return "";
    }

    /**
     * Activa o desactiva la visibilidad del encabezado
     *
     * @param visibility Si esta activado
     */
    public void setHeadingVisibility(boolean visibility) {
        heading.setVisible(visibility);
    }

}
