package com.jmolina.orb.widgets;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.jmolina.orb.assets.Asset;
import com.jmolina.orb.utils.Grid;

public class Gauge extends BaseGroup {

    private final float LEVEL_MIN = 0f;
    private final float LEVEL_MAX = 1f;

    private Image background;
    private Image bar;
    private Image frame;
    private float level;
    private boolean paused;

    public Gauge(AssetManager am) {
        super(am);

        paused = false;

        background = new Image(getAsset(Asset.HUD_GAUGE_BACKGROUND, Texture.class));
        bar = new Image(getAsset(Asset.HUD_GAUGE_BAR, Texture.class));
        frame = new Image(getAsset(Asset.HUD_GAUGE_FRAME, Texture.class));

        background.setPosition(0f, 0f);
        bar.setPosition(0f, 0f);
        frame.setPosition(0f, 0f);

        addActor(background);
        addActor(bar);
        addActor(frame);

        setSize(Grid.unit(1), Grid.unit(1.5f));
        setOrigin(background.getWidth() * 0.5f, background.getHeight() * 0.5f);

        reset();
    }

    public void reset () {
        level = 0f;
        bar.setScaleY(level);
    }

    /**
     * @param increment Nivel del Gauge, representado entre 0 y 1
     */
    public void increment(float increment) {
        if (!paused) {
            level = MathUtils.clamp(level + increment, LEVEL_MIN, LEVEL_MAX);
            bar.setScaleY(level);
        }
    }

    /**
     * @param decrement Nivel del Gauge, representado entre 0 y 1
     */
    public void decrease (float decrement) {
        if (!paused) {
            level = MathUtils.clamp(level - decrement, LEVEL_MIN, LEVEL_MAX);
            bar.setScaleY(level);
        }
    }

    public void pause () {
        paused = true;
    }

    public void resume () {
        paused = false;
    }

}
