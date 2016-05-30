package com.jmolina.orb.screens;

import com.badlogic.gdx.utils.Align;
import com.jmolina.orb.Orb;
import com.jmolina.orb.widgets.Header;
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

    private Header timeHeader;
    private Header distanceHeader;


    /**
     * TODO
     * Estos datos deberian venir de disco
     */
    public Stats() {
        super();

        setReturningScreen(Orb.Name.MAIN);
        setTitle("STATS");

        played = new Stat("Played", 1420.232f, "s");
        distance = new Stat("Distance", 51.3f, "m");
        fails = new Stat("Fails", 3);

        timeHeader = new Header("Time alive", Align.center);
        minTimeAlive = new Stat("Minimum", 0.9f, "s");
        maxTimeAlive = new Stat("Maximum", 71.22f, "s");
        avgTimeAlive = new Stat("Average", 14.8414f, "s");

        distanceHeader = new Header("Distance alive", Align.center);
        minDistanceAlive = new Stat("Minimum", 3.1f, "m");
        maxDistanceAlive = new Stat("Maximum", 143.4f, "m");
        avgDistanceAlive = new Stat("Average", 31.01f, "m");

        addRow(played, 0);
        addRow(distance, 0);
        addRow(fails, 0.5f);

        addRow(timeHeader, 0);
        addRow(minTimeAlive, 0);
        addRow(maxTimeAlive, 0);
        addRow(avgTimeAlive, 0.5f);

        addRow(distanceHeader, 0);
        addRow(minDistanceAlive, 0);
        addRow(maxDistanceAlive, 0);
        addRow(avgDistanceAlive, 0.5f);
    }

    @Override
    public void dispose() {
        super.dispose();
        played.dispose();
        distance.dispose();
        fails.dispose();
        avgTimeAlive.dispose();
        avgDistanceAlive.dispose();
        minTimeAlive.dispose();
        maxTimeAlive.dispose();
        minDistanceAlive.dispose();
        maxDistanceAlive.dispose();
    }

}
