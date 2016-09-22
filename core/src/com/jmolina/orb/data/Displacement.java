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

/**
 * Guarda información relativa al desplazamiento de un elemento movil ({@link com.jmolina.orb.elements.Movable})
 */
public class Displacement {

    /** Frecuencia del desplazamiento */
    public float frequency = 0f;

    /** Desplazamiento total en el eje XX' */
    public float x = 0f;

    /** Desplazamiento total en el eje YY' */
    public float y = 0f;

    /**
     * Constructor
     */
    public Displacement() {
        frequency = 0f;
        x = 0f;
        y = 0f;
    }

}
