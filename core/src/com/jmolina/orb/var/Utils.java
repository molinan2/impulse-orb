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

package com.jmolina.orb.var;

import com.badlogic.gdx.math.Vector2;

import java.text.DecimalFormat;
import java.util.Locale;

/**
 * Utilidades varias
 */
public class Utils {

    private final static float CELL_SIZE = Var.GRID_CELL_SIZE;

    /**
     * Formatea el tiempo en MM:SS.DD
     */
    public static String formatTime(float time) {
        String formattedTime = "";

        int minutes = (int) (time / 60f);
        int seconds = (int) (time - minutes * 60f);
        int decimals = (int) ((time - minutes * 60f - (float) seconds) * 100f);

        String minutesString = String.format(new Locale(""), "%02d", minutes);
        String secondsString = String.format(new Locale(""), "%02d", seconds);
        String decimalsString = String.format(new Locale(""), "%02d", decimals);

        formattedTime = minutesString + ":" + secondsString + "." + decimalsString;

        return formattedTime;
    }

    /**
     * Formatea la distancia con 2 decimales
     *
     * @param distance Distancia
     * @return String Distancia formateada
     */
    public static String formatDistance(float distance) {
        DecimalFormat df = new DecimalFormat("###.##");
        return df.format(distance) + " m";
    }

    /**
     * Calcula la distancia entre 2 puntos
     */
    public static float distance(Vector2 pointA, Vector2 pointB) {
        return pointA.dst(pointB);
    }

    /**
     * Calcula la normal a un vector
     *
     * @param vector Vector
     * @return Vector normal
     */
    public static Vector2 normal(Vector2 vector) {
        Vector2 normal = new Vector2(vector.y, - vector.x);
        normal.nor();

        return normal;
    }

    /**
     * Translates grid cells to pixels
     *
     * @param cells int
     * @return float
     */
    public static float cell(int cells) {
        return (float) cells * CELL_SIZE;
    }

    /**
     * Translates grid cells to pixels
     *
     * @param cells float
     * @return float
     */
    public static float cell(float cells) {
        return cells * CELL_SIZE;
    }

    /**
     * Parte decimal de un número
     *
     * @param number Número en punto flotante
     * @return Parte decimal
     */
    public static float decimalPart(float number) {
        int integerPart = (int) number;
        return number - integerPart;
    }

}
