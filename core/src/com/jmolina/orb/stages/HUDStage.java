package com.jmolina.orb.stages;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.jmolina.orb.interfaces.LevelManager;
import com.jmolina.orb.managers.AssetManager;
import com.jmolina.orb.screens.BaseScreen;
import com.jmolina.orb.screens.Level;
import com.jmolina.orb.var.Utils;
import com.jmolina.orb.widgets.game.Gauge;
import com.jmolina.orb.widgets.game.HUDBackground;
import com.jmolina.orb.widgets.game.Curtain;
import com.jmolina.orb.widgets.game.PauseButton;
import com.jmolina.orb.widgets.game.PauseMenu;
import com.jmolina.orb.widgets.game.Timer;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

/**
 * Stage donde se dibuja el HUD y el menu de pausa.
 */
public class HUDStage extends Stage {

    private final float FADE_TIME = 0.2f;
    private final float ROLLER_TIME = 0.75f;
    private final float DESTROY_TIME = 1.3f;
    private final float OVERLAY_FADE_TIME = ROLLER_TIME * 0.5f;
    private final Interpolation ROLLER_INTERPOLATION = Interpolation.exp5;
    private final Interpolation FADE_INTERPOLATION = Interpolation.pow2;
    private final float HUD_BACKGROUND_X = -6f;
    private final float HUD_BACKGROUND_Y = 16f;
    private final float BACKGROUND_FADE_TIME = 0.5f;

    public static final float INTRO_SEQUENCE_TIME = 1f;

    /** Cortina de fundido de inicio de nivel */
    private Curtain curtain;

    /** Fondo del HUD */
    private HUDBackground background;

    /** Medidor de calir */
    private Gauge gauge;

    /** Cronometro */
    private Timer timer;

    /** Boton de pausa */
    private PauseButton pauseButton;

    /** Menu de pausa */
    private PauseMenu pauseMenu;

    /** Callbacks ejecutables */
    private Runnable enableTouchables, toggleButton, enableMenuVisibility, disableMenuVisibility;

    private LevelManager levelManager;

    /**
     * Constructor
     *  @param levelManager LevelManager
     * @param assetManager AssetManager
     * @param viewport Viewport
     */
    public HUDStage(LevelManager levelManager, AssetManager assetManager, Viewport viewport) {
        super(viewport);

        this.levelManager = levelManager;

        createActors(assetManager);
        addActors();
        createRunnables();

        getRoot().setOrigin(viewport.getWorldWidth() * 0.5f, viewport.getWorldHeight() * 0.5f);
        getRoot().setSize(viewport.getWorldWidth(), viewport.getWorldHeight());
        getRoot().setScale(1f);
        getRoot().setPosition(0f, 0f);
    }

    /**
     * Crea los actores del HUD
     *
     * @param am AssetManager
     */
    private void createActors(AssetManager am) {
        background = new HUDBackground(am);
        timer = new Timer(am);
        pauseButton = new PauseButton(am);
        gauge = new Gauge(am);
        curtain = new Curtain(am);
        pauseMenu = new PauseMenu(am, levelManager);

        background.setPositionGrid(HUD_BACKGROUND_X, HUD_BACKGROUND_Y);
        curtain.setPositionGrid(0, 0);
        timer.setPositionGrid(3, 16.5f);
        pauseButton.setPositionGrid(10, 16.5f);
        gauge.setPositionGrid(0.5f, 16.5f);
        pauseMenu.setPositionGrid(0, 0);

        curtain.addAction(alpha(0));
        pauseMenu.setVisible(false);
        pauseMenu.addAction(alpha(0));

        pauseButton.addListener(getToggleListener());
    }

    /**
     * Añade los actores a la stage
     */
    private void addActors() {
        addActor(curtain);
        addActor(background);
        addActor(timer);
        addActor(pauseButton);
        addActor(gauge);
        addActor(pauseMenu);
    }

    /**
     * Crea los callbacks ejecutables
     */
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

