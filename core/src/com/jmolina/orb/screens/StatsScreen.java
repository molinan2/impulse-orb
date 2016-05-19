package com.jmolina.orb.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class StatsScreen extends MenuScreen {

    // private StatRow played;
    // private StatRow distance;
    // private StatRow deaths;
    // private StatRow timeBetweenDeaths;
    // private StatRow distanceBetweenDeaths;
    // private StatRow maxTimeAlive;
    // private StatRow maxDistanceAlive;
    // private StatRow minTimeAlive;
    // private StatRow minDistanceAlive;

    private Texture playedTexture;
    private Image played;
    private Image distance;
    private Image deaths;

    public StatsScreen() {
        super();

        playedTexture = new Texture(Gdx.files.internal("stat.png"));
        played = new Image(new TextureRegionDrawable(new TextureRegion(playedTexture)));
        setPositionRelative(0.5f, 0.65f, played);
        getStage().addActor(played);

        distance = new Image(new TextureRegionDrawable(new TextureRegion(playedTexture)));
        setPositionRelative(0.5f, 0.55f, distance);
        getStage().addActor(distance);

        deaths = new Image(new TextureRegionDrawable(new TextureRegion(playedTexture)));
        setPositionRelative(0.5f, 0.45f, deaths);
        getStage().addActor(deaths);
    }

    @Override
    public void dispose() {
        playedTexture.dispose();
        super.dispose();
    }
}
