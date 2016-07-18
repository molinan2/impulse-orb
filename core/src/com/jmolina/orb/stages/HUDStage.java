package com.jmolina.orb.stages;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.jmolina.orb.managers.AssetManager;
import com.jmolina.orb.screens.Level;
import com.jmolina.orb.utils.Grid;
import com.jmolina.orb.var.Var;
import com.jmolina.orb.widgets.Gauge;
import com.jmolina.orb.widgets.HUDBackground;
import com.jmolina.orb.widgets.Heading;
import com.jmolina.orb.widgets.MainButton;
import com.jmolina.orb.widgets.Overlay;
import com.jmolina.orb.widgets.PauseButton;
import com.jmolina.orb.widgets.Stat;
import com.jmolina.orb.widgets.Timer;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

public class HUDStage extends Stage {

    /**
     * Constants
     */

    private final float FADE_TIME = 0.2f;
    private final float ROLLER_TIME = 0.75f;
    private final float OVERLAY_FADE_TIME = ROLLER_TIME * 0.5f;
    private final Interpolation ROLLER_INTERPOLATION = Interpolation.exp5;
    private final Interpolation FADE_INTERPOLATION = Interpolation.pow2;

    /**
     * Fields
     */

    private HUDBackground background;
    private Timer timer;
    private PauseButton pauseButton;
    private Gauge gauge;
    private Overlay overlay;
    private MainButton resumeButton;
    private MainButton restartButton;
    private MainButton leaveButton;
    private Stat distanceStat;
    private Heading fullHeading;
    private Stat fullTimeStat;
    private Stat fullDistanceStat;
    private Stat fullDestroyedStat;
    private Level level;

    /**
     * Runnables
     */

    private Runnable enableTouchables = new Runnable() {
        @Override
        public void run() {
            pauseButton.setTouchable(Touchable.enabled);
            resumeButton.setTouchable(Touchable.enabled);
            restartButton.setTouchable(Touchable.enabled);
            leaveButton.setTouchable(Touchable.enabled);
        }
    };

    private Runnable disableTouchables = new Runnable() {
        @Override
        public void run() {
            pauseButton.setTouchable(Touchable.disabled);
            resumeButton.setTouchable(Touchable.disabled);
            restartButton.setTouchable(Touchable.disabled);
            leaveButton.setTouchable(Touchable.disabled);
        }
    };

    private Runnable resumeWidgets = new Runnable() {
        @Override
        public void run() {
            pauseButton.resume();
        }
    };

    private Runnable pauseWidgets = new Runnable() {
        @Override
        public void run() {
            pauseButton.pause();
        }
    };

    private Runnable fadeInWidgets = new Runnable() {
        @Override
        public void run() {
            resumeButton.addAction(fadeIn(FADE_TIME, FADE_INTERPOLATION));
            restartButton.addAction(fadeIn(FADE_TIME, FADE_INTERPOLATION));
            leaveButton.addAction(fadeIn(FADE_TIME, FADE_INTERPOLATION));
            distanceStat.addAction(fadeIn(FADE_TIME, FADE_INTERPOLATION));
            fullHeading.addAction(fadeIn(FADE_TIME, FADE_INTERPOLATION));
            fullTimeStat.addAction(fadeIn(FADE_TIME, FADE_INTERPOLATION));
            fullDistanceStat.addAction(fadeIn(FADE_TIME, FADE_INTERPOLATION));
            fullDestroyedStat.addAction(fadeIn(FADE_TIME, FADE_INTERPOLATION));
        }
    };

    private Runnable fadeOutWidgets = new Runnable() {
        @Override
        public void run() {
            resumeButton.addAction(fadeOut(FADE_TIME, FADE_INTERPOLATION));
            restartButton.addAction(fadeOut(FADE_TIME, FADE_INTERPOLATION));
            leaveButton.addAction(fadeOut(FADE_TIME, FADE_INTERPOLATION));
            distanceStat.addAction(fadeOut(FADE_TIME, FADE_INTERPOLATION));
            fullHeading.addAction(fadeOut(FADE_TIME, FADE_INTERPOLATION));
            fullTimeStat.addAction(fadeOut(FADE_TIME, FADE_INTERPOLATION));
            fullDistanceStat.addAction(fadeOut(FADE_TIME, FADE_INTERPOLATION));
            fullDestroyedStat.addAction(fadeOut(FADE_TIME, FADE_INTERPOLATION));
        }
    };

