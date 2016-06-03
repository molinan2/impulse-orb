package com.jmolina.orb.screens;

import com.badlogic.gdx.utils.Align;
import com.jmolina.orb.Orb;
import com.jmolina.orb.managers.OrbAssetManager;
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

    public Stats(OrbAssetManager am) {
        super(am);

        setReturningScreen(Orb.Name.MAIN);
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

        addRow(played, 0);
        addRow(distance, 0);
        addRow(fails, 0.5f);

        addRow(timeHeading, 0);
        addRow(minTimeAlive, 0);
        addRow(maxTimeAlive, 0);
        addRow(avgTimeAlive, 0.5f);

        addRow(distanceHeading, 0);
        addRow(minDistanceAlive, 0);
        addRow(maxDistanceAlive, 0);
        addRow(avgDistanceAlive, 0.5f);
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
