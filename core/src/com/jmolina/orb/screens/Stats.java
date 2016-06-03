package com.jmolina.orb.screens;

import com.badlogic.gdx.utils.Align;
import com.jmolina.orb.Orb;
import com.jmolina.orb.managers.ScreenManager;
import com.jmolina.orb.widgets.Heading;
import com.jmolina.orb.widgets.Stat;

public class Stats extends Menu {

    private Stat played;
    private Stat distance;
    private Stat fails;
    private Stat avgTimeAlive;
    private Stat avgDistanceAlive;
    private Stat minTimeAlive;
    private Stat maxTimeAlive;
    private Stat minDistanceAlive;
    private Stat maxDistanceAlive;

    private Heading timeHeading;
    private Heading distanceHeading;

    public Stats(Orb orb) {
        super(orb);

        setReturningScreen(ScreenManager.Key.MAIN);
        setTitle("STATS");

        played = new Stat(getAssetManager(), "Played", 1420.232f, "s");
        distance = new Stat(getAssetManager(), "Distance", 51.3f, "m");
        fails = new Stat(getAssetManager(), "Fails", 3);

        timeHeading = new Heading(getAssetManager(), "Time alive", Align.center);
        minTimeAlive = new Stat(getAssetManager(), "Minimum", 0.9f, "s");
        maxTimeAlive = new Stat(getAssetManager(), "Maximum", 71.22f, "s");
        avgTimeAlive = new Stat(getAssetManager(), "Average", 14.8414f, "s");

        distanceHeading = new Heading(getAssetManager(), "Distance alive", Align.center);
        minDistanceAlive = new Stat(getAssetManager(), "Minimum", 3.1f, "m");
        maxDistanceAlive = new Stat(getAssetManager(), "Maximum", 143.4f, "m");
        avgDistanceAlive = new Stat(getAssetManager(), "Average", 31.01f, "m");

        add(played, 0);
        add(distance, 0);
        add(fails, 0.5f);

        add(timeHeading, 0);
        add(minTimeAlive, 0);
        add(maxTimeAlive, 0);
        add(avgTimeAlive, 0.5f);

        add(distanceHeading, 0);
        add(minDistanceAlive, 0);
        add(maxDistanceAlive, 0);
        add(avgDistanceAlive, 0.5f);
    }

    @Override
    public void dispose() {
        played.dispose();
        distance.dispose();
        fails.dispose();
        avgTimeAlive.dispose();
        avgDistanceAlive.dispose();
        minTimeAlive.dispose();
        maxTimeAlive.dispose();
        minDistanceAlive.dispose();
        maxDistanceAlive.dispose();
        timeHeading.dispose();
        distanceHeading.dispose();
        super.dispose();
    }

}
