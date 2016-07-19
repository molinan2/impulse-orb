package com.jmolina.orb.data;

import com.badlogic.gdx.Preferences;
import com.jmolina.orb.managers.ScreenManager;

import java.util.ArrayList;
import java.util.Collections;

import static com.jmolina.orb.managers.PrefsManager.*;

public class PersonalTimes {

    private String LADDER_1;
    private String LADDER_2;
    private String LADDER_3;

    private Preferences prefs;
    private ScreenManager.Key screenKey;
    private ArrayList<Float> times;

    public PersonalTimes(Preferences prefs, ScreenManager.Key screenKey) {
        this.prefs = prefs;
        this.screenKey = screenKey;
        this.times = new ArrayList<Float>();

        detectLevel();
        load();
    }

    /**
     * Carga en orden los tiempos desde el disco
     */
    private void load() {
        if (prefs.contains(LADDER_1)) times.add(prefs.getFloat(LADDER_1));
        if (prefs.contains(LADDER_2)) times.add(prefs.getFloat(LADDER_2));
        if (prefs.contains(LADDER_3)) times.add(prefs.getFloat(LADDER_3));
        Collections.sort(times);
    }

    /**
     * Añade un nuevo tiempo y lo ordena dentro de la lista de tiempos
     */
    public void addAttempt(Attempt attempt) {
        if (attempt.isSuccessful()) {
            times.add(attempt.getTime());
            Collections.sort(times);
        }
    }

    /**
     * Guarda en disco los 3 primeros tiempos
     */
    public void put() {
        if (times.size() > 0) prefs.putFloat(LADDER_1, times.get(0));
        if (times.size() > 1) prefs.putFloat(LADDER_2, times.get(1));
        if (times.size() > 2) prefs.putFloat(LADDER_3, times.get(2));
    }

    private void detectLevel() {
        switch (screenKey) {
            case LEVEL_1:
                LADDER_1 = LADDER_1_1;
                LADDER_2 = LADDER_1_2;
                LADDER_3 = LADDER_1_3;
                break;
            case LEVEL_2:
                LADDER_1 = LADDER_2_1;
                LADDER_2 = LADDER_2_2;
                LADDER_3 = LADDER_2_3;
                break;
            case LEVEL_3:
                LADDER_1 = LADDER_3_1;
                LADDER_2 = LADDER_3_2;
                LADDER_3 = LADDER_3_3;
                break;
            case LEVEL_4:
                LADDER_1 = LADDER_4_1;
                LADDER_2 = LADDER_4_2;
                LADDER_3 = LADDER_4_3;
                break;
            case LEVEL_5:
                LADDER_1 = LADDER_5_1;
                LADDER_2 = LADDER_5_2;
                LADDER_3 = LADDER_5_3;
                break;
            default:
        }
    }

}