    /**
     * Devuelve un listener de activacion/desactivacion de la pausa al hacer click
     */
    private ClickListener getToggleListener() {
        return new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                pauseButton.clickEffect();
                if (!getLevelManager().isLocked())
                    getLevelManager().pauseGame();
                else
                    getLevelManager().resumeGame();

                event.cancel();
            }
        };
    }

    /**
     * Ejecuta la secuencia de pausa
     */
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

    /**
     * Ejecuta la secuencia de reanudacion
     *
     * @param unlock Callback de desbloqueo del nivel
     */
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

    /**
     * Ejecuta la secuencia de reinicio del nivel
     *
     * @param reset Callback de reseteo de widgets
     * @param intro Callback de secuencia de introduccion del orbe
     * @param unlock Callback de desbloqueo del nivel
     */
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
                delay(Math.max(OVERLAY_FADE_TIME, INTRO_SEQUENCE_TIME)),
                run(toggleButton),
                run(enableTouchables),
                run(unlock)
        ));
    }

    /**
     * Ejecuta la secuencia de destruccion
     *
     * @param destroy Callback de destruccion del orbe
     * @param reset Callback de reseteo de widgets
     * @param intro Callback de secuencia de introduccion del orbe
     * @param unlock Callback de desbloqueo del nivel
     */
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
                delay(Math.max(OVERLAY_FADE_TIME, INTRO_SEQUENCE_TIME)),
                run(toggleButton),
                run(enableTouchables),
                run(unlock)
        ));
    }

    /**
     * Ejecuta la secuencia de primer arranque del nivel
     *
     * @param transitionAction Accion de transicion entre pantallas
     * @param backgroundStage Stage de fondo estatico
     * @param orbIntro Callback de ejecucion de la secuencia de introduccion del orbe
     * @param setAsProcessor Callback de activar la entrada
     * @param unlock Callback de desbloqueo de nivel
     */
    public void init(Action transitionAction, Stage backgroundStage, Runnable orbIntro, Runnable setAsProcessor, Runnable unlock) {
        this.addAction(sequence(
                alpha(0),
                scaleTo(BaseScreen.SIZE_SMALL, BaseScreen.SIZE_SMALL),
                transitionAction,
                Actions.addAction(sequence(alpha(1), fadeOut(BACKGROUND_FADE_TIME)), backgroundStage.getRoot()),
                delay(0.5f * BACKGROUND_FADE_TIME),
                run(orbIntro),
                delay(INTRO_SEQUENCE_TIME),
                run(setAsProcessor),
                run(unlock)
        ));
    }

    /**
     * Actualiza el cronometro
     */
    public void updateTimer() {
        timer.update();
    }

    /**
     * Fija el nivel del medidor de calor
     *
     * @param level Nivel [0,1]
     */
    public void setGaugeLevel(float level) {
        gauge.setLevel(level);
    }

    /**
     * Fija el valor de la estadistica de distancia recorrida en el intento actual
     *
     * @param distance Distancia
     */
    public void setDistanceValue(float distance) {
        pauseMenu.setDistanceValue(distance);
    }

    /**
     * Fija el valor de la estadistica de distancia recorrida total
     *
     * @param distance Distancia
     */
    public void setFullDistanceValue(float distance) {
        pauseMenu.setFullDistanceValue(distance);
    }

    /**
     * Fija el valor de la estadistica de tiempo invertido total
     *
     * @param time Tiempo
     */
    public void setFullTimeValue(float time) {
        pauseMenu.setFullTimeValue(time);
    }

    /**
     * Fija el valor del numero de destrucciones totales
     *
     * @param destroyed Numero de destrucciones
     */
    public void setFullDestroyedValue(int destroyed) {
        pauseMenu.setFullDestroyedValue(destroyed);
    }

    /**
     * Activa o desactiva la sobrecarga del medidor de calor
     *
     * @param overloaded Sobrecarga
     */
    public void setGaugeOverload(boolean overloaded) {
        gauge.setOverloaded(overloaded);
    }

    /**
     * Reinicia los widgets del HUD: cronometro y medidor de calor
     */
    public void reset() {
        timer.reset();
        gauge.reset();
    }

    /**
     * Devuelve el LevelManager
     */
    private LevelManager getLevelManager() {
        return levelManager;
    }

    /**
     * Devuelve el tiempo actual indicado por el cronometro
     */
    public float getTime() {
        return timer.getTime();
    }

}
