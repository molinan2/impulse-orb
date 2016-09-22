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
 * Un intento de completar un nivel del juego. Un intento puede ser fallido (si el orbe se destruyo),
 * exitoso (si el orbe alcanzo la meta) o ninguno de los dos (si se abandono el nivel).
 */
public class Attempt {

    /** Tiempo invertido */
    private float time;

    /** Distancia recorrida */
    private float distance;

    /** Si el Orb resulto destruido en el transcurso */
    private boolean failed;

    /** Si el Orb alcanzo la salida */
    private boolean successful;

    /** Puesto en el ranking correspondiente */
    private int rank;

    /**
     * Constructor
     */
    public Attempt() {
        time = 0f;
        distance = 0f;
        failed = false;
        successful = false;
        rank = 0;
    }

    /**
     * Aumenta el tiempo invertido
     *
     * @param timeIncrement Incremento de tiempo
     */
    public void addTime(float timeIncrement) {
        time += timeIncrement;
    }

    /**
     * Fija el tiempo invertido a un valor dado
     *
     * @param time Tiempo
     */
    public void setTime(float time) {
        this.time = time;
    }

    /**
     * Devuelve el tiempo invertido actual
     */
    public float getTime() {
        return time;
    }

    /**
     * Aumenta la distancia recorrida
     *
     * @param distanceIncrement Incremento de distancia
     */
    public void addDistance(float distanceIncrement) {
        distance += distanceIncrement;
    }

    /**
     * Fija la distancia recorrida a un valor dado
     *
     * @param distance Distancia
     */
    public void setDistance(float distance) {
        this.distance = distance;
    }

    /**
     * Devuelve la distancia recorrida actual
     */
    public float getDistance() {
        return distance;
    }

    /**
     * Indica si el intento fue fallido
     */
    public boolean isFailed() {
        return failed;
    }

    /**
     * Indica si el intento fue exitoso
     */
    public boolean isSuccessful() {
        return successful;
    }

    /**
     * Indica si el intento se completo. Un intento se considera completado si el orbe fue destruido
     * o alcanzo la meta. Los intentos que terminan en abandono NO se consideran completados.
     */
    public boolean isCompleted() {
        return isFailed() || isSuccessful();
    }

    /**
     * Fija si el intento es fallido o no
     *
     * @param failed Indica si es fallido o no
     */
    public void setFailed(boolean failed) {
        this.failed = failed;
    }

    /**
     * Fija si el intento es exitoso o no
     *
     * @param successful Indica si es exitoso o no
     */
    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }

    /**
     * Fija el rango conseguido por el tiempo
     *
     * @param rank Rango
     */
    public void setRank(int rank) {
        this.rank = rank;
    }

    /**
     * Devuelve el rango correspondiente al intento
     */
    public int getRank() {
        return rank;
    }

}