    private Runnable enableWidgetsVisibility = new Runnable() {
        @Override
        public void run() {
            resumeButton.setVisible(true);
            restartButton.setVisible(true);
            leaveButton.setVisible(true);
            distanceStat.setVisible(true);
            fullHeading.setVisible(true);
            fullTimeStat.setVisible(true);
            fullDistanceStat.setVisible(true);
            fullDestroyedStat.setVisible(true);
        }
    };

    private Runnable disableWidgetsVisibility = new Runnable() {
        @Override
        public void run() {
            resumeButton.setVisible(false);
            restartButton.setVisible(false);
            leaveButton.setVisible(false);
            distanceStat.setVisible(false);
            fullHeading.setVisible(false);
            fullTimeStat.setVisible(false);
            fullDistanceStat.setVisible(false);
            fullDestroyedStat.setVisible(false);
        }
    };

    private Runnable fadeInOverlay = new Runnable() {
        @Override
        public void run() {
            overlay.addAction(fadeIn(OVERLAY_FADE_TIME, FADE_INTERPOLATION));
        }
    };

    private Runnable fadeOutOverlay = new Runnable() {
        @Override
        public void run() {
            overlay.addAction(fadeOut(OVERLAY_FADE_TIME, FADE_INTERPOLATION));
        }
    };

    private Runnable fullOverlay = new Runnable() {
        @Override
        public void run() {
            overlay.addAction(alpha(1));
        }
    };


    /**
     * Listeners
     */

    private ClickListener toggleListener = new ClickListener(){
        @Override
        public void clicked(InputEvent event, float x, float y) {
            if (!level.isGamePaused()) level.pauseGame();
            else level.resumeGame();

            event.cancel();
        }
    };

    private ClickListener resumeListener = new ClickListener(){
        @Override
        public void clicked(InputEvent event, float x, float y) {
            if (level.isGamePaused()) level.resumeGame();

            event.cancel();
        }
    };

    private ClickListener restartListener = new ClickListener(){
        @Override
        public void clicked(InputEvent event, float x, float y) {
            level.restartGame();
            event.cancel();
        }
    };

    private ClickListener leaveListener = new ClickListener(){
        @Override
        public void clicked(InputEvent event, float x, float y) {
            level.leaveGame();
            event.cancel();
        }
    };


    /**
     * Constructor
     *
     * @param am AssetManager
     * @param level Level Futuro GameManager
     * @param vp Viewport
     */
    public HUDStage(AssetManager am, Level level, Viewport vp) {
        super(vp);

        this.level = level;
        background = new HUDBackground(am);
        timer = new Timer(am);
        pauseButton = new PauseButton(am);
        gauge = new Gauge(am);
        overlay = new Overlay(am);

        resumeButton = new MainButton(am, "RESUME", MainButton.Type.Play);
        restartButton = new MainButton(am, "RESTART", MainButton.Type.Default);
        leaveButton = new MainButton(am, "LEAVE", MainButton.Type.Exit);
        distanceStat = new Stat(am, "Distance", 0f, "m");
        fullHeading = new Heading(am, "Since start", Align.center, Heading.Weight.Bold, Var.COLOR_WHITE);
        fullTimeStat = new Stat(am, "Time", 0, "s");
        fullDistanceStat = new Stat(am, "Distance", 0, "m");
        fullDestroyedStat = new Stat(am, "Destroyed", 0, "times");

        background.setPositionGrid(-6, 16);
        overlay.setPositionGrid(0, 0);
        timer.setPositionGrid(3, 16.5f);
        pauseButton.setPositionGrid(10, 16.5f);
        gauge.setPositionGrid(0.5f, 16.5f);
        resumeButton.setPositionGrid(2, 12.5f);
        restartButton.setPositionGrid(2, 10);
        leaveButton.setPositionGrid(2, 7.5f);
        distanceStat.setPositionGrid(1, 5);
        fullHeading.setPositionGrid(1, 4);
        fullTimeStat.setPositionGrid(1, 3);
        fullDistanceStat.setPositionGrid(1, 2);
        fullDestroyedStat.setPositionGrid(1, 1);

        overlay.addAction(alpha(0)); // todo Borrarlo?

        resumeButton.setVisible(false);
        restartButton.setVisible(false);
        leaveButton.setVisible(false);
        distanceStat.setVisible(false);
        fullHeading.setVisible(false);
        fullTimeStat.setVisible(false);
        fullDistanceStat.setVisible(false);
        fullDestroyedStat.setVisible(false);

        resumeButton.addAction(alpha(0));
        restartButton.addAction(alpha(0));
        leaveButton.addAction(alpha(0));
        distanceStat.addAction(alpha(0));
        fullHeading.addAction(alpha(0));
        fullTimeStat.addAction(alpha(0));
        fullDistanceStat.addAction(alpha(0));
        fullDestroyedStat.addAction(alpha(0));

        pauseButton.addListener(toggleListener);
        resumeButton.addListener(resumeListener);
        restartButton.addListener(restartListener);
        leaveButton.addListener(leaveListener);

        distanceStat.setLabelColor(Var.COLOR_WHITE);
        fullTimeStat.setLabelColor(Var.COLOR_WHITE);
        fullDistanceStat.setLabelColor(Var.COLOR_WHITE);
        fullDestroyedStat.setLabelColor(Var.COLOR_WHITE);

        addActor(overlay);
        addActor(background);
        addActor(timer);
        addActor(pauseButton);
        addActor(gauge);
        addActor(resumeButton);
        addActor(restartButton);
        addActor(leaveButton);
        addActor(distanceStat);
        addActor(fullHeading);
        addActor(fullTimeStat);
        addActor(fullDistanceStat);
        addActor(fullDestroyedStat);

        getRoot().setOrigin(vp.getWorldWidth() * 0.5f, vp.getWorldHeight() * 0.5f);
        getRoot().setScale(1f, 1f);
        getRoot().setSize(vp.getWorldWidth(), vp.getWorldHeight());
        getRoot().setPosition(0f, 0f);
    }

