package com.jmolina.orb.stages;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.jmolina.orb.managers.AssetManager;
import com.jmolina.orb.screens.Level;
import com.jmolina.orb.var.Utils;
import com.jmolina.orb.widgets.game.Gauge;
import com.jmolina.orb.widgets.game.HUDBackground;
import com.jmolina.orb.widgets.game.Curtain;
import com.jmolina.orb.widgets.game.PauseButton;
import com.jmolina.orb.widgets.game.PauseMenu;
import com.jmolina.orb.widgets.game.Timer;

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

    private Level level;
    private Curtain curtain;
    private HUDBackground background;
    private Gauge gauge;
    private Timer timer;
    private PauseButton pauseButton;
    private PauseMenu pauseMenu;
    private Runnable enableTouchables, toggleButton, enableMenuVisibility, disableMenuVisibility;
    private ClickListener toggleListener;

    /**
     * Constructor
     *
     * @param assetManager AssetManager
     * @param level Level Futuro GameManager
     * @param viewport Viewport
     */
    public HUDStage(AssetManager assetManager, Level level, Viewport viewport) {
        super(viewport);

        this.level = level;

        createListeners();
        createActors(assetManager, level);
        addActors();
        createRunnables();

        getRoot().setOrigin(viewport.getWorldWidth() * 0.5f, viewport.getWorldHeight() * 0.5f);
        getRoot().setSize(viewport.getWorldWidth(), viewport.getWorldHeight());
        getRoot().setScale(1f);
        getRoot().setPosition(0f, 0f);
    }

    private void createActors(AssetManager am, Level gm) {
        background = new HUDBackground(am);
        timer = new Timer(am);
        pauseButton = new PauseButton(am);
        gauge = new Gauge(am);
        curtain = new Curtain(am);
        pauseMenu = new PauseMenu(am, gm);

        background.setPositionGrid(HUD_BACKGROUND_X, HUD_BACKGROUND_Y);
        curtain.setPositionGrid(0, 0);
        timer.setPositionGrid(3, 16.5f);
        pauseButton.setPositionGrid(10, 16.5f);
        gauge.setPositionGrid(0.5f, 16.5f);
        pauseMenu.setPositionGrid(0, 0);

        curtain.addAction(alpha(0));
        pauseMenu.setVisible(false);
        pauseMenu.addAction(alpha(0));

        pauseButton.addListener(toggleListener);
    }

    private void addActors() {
        addActor(curtain);
        addActor(background);
        addActor(timer);
        addActor(pauseButton);
        addActor(gauge);
        addActor(pauseMenu);
    }

    private void createRunnables() {
        enableTouchables = new Runnable() {
            @Override
            public void run() {
                pauseButton.setTouchable(Touchable.enabled);
                pauseMenu.setTouchable(Touchable.enabled);
            }
        };

        toggleButton = new Runnable() {
            @Override
            public void run() {
                pauseButton.resume();
            }
        };

        enableMenuVisibility = new Runnable() {
            @Override
            public void run() {
                pauseMenu.setVisible(true);
            }
        };

        disableMenuVisibility = new Runnable() {
            @Override
            public void run() {
                pauseMenu.setVisible(false);
            }
        };
    }

    private void createListeners() {
        toggleListener = new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                pauseButton.clickEffect();
                if (!getLevel().isLocked())
                    getLevel().pauseGame();
                else
                    getLevel().resumeGame();

                event.cancel();
            }
        };
    }

    public void pause() {
        pauseButton.pause();
        pauseButton.setTouchable(Touchable.disabled);
        pauseMenu.setTouchable(Touchable.disabled);
        background.clearActions();
        background.addAction(sequence(
                moveTo(Utils.cell(HUD_BACKGROUND_X), Utils.cell(HUD_BACKGROUND_Y), 0),
                moveTo(Utils.cell(HUD_BACKGROUND_X), Utils.cell(-0.25f), ROLLER_TIME, ROLLER_INTERPOLATION),
                moveTo(Utils.cell(HUD_BACKGROUND_X), Utils.cell(-6), 0), // Asegura que no se vean los bordes si leaveGame()
                run(enableMenuVisibility),
                Actions.addAction(fadeIn(FADE_TIME, FADE_INTERPOLATION), pauseMenu),
                delay(FADE_TIME),
                run(enableTouchables)
        ));
    }

    public void resume(final Runnable unlock) {
        pauseButton.pause();
        pauseButton.setTouchable(Touchable.disabled);
        pauseMenu.setTouchable(Touchable.disabled);
        background.clearActions();
        background.addAction(sequence(
                Actions.addAction(fadeOut(FADE_TIME, FADE_INTERPOLATION), pauseMenu),
                delay(FADE_TIME),
                run(disableMenuVisibility),
                moveTo(Utils.cell(HUD_BACKGROUND_X), Utils.cell(-0.25f), 0),
                moveTo(Utils.cell(HUD_BACKGROUND_X), Utils.cell(HUD_BACKGROUND_Y), ROLLER_TIME, ROLLER_INTERPOLATION),
                run(toggleButton),
                run(enableTouchables),
                run(unlock)
        ));
    }

    public void restart (Runnable reset, Runnable intro, Runnable unlock) {
        pauseButton.pause();
        pauseButton.setTouchable(Touchable.disabled);
        pauseMenu.setTouchable(Touchable.disabled);
        background.clearActions();
        background.addAction(sequence(
                Actions.addAction(fadeOut(FADE_TIME, FADE_INTERPOLATION), pauseMenu),
                delay(FADE_TIME),
                run(disableMenuVisibility),
                moveTo(Utils.cell(HUD_BACKGROUND_X), Utils.cell(-0.25f), 0),
                moveTo(Utils.cell(HUD_BACKGROUND_X), Utils.cell(HUD_BACKGROUND_Y), ROLLER_TIME, ROLLER_INTERPOLATION),
                Actions.addAction(fadeIn(OVERLAY_FADE_TIME, FADE_INTERPOLATION), curtain),
                delay(OVERLAY_FADE_TIME),
                run(reset),
                run(intro),
                Actions.addAction(fadeOut(OVERLAY_FADE_TIME, FADE_INTERPOLATION), curtain),
                delay(Math.max(OVERLAY_FADE_TIME, Level.INTRO_SEQUENCE_TIME)),
                run(toggleButton),
                run(enableTouchables),
                run(unlock)
        ));
    }

    public void destroy(Runnable destroy, Runnable reset, Runnable intro, Runnable unlock) {
        pauseButton.setTouchable(Touchable.disabled);
        pauseMenu.setTouchable(Touchable.disabled);
        background.clearActions();
        background.addAction(sequence(
                run(destroy),
                delay(DESTROY_TIME),
                Actions.addAction(fadeIn(OVERLAY_FADE_TIME, FADE_INTERPOLATION), curtain),
                delay(OVERLAY_FADE_TIME),
                run(reset),
                run(intro),
                Actions.addAction(fadeOut(OVERLAY_FADE_TIME, FADE_INTERPOLATION), curtain),
                delay(Math.max(OVERLAY_FADE_TIME, Level.INTRO_SEQUENCE_TIME)),
                run(toggleButton),
                run(enableTouchables),
                run(unlock)
        ));
    }

    public void init() {

    }

    public void updateTimer() {
        timer.update();
    }

    public void setGaugeLevel(float level) {
        gauge.setLevel(level);
    }

    public void setDistanceValue(float distance) {
        pauseMenu.setDistanceValue(distance);
    }

    public void setFullDistanceValue(float distance) {
        pauseMenu.setFullDistanceValue(distance);
    }

    public void setFullTimeValue(float time) {
        pauseMenu.setFullTimeValue(time);
    }

    public void setFullDestroyedValue(int destroyed) {
        pauseMenu.setFullDestroyedValue(destroyed);
    }

    public void setGaugeOverload(boolean overloaded) {
        gauge.setOverloaded(overloaded);
    }

    public void reset() {
        timer.reset();
        gauge.reset();
    }

    private Level getLevel() {
        return level;
    }

    public float getTime() {
        return timer.getTime();
    }

}
