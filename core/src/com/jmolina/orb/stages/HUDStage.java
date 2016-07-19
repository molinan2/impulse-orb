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

    private final float FADE_TIME = 0.2f;
    private final float ROLLER_TIME = 0.75f;
    private final float DESTROY_TIME = 1.3f;
    private final float OVERLAY_FADE_TIME = ROLLER_TIME * 0.5f;
    private final Interpolation ROLLER_INTERPOLATION = Interpolation.exp5;
    private final Interpolation FADE_INTERPOLATION = Interpolation.pow2;
    private final float HUD_BACKGROUND_X = -6f;
    private final float HUD_BACKGROUND_Y = 16f;

    private Level gameManager; // TODO: Debe ser un GameManager
    private Overlay overlay;
    private HUDBackground background;
    private Gauge gauge;
    private Timer timer;
    private PauseButton pauseButton;
    private MainButton resumeButton, restartButton, leaveButton;
    private Heading fullHeading;
    private Stat distanceStat, fullTimeStat, fullDistanceStat, fullDestroyedStat;


    /**
     * Constructor
     *
     * @param am AssetManager
     * @param gm Level Futuro GameManager
     * @param vp Viewport
     */
    public HUDStage(AssetManager am, Level gm, Viewport vp) {
        super(vp);

        gameManager = gm;
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

        initializeActors();

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

    private void initializeActors() {
        background.setPositionGrid(HUD_BACKGROUND_X, HUD_BACKGROUND_Y);
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

        overlay.addAction(alpha(0));

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
    }

    public void updateTimer() {
        timer.update();
    }

    private void resetTimer() {
        timer.reset();
    }

    public void setGaugeLevel(float level) {
        gauge.setLevel(level);
    }

    public void pause() {
        background.clearActions();
        background.addAction(sequence(
                run(disableTouchables),
                run(pauseWidgets),
                moveTo(Grid.unit(HUD_BACKGROUND_X), Grid.unit(HUD_BACKGROUND_Y), 0),
                moveTo(Grid.unit(HUD_BACKGROUND_X), Grid.unit(-0.25f), ROLLER_TIME, ROLLER_INTERPOLATION),
                moveTo(Grid.unit(HUD_BACKGROUND_X), Grid.unit(-6), 0), // Asegura que no se vean los bordes si leaveGame()
                run(enableWidgetsVisibility),
                run(fadeInWidgets),
                delay(FADE_TIME),
                run(enableTouchables)
        ));
    }

    public void resume(final Runnable callback) {
        background.clearActions();
        background.addAction(sequence(
                run(disableTouchables),
                run(fadeOutWidgets),
                delay(FADE_TIME),
                run(disableWidgetsVisibility),
                moveTo(Grid.unit(HUD_BACKGROUND_X), Grid.unit(-0.25f), 0),
                moveTo(Grid.unit(HUD_BACKGROUND_X), Grid.unit(HUD_BACKGROUND_Y), ROLLER_TIME, ROLLER_INTERPOLATION),
                run(resumeWidgets),
                run(enableTouchables),
                run(callback)
        ));
    }

    /**
     * Secuencia de acciones visuales para reiniciar un juego
     *
     * @param reset Restea todos los elementos a su estado inicial
     * @param unpause Despausa el juego
     */
    public void restart (Runnable reset, Runnable intro, Runnable unpause) {
        background.clearActions();
        background.addAction(sequence(
                run(disableTouchables),
                run(fadeOutWidgets),
                delay(FADE_TIME),
                run(disableWidgetsVisibility),
                moveTo(Grid.unit(HUD_BACKGROUND_X), Grid.unit(-0.25f), 0),
                moveTo(Grid.unit(HUD_BACKGROUND_X), Grid.unit(HUD_BACKGROUND_Y), ROLLER_TIME, ROLLER_INTERPOLATION),
                run(fadeInOverlay),
                delay(OVERLAY_FADE_TIME),
                run(reset),
                run(intro),
                run(fadeOutOverlay),
                delay(Math.max(OVERLAY_FADE_TIME, Level.INTRO_SEQUENCE_TIME)),
                run(resumeWidgets),
                run(enableTouchables),
                run(unpause)
        ));
    }

    public void destroyAndRestart(Runnable destroy, Runnable reset, Runnable intro, Runnable unlock, Runnable unpause) {
        background.clearActions();
        background.addAction(sequence(
                run(disableTouchables),
                run(destroy),
                delay(DESTROY_TIME),
                run(fadeInOverlay),
                delay(OVERLAY_FADE_TIME),
                run(reset),
                run(intro),
                run(fadeOutOverlay),
                delay(Math.max(OVERLAY_FADE_TIME, Level.INTRO_SEQUENCE_TIME)),
                run(resumeWidgets),
                run(enableTouchables),
                run(unlock),
                run(unpause)
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

    public void setGaugeOverload(boolean overloaded) {
        gauge.setOverloaded(overloaded);
    }

    private void resetGauge() {
        gauge.reset();
    }

    public void reset() {
        resetTimer();
        resetGauge();
    }

    /**
     * Temporalmente, devuelve el Level
     */
    private Level getGameManager() {
        return this.gameManager;
    }



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

    private Runnable pauseWidgets = new Runnable() {
        @Override
        public void run() {
            pauseButton.pause();
        }
    };

    private Runnable resumeWidgets = new Runnable() {
        @Override
        public void run() {
            pauseButton.resume();
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


    /**
     * Listeners
     */

    private ClickListener toggleListener = new ClickListener(){
        @Override
        public void clicked(InputEvent event, float x, float y) {
            if (!getGameManager().isGamePaused())
                getGameManager().pauseGame();
            else
                getGameManager().resumeGame();

            event.cancel();
        }
    };

    private ClickListener resumeListener = new ClickListener(){
        @Override
        public void clicked(InputEvent event, float x, float y) {
            if (getGameManager().isGamePaused())
                getGameManager().resumeGame();

            event.cancel();
        }
    };

    private ClickListener restartListener = new ClickListener(){
        @Override
        public void clicked(InputEvent event, float x, float y) {
            getGameManager().restartGame();
            event.cancel();
        }
    };

    private ClickListener leaveListener = new ClickListener(){
        @Override
        public void clicked(InputEvent event, float x, float y) {
            getGameManager().leaveGame();
            event.cancel();
        }
    };

}
