package com.jmolina.orb.utils;

import java.util.Locale;

public class Time {

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

}
