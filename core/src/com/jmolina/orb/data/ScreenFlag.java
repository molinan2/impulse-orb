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

package com.jmolina.orb.data;

import com.jmolina.orb.managers.ScreenManager;
import com.jmolina.orb.screens.BaseScreen;

/**
 * Bandera de señalizacion de cambio de pantalla. Los cambios de pantalla no tienen efecto isntantaneo,
 * sino que cuando se desea cambiar de pantalla, se señaliza a la pantalla actual para que ordene
 * el cambio al final del siguiente ciclo de render.
 */
public class ScreenFlag {

    /** Bandera de señalizacion */
    private boolean flag;

    /** Pantalla destino */
    private ScreenManager.Key screen;

    /** Jerarquia de la pantalla destino respecto de la actual */
    private BaseScreen.Hierarchy hierarchy;

    /**
     * Constructor
     */
    public ScreenFlag() {
        this.flag = false;
    }

    /**
     * Activa la señalizacion
     *
     * @param screen Pantalla destino
     * @param hierarchy Jerarquia de la pantalla destino
     */
    public void enable(ScreenManager.Key screen, BaseScreen.Hierarchy hierarchy) {
        this.flag = true;
        this.screen = screen;
        this.hierarchy = hierarchy;
    }

    /**
     * Indica si la señalizacion esta activa
     */
    public boolean isEnabled() {
        return flag;
    }

    /**
     * Devuelve la pantalla destino
     */
    public ScreenManager.Key getScreen() {
        return isEnabled() ? screen : null;
    }

    /**
     * Devuelve la jerarquia de la pantalla destino respecto de la actual
     */
    public BaseScreen.Hierarchy getHierarchy() {
        return isEnabled() ? hierarchy : null;
    }

}
