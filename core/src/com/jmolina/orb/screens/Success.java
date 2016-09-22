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

package com.jmolina.orb.screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.jmolina.orb.data.Attempt;
import com.jmolina.orb.interfaces.PlayServices;
import com.jmolina.orb.interfaces.SuperManager;
import com.jmolina.orb.managers.GameManager;
import com.jmolina.orb.managers.ScreenManager;
import com.jmolina.orb.var.Asset;
import com.jmolina.orb.var.Utils;
import com.jmolina.orb.var.Var;
import com.jmolina.orb.widgets.ui.BigText;
import com.jmolina.orb.widgets.ui.Heading;
import com.jmolina.orb.widgets.ui.MainButton;
import com.jmolina.orb.widgets.ui.Rating;
import com.jmolina.orb.widgets.ui.Star;
import com.jmolina.orb.widgets.ui.SuccessCover;
import com.jmolina.orb.widgets.ui.SuccessTitle;

/**
 * Pantalla de exito de un nivel
 */
public class Success extends BaseScreen {

    /** Titulo de exito */
    private SuccessTitle title;

    /** Portada de exito */
    private SuccessCover cover;

    /** Encabezados */
    private Heading timeHeading, distanceHeading;

    /** Tiempo y distancia conseguidos */
    private BigText time, distance;

    /** Boton atras */
    private MainButton button;

    /** Estrella de puesto en el podium */
    private Star podium;

    /** Widget de medallas (rating) */
    private Rating rating;

    /** Rating numerico */
    private int numericRating;

    /**
     * Constructor
     *
     * @param superManager SuperManager
     * @param thisKey Clave de nivel
     * @param label Etiqueta de titulo
     */
    public Success(SuperManager superManager, ScreenManager.Key thisKey, String label) {
        super(superManager);
        setPreviousScreen(ScreenManager.Key.LEVEL_SELECT);
        setThisKey(thisKey);

        Attempt attempt = getGameManager().getCachedAttempt();
        numericRating = getNumericRating(thisKey, attempt.getTime());

        title = new SuccessTitle(getAssetManager(), label);
        cover = new SuccessCover(getAssetManager(), getCoverTexture(thisKey));
        timeHeading = new Heading(getAssetManager(), "Time", Align.center, Heading.Weight.Regular, Var.COLOR_LILAC_DARK);
        distanceHeading = new Heading(getAssetManager(), "Distance", Align.center, Heading.Weight.Regular, Var.COLOR_LILAC_DARK);
        time = new BigText(getAssetManager(), Utils.formatTime(attempt.getTime()));
        distance = new BigText(getAssetManager(), Utils.formatDistance(attempt.getDistance()));

        button = new MainButton(getAssetManager(), "BACK", MainButton.Type.DEFAULT);
        button.addListener(new ClickListener(){
            @Override
            public void clicked (InputEvent event, float x, float y) {
                button.clickEffect();
                getGameManager().play(GameManager.Fx.Back);
                switchToScreen(ScreenManager.Key.LEVEL_SELECT, Hierarchy.HIGHER);
            }
        });

        podium = new Star(getAssetManager(), getGameManager().getCachedRank());
        rating = new Rating(getAssetManager(), numericRating);

        title.setPositionGrid(1, 16.5f);
        cover.setPositionGrid(1, 12f);
        podium.setPositionGrid(7.65f, 12.25f);
        timeHeading.setPositionGrid(1, 10.5f);
        time.setPositionGrid(1, 9f);
        distanceHeading.setPositionGrid(1, 7.5f);
        distance.setPositionGrid(1, 6f);
        rating.setPositionGrid(1, 3.5f);
        button.setPositionGrid(2, 1f);

        addMainActor(title);
        addMainActor(cover);
        addMainActor(podium);
        addMainActor(timeHeading);
        addMainActor(time);
        addMainActor(distanceHeading);
        addMainActor(distance);
        addMainActor(rating);
        addMainActor(button);
    }

    @Override
    public void show() {
        super.show();
        getGameManager().play(GameManager.Track.Success);

        if (numericRating == GameManager.RATING_DEVELOPER)
            unlockFastFuriousAchievement();
    }

    /**
     * Obtiene el rating numérico
     *
     * @param key
     * @param time
     * @return
     */
    private int getNumericRating(ScreenManager.Key key, float time) {
        switch (key) {
            case SUCCESS_1: return getGameManager().getNumericRating(1, time);
            case SUCCESS_2: return getGameManager().getNumericRating(2, time);
            case SUCCESS_3: return getGameManager().getNumericRating(3, time);
            case SUCCESS_4: return getGameManager().getNumericRating(4, time);
            case SUCCESS_5: return getGameManager().getNumericRating(5, time);
            default: return GameManager.RATING_UNRATED;
        }
    }

    /**
     * Devuelve la textura de la portada
     *
     * @param thisKey Clave de pantalla
     */
    private Texture getCoverTexture(ScreenManager.Key thisKey) {
        switch (thisKey) {
            case SUCCESS_1: return getAsset(Asset.UI_SUCCESS_COVER_1, Texture.class);
            case SUCCESS_2: return getAsset(Asset.UI_SUCCESS_COVER_2, Texture.class);
            case SUCCESS_3: return getAsset(Asset.UI_SUCCESS_COVER_3, Texture.class);
            case SUCCESS_4: return getAsset(Asset.UI_SUCCESS_COVER_4, Texture.class);
            case SUCCESS_5: return getAsset(Asset.UI_SUCCESS_COVER_5, Texture.class);
            default: return getAsset(Asset.UI_SUCCESS_COVER_1, Texture.class);
        }
    }

    /**
     * Desbloquea el logro Fast & Furious correspondiente al nivel de esta pantalla
     */
    public void unlockFastFuriousAchievement() {
        switch (getThisKey()) {
            case SUCCESS_1: getGameManager().unlockAchievement(PlayServices.Achievement.FastFurious1); break;
            case SUCCESS_2: getGameManager().unlockAchievement(PlayServices.Achievement.FastFurious2); break;
            case SUCCESS_3: getGameManager().unlockAchievement(PlayServices.Achievement.FastFurious3); break;
            case SUCCESS_4: getGameManager().unlockAchievement(PlayServices.Achievement.FastFurious4); break;
            case SUCCESS_5: getGameManager().unlockAchievement(PlayServices.Achievement.FastFurious5); break;
            default:
        }
    }

}
