package com.jmolina.orb.widgets.ui;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.jmolina.orb.managers.AssetManager;
import com.jmolina.orb.screens.Level;
import com.jmolina.orb.var.Var;
import com.jmolina.orb.widgets.*;

public class PauseMenu extends BaseGroup {

    private Level level;
    private MainButton resumeButton, restartButton, leaveButton;
    private com.jmolina.orb.widgets.ui.Heading fullHeading;
    private Stat distanceStat, fullTimeStat, fullDistanceStat, fullDestroyedStat;
    private ClickListener resumeListener, restartListener, leaveListener;

    public PauseMenu(AssetManager assetManager, Level level) {
        super(assetManager);

        this.level = level;
        createListeners();
        createActors();
        addActors();
    }

    private Level getLevel() {
        return this.level;
    }

    private void createListeners() {
        resumeListener = new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (getLevel().isLocked())
                    getLevel().resumeGame();

                event.cancel();
            }
        };

        restartListener = new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                getLevel().restartGame();
                event.cancel();
            }
        };

        leaveListener = new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                getLevel().leaveGame();
                event.cancel();
            }
        };
    }

    private void createActors() {
        resumeButton = new MainButton(getAssetManager(), "RESUME", MainButton.Type.Play);
        restartButton = new MainButton(getAssetManager(), "RESTART", MainButton.Type.Default);
        leaveButton = new MainButton(getAssetManager(), "LEAVE", MainButton.Type.Exit);
        distanceStat = new Stat(getAssetManager(), "Distance", 0f, "m");
        fullHeading = new com.jmolina.orb.widgets.ui.Heading(getAssetManager(), "Since start", Align.center, com.jmolina.orb.widgets.ui.Heading.Weight.Bold, Var.COLOR_WHITE);
        fullTimeStat = new Stat(getAssetManager(), "Time", 0, "s");
        fullDistanceStat = new Stat(getAssetManager(), "Distance", 0, "m");
        fullDestroyedStat = new Stat(getAssetManager(), "Destroyed", 0, "times");

        resumeButton.setPositionGrid(2, 12.5f);
        restartButton.setPositionGrid(2, 10);
        leaveButton.setPositionGrid(2, 7.5f);
        distanceStat.setPositionGrid(1, 5);
        fullHeading.setPositionGrid(1, 4);
        fullTimeStat.setPositionGrid(1, 3);
        fullDistanceStat.setPositionGrid(1, 2);
        fullDestroyedStat.setPositionGrid(1, 1);

        distanceStat.setLabelColor(Var.COLOR_WHITE);
        fullTimeStat.setLabelColor(Var.COLOR_WHITE);
        fullDistanceStat.setLabelColor(Var.COLOR_WHITE);
        fullDestroyedStat.setLabelColor(Var.COLOR_WHITE);

        resumeButton.addListener(resumeListener);
        restartButton.addListener(restartListener);
        leaveButton.addListener(leaveListener);
    }

    private void addActors() {
        addActor(resumeButton);
        addActor(restartButton);
        addActor(leaveButton);
        addActor(distanceStat);
        addActor(fullHeading);
        addActor(fullTimeStat);
        addActor(fullDistanceStat);
        addActor(fullDestroyedStat);
    }

    public void setDistanceValue(float distance) {
        distanceStat.setValue(distance, "m");
    }

    public void setFullDistanceValue(float distance) {
        fullDistanceStat.setValue(distance, "m");
    }

    public void setFullTimeValue(float time) {
        fullTimeStat.setValue(time, "s");
    }

    public void setFullDestroyedValue(int destroyed) {
        fullDestroyedStat.setValue(destroyed, "times");
    }

}
