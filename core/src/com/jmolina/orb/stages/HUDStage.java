package com.jmolina.orb.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.jmolina.orb.assets.Asset;
import com.jmolina.orb.elements.BaseActor;
import com.jmolina.orb.managers.AssetManager;
import com.jmolina.orb.screens.BaseScreen;
import com.jmolina.orb.screens.LevelBaseScreen;
import com.jmolina.orb.utils.Grid;
import com.jmolina.orb.widgets.Gauge;
import com.jmolina.orb.widgets.HUDBackground;
import com.jmolina.orb.widgets.PauseButton;
import com.jmolina.orb.widgets.Timer;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeIn;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeOut;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.parallel;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.scaleTo;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

public class HUDStage extends Stage {

    private final float VIEWPORT_WIDTH = BaseScreen.VIEWPORT_WIDTH;
    private final float VIEWPORT_HEIGHT = BaseScreen.VIEWPORT_HEIGHT;

    private HUDBackground background;
    private Timer timer;
    private PauseButton pause;
    private Gauge gauge;

    private float time = 0f;

    public HUDStage(Viewport vp, AssetManager am) {
        super(vp);

        background = new HUDBackground(am);
        timer = new Timer(am);
        pause = new PauseButton(am);
        gauge = new Gauge(am);

        background.setPosition(0, Grid.unit(16));
        timer.setPosition(Grid.unit(3), Grid.unit(16.5f));
        pause.setPosition(Grid.unit(10), Grid.unit(16.5f));
        gauge.setPosition(Grid.unit(0.5f), Grid.unit(16.5f));

        addActor(background);
        addActor(timer);
        addActor(pause);
        addActor(gauge);
    }

    @Override
    public void draw() {
        super.draw();

        time += Gdx.graphics.getDeltaTime();
        timer.updateTime(time);
    }

    /**
     * Metodo temporal para incrementar el Gauge
     * Se implementara en el GameManager
     */
    public void increase (float increment) {
        gauge.increase(increment);
    }

    /**
     * Metodo temporal para decrementar el Gauge
     * Se implementara en el GameManager
     */
    public void decrease (float decrement) {
        gauge.decrease(decrement);
    }

}
