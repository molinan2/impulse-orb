package com.jmolina.orb.widgets.game;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.jmolina.orb.interfaces.LevelManager;
import com.jmolina.orb.managers.AssetManager;
import com.jmolina.orb.screens.Level;
import com.jmolina.orb.var.Var;
import com.jmolina.orb.widgets.*;
import com.jmolina.orb.widgets.ui.Heading;
import com.jmolina.orb.widgets.ui.MainButton;
import com.jmolina.orb.widgets.ui.Stat;

public class PauseMenu extends BaseGroup {

    private LevelManager levelManager;
    private MainButton resumeButton, restartButton, leaveButton;
    private Heading fullHeading;
    private Stat distanceStat, fullTimeStat, fullDistanceStat, fullDestroyedStat;

    public PauseMenu(AssetManager assetManager, LevelManager levelManager) {
        super(assetManager);

        this.levelManager = levelManager;
        createActors();
        addActors();
    }

    private LevelManager getLevelManager() {
        return this.levelManager;
    }

    private void createActors() {
        resumeButton = new MainButton(getAssetManager(), "RESUME", MainButton.Type.SUCCESS);
        restartButton = new MainButton(getAssetManager(), "RESTART", MainButton.Type.WARNING);
        leaveButton = new MainButton(getAssetManager(), "LEAVE", MainButton.Type.DANGER);
        distanceStat = new Stat(getAssetManager(), "Distance", 0f, "m");
        fullHeading = new Heading(getAssetManager(), "Since start", Align.center, Heading.Weight.Bold, Var.COLOR_WHITE);
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

        resumeButton.addListener(getResumeListener());
        restartButton.addListener(getRestartListener());
        leaveButton.addListener(getLeaveListener());
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

    private ClickListener getResumeListener() {
        return new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                resumeButton.clickEffect();
                if (getLevelManager().isLocked())
                    getLevelManager().resumeGame();

                event.cancel();
            }
        };
    }

    private ClickListener getRestartListener() {
        return new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                restartButton.clickEffect();
                getLevelManager().restartGame();
                event.cancel();
            }
        };
    }

    private ClickListener getLeaveListener() {
        return new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                leaveButton.clickEffect();
                getLevelManager().leaveGame();
                event.cancel();
            }
        };
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