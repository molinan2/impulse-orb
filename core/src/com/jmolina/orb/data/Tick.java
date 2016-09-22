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
 * Representa el efecto de tick o calentamiento de los elementos calentadores
 */
public class Tick {

    /** Cantidad por tick */
    public float amount;

    /** Periodo del ticking */
    public float period;

    /** Contador de tiempo */
    public float timer;

    /**
     * Constructor
     */
    public Tick() {
        amount = 0f;
        period = 0f;
        timer = 0f;
    }

    /**
     * Indica si el tiempo transcurrido ha superado el tiempo entre ticks
     */
    public boolean expired() {
        return timer > period;
    }

    /**
     * Reinicia el temporizador
     */
    public void reset() {
        timer = 0f;
    }

    /**
     * Incrementa el temporizador
     *
     * @param time Incremento de tiempo
     */
    public void update(float time) {
        this.timer += time;
    }

}
