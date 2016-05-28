package com.jmolina.orb.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.jmolina.orb.Orb;
import com.jmolina.orb.widgets.StatWidget;

public class StatsScreen extends MenuScreen {

    private StatWidget played;
    private StatWidget distance;
    private StatWidget fails;
    private StatWidget avgTimeBetweenFails;
    private StatWidget avgDistanceBetweenFails;
    private StatWidget minTimeAlive;
    private StatWidget maxTimeAlive;
    private StatWidget minDistanceAlive;
    private StatWidget maxDistanceAlive;

    private Texture statName;

    public StatsScreen() {
        super();

        setReturningScreen(Orb.Name.MAIN);

        statName = new Texture(Gdx.files.internal("stats_name.png"));

        played = new StatWidget(statName);
        distance = new StatWidget(statName);
        fails = new StatWidget(statName);
        avgTimeBetweenFails = new StatWidget(statName);
        avgDistanceBetweenFails = new StatWidget(statName);
        minTimeAlive = new StatWidget(statName);
        maxTimeAlive = new StatWidget(statName);
        minDistanceAlive = new StatWidget(statName);
        maxDistanceAlive = new StatWidget(statName);

        addRow(played, 0);
        addRow(distance, 0);
        addRow(fails, 0.5f);
        addRow(avgTimeBetweenFails, 0);
        addRow(avgDistanceBetweenFails, 0.5f);
        addRow(minTimeAlive, 0);
        addRow(maxTimeAlive, 0);
        addRow(minDistanceAlive, 0);
        addRow(maxDistanceAlive, 0);
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
