package com.jmolina.orb.utils;

import com.badlogic.gdx.math.Vector2;
import java.util.Locale;

public class Utils {

    /**
     * Formatea el tiempo en MM:SS.DD
     */
    public static String formatTime(float time) {
        String formattedDistance = "";

        int minutes = (int) (time / 60f);
        int seconds = (int) (time - minutes * 60f);
        int decimals = (int) ((time - minutes * 60f - (float) seconds) * 100f);

        String minutesString = String.format(new Locale(""), "%02d", minutes);
        String secondsString = String.format(new Locale(""), "%02d", seconds);
        String decimalsString = String.format(new Locale(""), "%02d", decimals);

        formattedDistance = minutesString + ":" + secondsString + "." + decimalsString;

        return formattedDistance;
    }

    /**
     * Distancia entre 2 puntos
     */
    public static float distance(Vector2 pointA, Vector2 pointB) {
        Vector2 inter = new Vector2(
                pointB.x - pointA.x,
                pointB.y - pointA.y
        );

        return (float) Math.sqrt(Math.pow(inter.x, 2) + Math.pow(inter.y, 2));
    }

}
