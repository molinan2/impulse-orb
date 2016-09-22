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

import com.jmolina.orb.interfaces.SuperManager;
import com.jmolina.orb.managers.AssetManager;
import com.jmolina.orb.managers.ScreenManager;
import com.jmolina.orb.var.Var;
import com.jmolina.orb.widgets.ui.ProgressBar;
import com.jmolina.orb.widgets.ui.Splash;

/**
 * Pantalla de carga
 */
public class Load extends BaseScreen {

    /** Primera pantalla después de la pantalla de carga */
    private final ScreenManager.Key FIRST_SCREEN = Var.FIRST_SCREEN;

    /** Splash */
    private Splash splash;

    /** Barra de progreso */
    private ProgressBar bar;

    /** Indicadores de cambio de pantalla en proceso, y de assets cargados */
    private boolean switching, loaded;

    /**
     * Constructor
     *
     * @param superManager SuperManager
     */
    public Load(SuperManager superManager) {
        super(superManager);

        splash = new Splash(getAssetManager());
        splash.setPositionGrid(2, 7.5f);

        bar = new ProgressBar(getAssetManager());
        bar.setPositionGrid(2, 3.5f);

        addMainActor(splash);
        addMainActor(bar);

        switching = false;
        loaded = false;

        getAssetManager().preloadAll(AssetManager.ASSET_CLASS);
    }

    /**
     * El ciclo de render de la pantalla de carga realiza la carga asincrona de assets (que fueron
     * previamente precargados) y actualiza la barra de progreso segun avanza la carga. Una vez esta
     * completa, crea el GameManager y señaliza el cambio de pantalla.
     *
     * @param delta Delta time
     */
    @Override
    public void render(float delta) {
        loaded = getAssetManager().update();
        bar.updateProgress(getAssetManager().getProgress());

        super.render(delta);

        if (loaded && !switching) {
            switching = true;
            getSuperManager().createGameManager();
            switchToScreen(FIRST_SCREEN, Hierarchy.LOWER);
        }
    }

}