    public void updateTimer() {
        timer.update();
    }

    public void resetTimer() {
        timer.reset();
    }

    public void setGaugeLevel(float level) {
        gauge.setLevel(level);
    }

    public void pause () {
        background.clearActions();
        background.addAction(sequence(
                run(disableTouchables),
                run(pauseWidgets),
                moveTo(0, Grid.unit(16f), 0),
                moveTo(0, Grid.unit(-0.25f), ROLLER_TIME, ROLLER_INTERPOLATION),
                run(enableWidgetsVisibility),
                run(fadeInWidgets),
                delay(FADE_TIME),
                run(enableTouchables)
        ));
    }

    public void resume (final Runnable callback) {
        background.clearActions();
        background.addAction(sequence(
                run(disableTouchables),
                run(fadeOutWidgets),
                delay(FADE_TIME),
                run(disableWidgetsVisibility),
                moveTo(0, Grid.unit(-0.25f), 0),
                moveTo(0, Grid.unit(16f), ROLLER_TIME, ROLLER_INTERPOLATION),
                run(resumeWidgets),
                run(enableTouchables),
                run(callback)
        ));
    }

    /**
     * Secuencia de acciones visuales para reiniciar un juego
     *
     * @param resetCallabck Restea todos los elementos a su estado inicial
     * @param unpauseCallback Despausa el juego
     */
    public void restart (Runnable resetCallabck, Runnable unpauseCallback, Runnable orbIntroCallback) {
        background.clearActions();
        background.addAction(sequence(
                run(disableTouchables),
                run(fadeOutWidgets),
                delay(FADE_TIME),
                run(disableWidgetsVisibility),
                moveTo(0, Grid.unit(-0.25f), 0),
                moveTo(0, Grid.unit(16f), ROLLER_TIME, ROLLER_INTERPOLATION),
                run(fadeInOverlay),
                delay(OVERLAY_FADE_TIME),
                run(resetCallabck),
                run(orbIntroCallback),
                run(fadeOutOverlay),
                delay(Math.max(OVERLAY_FADE_TIME, Level.ORB_INTRO_SEQUENCE_TIME)),
                run(resumeWidgets),
                run(enableTouchables),
                run(unpauseCallback)
        ));
    }

    public void destroyAndRestart(Runnable callbackReset, Runnable callbackDestroyOrb, Runnable callbackUnpause, Runnable callbackUndestroy, Runnable callbackOrbIntro) {
        background.clearActions();
        background.addAction(sequence(
                run(disableTouchables),
                run(callbackDestroyOrb),
                delay(1.3f),
                run(fadeInOverlay),
                delay(OVERLAY_FADE_TIME),
                run(callbackReset),
                run(callbackOrbIntro),
                run(fadeOutOverlay),
                delay(Math.max(OVERLAY_FADE_TIME, Level.ORB_INTRO_SEQUENCE_TIME)),
                run(resumeWidgets),
                run(enableTouchables),
                run(callbackUndestroy),
                run(callbackUnpause)
        ));
    }

    public void leave () {
        background.clearActions();
        background.addAction(sequence(
                run(fadeInOverlay),
                delay(OVERLAY_FADE_TIME)
        ));
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

    public HUDBackground getHUDBackground() {
        return background;
    }

    public void setGaugeOverload(boolean overloaded) {
        gauge.setOverloaded(overloaded);
    }

    public void resetGauge() {
        gauge.reset();
    }

}
