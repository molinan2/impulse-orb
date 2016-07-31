package com.jmolina.orb.screens;

import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.utils.Align;
import com.jmolina.orb.interfaces.SuperManager;
import com.jmolina.orb.managers.ScreenManager;
import com.jmolina.orb.widgets.ui.Heading;
import com.jmolina.orb.widgets.ui.Stat;

import static com.jmolina.orb.managers.PrefsManager.*;

public class Stats extends Menu {

    private Stat played;
    private Stat distance;
    private Stat fails;
    private Stat successes;
    private Stat avgTimeAlive;
    private Stat avgDistanceAlive;
    private Stat minTimeAlive;
    private Stat maxTimeAlive;
    private Stat minDistanceAlive;
    private Stat maxDistanceAlive;

    private Heading generalHeading;
    private Heading attemptsHeading;
    private Heading timeHeading;
    private Heading distanceHeading;

    private Preferences prefs;

    public Stats(SuperManager superManager, ScreenManager.Key key) {
        super(superManager, key);

        setPreviousScreen(ScreenManager.Key.MAIN);
        setTitle("STATS");
        prefs = superManager.getPrefsManager().getPrefs();

        generalHeading = new Heading(getAssetManager(), "All attempts", Align.center, Heading.Weight.Bold);
        played = new Stat(getAssetManager(), "Time played");
        distance = new Stat(getAssetManager(), "Distance traveled");
        successes = new Stat(getAssetManager(), "Successful attempts");
        fails = new Stat(getAssetManager(), "Failed attempts");

        attemptsHeading = new Heading(getAssetManager(), "Completed attempts", Align.center, Heading.Weight.Bold);
        timeHeading = new Heading(getAssetManager(), "Time alive", Align.center);
        minTimeAlive = new Stat(getAssetManager(), "Minimum");
        avgTimeAlive = new Stat(getAssetManager(), "Average");
        maxTimeAlive = new Stat(getAssetManager(), "Maximum");

        distanceHeading = new Heading(getAssetManager(), "Distance alive", Align.center);
        minDistanceAlive = new Stat(getAssetManager(), "Minimum");
        avgDistanceAlive = new Stat(getAssetManager(), "Average");
        maxDistanceAlive = new Stat(getAssetManager(), "Maximum");

        updateStats();

        add(generalHeading, 0);
        add(played, 0);
        add(distance, 0);
        add(successes, 0);
        add(fails, 0.5f);

        add(attemptsHeading, 0);
        add(timeHeading, 0);
        add(minTimeAlive, 0);
        add(avgTimeAlive, 0);
        add(maxTimeAlive, 0.5f);

        add(distanceHeading, 0);
        add(minDistanceAlive, 0);
        add(avgDistanceAlive, 0);
        add(maxDistanceAlive, 0.5f);
    }

    @Override
    public void dispose() {
        played.dispose();
        distance.dispose();
        fails.dispose();
        successes.dispose();
        avgTimeAlive.dispose();
        avgDistanceAlive.dispose();
        minTimeAlive.dispose();
        maxTimeAlive.dispose();
        minDistanceAlive.dispose();
        maxDistanceAlive.dispose();
        timeHeading.dispose();
        distanceHeading.dispose();
        generalHeading.dispose();
        super.dispose();
    }

    @Override
    public void show() {
        updateStats();
        super.show();
    }

    private void updateStats() {
        played.setValue(prefs.getFloat(STAT_TIME), "s", false);
        distance.setValue(prefs.getFloat(STAT_DISTANCE), "m", false);
        fails.setValue(prefs.getInteger(STAT_FAILS));
        successes.setValue(prefs.getInteger(STAT_SUCCESSES));

        if (prefs.getInteger(STAT_COMPLETED_ATTEMPTS) == 0) {
            minTimeAlive.setNullValue("s");
            maxTimeAlive.setNullValue("s");
            avgTimeAlive.setNullValue("s");
            minDistanceAlive.setNullValue("m");
            maxDistanceAlive.setNullValue("m");
            avgDistanceAlive.setNullValue("m");
        }
        else {
            minTimeAlive.setValue(prefs.getFloat(STAT_MIN_TIME_ALIVE), "s");
            maxTimeAlive.setValue(prefs.getFloat(STAT_MAX_TIME_ALIVE), "s");
            avgTimeAlive.setValue(prefs.getFloat(STAT_AVG_TIME_ALIVE), "s");
            minDistanceAlive.setValue(prefs.getFloat(STAT_MIN_DISTANCE_ALIVE), "m");
            maxDistanceAlive.setValue(prefs.getFloat(STAT_MAX_DISTANCE_ALIVE), "m");
            avgDistanceAlive.setValue(prefs.getFloat(STAT_AVG_DISTANCE_ALIVE), "m");
        }
    }

}
