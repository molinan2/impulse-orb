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

package com.jmolina.orb.listeners;

import com.badlogic.gdx.input.GestureDetector;
import com.jmolina.orb.interfaces.LevelManager;

/**
 * Listener de la entrada gestual en los niveles de juego
 */
public class GestureHandler extends GestureDetector.GestureAdapter {

    private LevelManager levelManager;

    /**
     * Constructor
     *
     * @param levelManager LevelManager
     */
    public GestureHandler(LevelManager levelManager) {
        this.levelManager = levelManager;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        levelManager.freeze();
        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        levelManager.impulse(velocityX, velocityY);
        return false;
    }

}
