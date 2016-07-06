package com.jmolina.orb.stages;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.jmolina.orb.managers.AssetManager;
import com.jmolina.orb.screens.LevelScreen;
import com.jmolina.orb.utils.Grid;
import com.jmolina.orb.widgets.BaseGroup;
import com.jmolina.orb.widgets.Gauge;
import com.jmolina.orb.widgets.HUDBackground;
import com.jmolina.orb.widgets.MainButton;
import com.jmolina.orb.widgets.Overlay;
import com.jmolina.orb.widgets.PauseButton;
import com.jmolina.orb.widgets.Stat;
import com.jmolina.orb.widgets.Timer;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.alpha;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.delay;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeIn;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeOut;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveTo;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.run;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

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
    private Stat traveledStat;
    private Stat destroyedStat;
    private LevelScreen level;

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
            traveledStat.addAction(fadeIn(FADE_TIME, FADE_INTERPOLATION));
            destroyedStat.addAction(fadeIn(FADE_TIME, FADE_INTERPOLATION));
        }
    };

    private Runnable fadeOutWidgets = new Runnable() {
        @Override
        public void run() {
            resumeButton.addAction(fadeOut(FADE_TIME, FADE_INTERPOLATION));
            restartButton.addAction(fadeOut(FADE_TIME, FADE_INTERPOLATION));
            leaveButton.addAction(fadeOut(FADE_TIME, FADE_INTERPOLATION));
            traveledStat.addAction(fadeOut(FADE_TIME, FADE_INTERPOLATION));
            destroyedStat.addAction(fadeOut(FADE_TIME, FADE_INTERPOLATION));
        }
    };

    private Runnable enableWidgetsVisibility = new Runnable() {
        @Override
        public void run() {
            resumeButton.setVisible(true);
            restartButton.setVisible(true);
            leaveButton.setVisible(true);
            traveledStat.setVisible(true);
            destroyedStat.setVisible(true);
        }
    };

    private Runnable disableWidgetsVisibility = new Runnable() {
        @Override
        public void run() {
            resumeButton.setVisible(false);
            restartButton.setVisible(false);
            leaveButton.setVisible(false);
            traveledStat.setVisible(false);
            destroyedStat.setVisible(false);
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
            if (!level.isGamePaused())
                level.pauseGame();
            else
                level.resumeGame();

            event.cancel();
        }
    };

    private ClickListener resumeListener = new ClickListener(){
        @Override
        public void clicked(InputEvent event, float x, float y) {
            if (level.isGamePaused())
                level.resumeGame();

            event.cancel();
        }
    };

    private ClickListener restartListener = new ClickListener(){
        @Override
        public void clicked(InputEvent event, float x, float y) {
            level.resetGame();
            event.cancel();
        }
    };

    private ClickListener leaveListener = new ClickListener(){
        @Override
        public void clicked(InputEvent event, float x, float y) {
            System.out.println("leave");
            event.cancel();
        }
    };


    /**
     * Constructor
     *
     * @param am AssetManager
     * @param levelScreen LevelScreen Futuro GameManager
     * @param vp Viewport
     */
    public HUDStage(AssetManager am, LevelScreen levelScreen, Viewport vp) {
        super(vp);

        level = levelScreen;
        background = new HUDBackground(am);
        timer = new Timer(am);
        pauseButton = new PauseButton(am);
        gauge = new Gauge(am);
        overlay = new Overlay(am);

        resumeButton = new MainButton(am, "RESUME", MainButton.Type.Play);
        restartButton = new MainButton(am, "RESTART", MainButton.Type.Default);
        leaveButton = new MainButton(am, "LEAVE", MainButton.Type.Exit);
        traveledStat = new Stat(am, "Distance traveled", 542.4f, "m");
        destroyedStat = new Stat(am, "Got destroyed", 13, "times");

        background.setPositionGrid(0, 16);
        overlay.setPositionGrid(0, 0);
        timer.setPositionGrid(3, 16.5f);
        pauseButton.setPositionGrid(10, 16.5f);
        gauge.setPositionGrid(0.5f, 16.5f);
        resumeButton.setPositionGrid(2, 11.5f);
        restartButton.setPositionGrid(2, 9);
        leaveButton.setPositionGrid(2, 6.5f);
        traveledStat.setPositionGrid(1, 2);
        destroyedStat.setPositionGrid(1, 1);

        resumeButton.setVisible(false);
        restartButton.setVisible(false);
        leaveButton.setVisible(false);
        traveledStat.setVisible(false);
        destroyedStat.setVisible(false);

        resumeButton.addAction(alpha(0));
        restartButton.addAction(alpha(0));
        leaveButton.addAction(alpha(0));
        traveledStat.addAction(alpha(0));
        destroyedStat.addAction(alpha(0));

        pauseButton.addListener(toggleListener);
        resumeButton.addListener(resumeListener);
        restartButton.addListener(restartListener);
        leaveButton.addListener(leaveListener);

        traveledStat.setLabelColor(BaseGroup.COLOR_WHITE);
        destroyedStat.setLabelColor(BaseGroup.COLOR_WHITE);

        addActor(overlay);
        addActor(background);
        addActor(timer);
        addActor(pauseButton);
        addActor(gauge);
        addActor(resumeButton);
        addActor(restartButton);
        addActor(leaveButton);
        addActor(traveledStat);
        addActor(destroyedStat);
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
     * @param callbackReset Restea todos los elementos a su estado inicial
     * @param callbackUnpause Despausa el juego
     */
    public void restart (Runnable callbackReset, Runnable callbackUnpause) {
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
                run(callbackReset),
                run(fadeOutOverlay),
                delay(OVERLAY_FADE_TIME),
                run(resumeWidgets),
                run(enableTouchables),
                run(callbackUnpause)
        ));
    }

}
