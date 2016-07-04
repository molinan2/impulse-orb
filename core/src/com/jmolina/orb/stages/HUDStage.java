package com.jmolina.orb.stages;

import com.badlogic.gdx.Gdx;
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

    private final float COOLING_TICK = Gdx.graphics.getDeltaTime() * 0.1f;

    private HUDBackground background;
    private Timer timer;
    private PauseButton pause;
    private Gauge gauge;
    private MainButton resume;
    private MainButton restart;
    private MainButton leave;
    private Stat traveled;
    private Stat destroyed;
    private LevelScreen level;

    /**
     * Runnables
     */

    private Runnable enableTouchables = new Runnable() {
        @Override
        public void run() {
            pause.setTouchable(Touchable.enabled);
            resume.setTouchable(Touchable.enabled);
            restart.setTouchable(Touchable.enabled);
            leave.setTouchable(Touchable.enabled);
        }
    };

    private Runnable disableTouchables = new Runnable() {
        @Override
        public void run() {
            pause.setTouchable(Touchable.disabled);
            resume.setTouchable(Touchable.disabled);
            restart.setTouchable(Touchable.disabled);
            leave.setTouchable(Touchable.disabled);
        }
    };

    private Runnable resumeWidgets = new Runnable() {
        @Override
        public void run() {
            pause.resume();
            timer.resume();
            gauge.resume();
        }
    };

    private Runnable pauseWidgets = new Runnable() {
        @Override
        public void run() {
            pause.pause();
            timer.pause();
            gauge.pause();
        }
    };

    private Runnable fadeIn = new Runnable() {
        @Override
        public void run() {
            resume.addAction(fadeIn(0.2f));
            restart.addAction(fadeIn(0.2f));
            leave.addAction(fadeIn(0.2f));
            traveled.addAction(fadeIn(0.2f));
            destroyed.addAction(fadeIn(0.2f));
        }
    };

    private Runnable fadeOut = new Runnable() {
        @Override
        public void run() {
            resume.addAction(fadeOut(0.2f));
            restart.addAction(fadeOut(0.2f));
            leave.addAction(fadeOut(0.2f));
            traveled.addAction(fadeOut(0.2f));
            destroyed.addAction(fadeOut(0.2f));
        }
    };

    private Runnable enableVisibility = new Runnable() {
        @Override
        public void run() {
            resume.setVisible(true);
            restart.setVisible(true);
            leave.setVisible(true);
            traveled.setVisible(true);
            destroyed.setVisible(true);
        }
    };

    private Runnable disableVisibility = new Runnable() {
        @Override
        public void run() {
            resume.setVisible(false);
            restart.setVisible(false);
            leave.setVisible(false);
            traveled.setVisible(false);
            destroyed.setVisible(false);
        }
    };


    /**
     * Constructor
     *
     * @param am AssetManager
     * @param ls LevelScreen Futuro GameManager
     * @param vp Viewport
     */
    public HUDStage(AssetManager am, LevelScreen ls, Viewport vp) {
        super(vp);

        level = ls;
        background = new HUDBackground(am);
        timer = new Timer(am);
        pause = new PauseButton(am);
        gauge = new Gauge(am);
        resume = new MainButton(am, "RESUME", MainButton.Type.Play);
        restart = new MainButton(am, "RESTART", MainButton.Type.Default);
        leave = new MainButton(am, "LEAVE", MainButton.Type.Exit);
        traveled = new Stat(am, "Distance traveled", 542.4f, "m");
        destroyed = new Stat(am, "Got destroyed", 13, "times");

        background.setPositionGrid(0, 16);
        timer.setPositionGrid(3, 16.5f);
        pause.setPositionGrid(10, 16.5f);
        gauge.setPositionGrid(0.5f, 16.5f);
        resume.setPositionGrid(2, 11.5f);
        restart.setPositionGrid(2, 9);
        leave.setPositionGrid(2, 6.5f);
        traveled.setPositionGrid(1, 2);
        destroyed.setPositionGrid(1, 1);

        resume.setVisible(false);
        restart.setVisible(false);
        leave.setVisible(false);
        traveled.setVisible(false);
        destroyed.setVisible(false);

        resume.addAction(alpha(0));
        restart.addAction(alpha(0));
        leave.addAction(alpha(0));
        traveled.addAction(alpha(0));
        destroyed.addAction(alpha(0));

        createListeners();

        traveled.setLabelColor(BaseGroup.COLOR_WHITE);
        destroyed.setLabelColor(BaseGroup.COLOR_WHITE);

        addActor(background);
        addActor(timer);
        addActor(pause);
        addActor(gauge);
        addActor(resume);
        addActor(restart);
        addActor(leave);
        addActor(traveled);
        addActor(destroyed);
    }

    @Override
    public void draw() {
        timer.updateTime();
        gauge.decrease(COOLING_TICK);
        super.draw();
    }

    private void createListeners() {
        pause.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (!level.isPausedGame()) level.pauseGame();
                else level.resumeGame();

                event.cancel();
            }
        });

        resume.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (level.isPausedGame()) level.resumeGame();

                event.cancel();
            }
        });

        restart.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("restart");
                event.cancel();
            }
        });

        leave.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("leave");
                event.cancel();
            }
        });
    }

    /**
     * Metodo temporal para incrementar el Gauge
     * Se implementara en el GameManager
     */
    public void increment(float increment) {
        gauge.increment(increment);
    }

    public void pause () {
        background.clearActions();
        background.addAction(sequence(
                run(disableTouchables),
                run(pauseWidgets),
                moveTo(0, Grid.unit(16f), 0),
                moveTo(0, Grid.unit(-0.25f), 0.7f, Interpolation.pow2),
                run(enableVisibility),
                run(fadeIn),
                delay(0.2f),
                run(enableTouchables)
        ));
    }

    public void resume (final Runnable callback) {
        background.clearActions();
        background.addAction(sequence(
                run(disableTouchables),
                run(fadeOut),
                delay(0.2f),
                run(disableVisibility),
                moveTo(0, Grid.unit(-0.25f), 0),
                moveTo(0, Grid.unit(16f), 0.7f, Interpolation.pow2),
                run(resumeWidgets),
                run(enableTouchables),
                run(callback)
        ));
    }



}
