package com.jmolina.orb.stages;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.jmolina.orb.managers.AssetManager;
import com.jmolina.orb.screens.Level;
import com.jmolina.orb.utils.Grid;
import com.jmolina.orb.widgets.Gauge;
import com.jmolina.orb.widgets.HUDBackground;
import com.jmolina.orb.widgets.Overlay;
import com.jmolina.orb.widgets.PauseButton;
import com.jmolina.orb.widgets.PauseContent;
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
    private PauseContent pauseContent;
    private Runnable enableTouchables, disableTouchables, pauseWidgets, resumeWidgets,
            fadeInWidgets, fadeOutWidgets, enableWidgetsVisibility, disableWidgetsVisibility,
            fadeInOverlay, fadeOutOverlay;
    private ClickListener toggleListener, resumeListener, restartListener, leaveListener;

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

        createListeners();
        createActors(am, gm);
        addActors();
        createRunnables();

        getRoot().setOrigin(vp.getWorldWidth() * 0.5f, vp.getWorldHeight() * 0.5f);
        getRoot().setScale(1f, 1f);
        getRoot().setSize(vp.getWorldWidth(), vp.getWorldHeight());
        getRoot().setPosition(0f, 0f);
    }

    private void createActors(AssetManager am, Level gm) {
        background = new HUDBackground(am);
        timer = new Timer(am);
        pauseButton = new PauseButton(am);
        gauge = new Gauge(am);
        overlay = new Overlay(am);
        pauseContent = new PauseContent(am, gm);

        background.setPositionGrid(HUD_BACKGROUND_X, HUD_BACKGROUND_Y);
        overlay.setPositionGrid(0, 0);
        timer.setPositionGrid(3, 16.5f);
        pauseButton.setPositionGrid(10, 16.5f);
        gauge.setPositionGrid(0.5f, 16.5f);
        pauseContent.setPositionGrid(0, 0);

        overlay.addAction(alpha(0));
        pauseContent.setVisible(false);
        pauseContent.addAction(alpha(0));

        pauseButton.addListener(toggleListener);
    }

    private void addActors() {
        addActor(overlay);
        addActor(background);
        addActor(timer);
        addActor(pauseButton);
        addActor(gauge);
        addActor(pauseContent);
    }

    private void createRunnables() {
        enableTouchables = new Runnable() {
            @Override
            public void run() {
                pauseButton.setTouchable(Touchable.enabled);
                pauseContent.setTouchable(Touchable.enabled);
            }
        };

        disableTouchables = new Runnable() {
            @Override
            public void run() {
                pauseButton.setTouchable(Touchable.disabled);
                pauseContent.setTouchable(Touchable.disabled);
            }
        };

        pauseWidgets = new Runnable() {
            @Override
            public void run() {
                pauseButton.pause();
            }
        };

        resumeWidgets = new Runnable() {
            @Override
            public void run() {
                pauseButton.resume();
            }
        };

        fadeInWidgets = new Runnable() {
            @Override
            public void run() {
                pauseContent.addAction(fadeIn(FADE_TIME, FADE_INTERPOLATION));
            }
        };

        fadeOutWidgets = new Runnable() {
            @Override
            public void run() {
                pauseContent.addAction(fadeOut(FADE_TIME, FADE_INTERPOLATION));
            }
        };

        enableWidgetsVisibility = new Runnable() {
            @Override
            public void run() {
                pauseContent.setVisible(true);
            }
        };

        disableWidgetsVisibility = new Runnable() {
            @Override
            public void run() {
                pauseContent.setVisible(false);
            }
        };

        fadeInOverlay = new Runnable() {
            @Override
            public void run() {
                overlay.addAction(fadeIn(OVERLAY_FADE_TIME, FADE_INTERPOLATION));
            }
        };

        fadeOutOverlay = new Runnable() {
            @Override
            public void run() {
                overlay.addAction(fadeOut(OVERLAY_FADE_TIME, FADE_INTERPOLATION));
            }
        };
    }

    private void createListeners() {
        toggleListener = new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (!getGameManager().isGamePaused())
                    getGameManager().pauseGame();
                else
                    getGameManager().resumeGame();

                event.cancel();
            }
        };
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
        pauseContent.setDistanceValue(distance);
    }

    public void setFullDistanceValue(float distance) {
        pauseContent.setFullDistanceValue(distance);
    }

    public void setFullTimeValue(float time) {
        pauseContent.setFullTimeValue(time);
    }

    public void setFullDestroyedValue(int destroyed) {
        pauseContent.setFullDestroyedValue(destroyed);
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

}
