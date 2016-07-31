package com.jmolina.orb.widgets.game;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.jmolina.orb.utils.Utils;
import com.jmolina.orb.var.Asset;
import com.jmolina.orb.widgets.BaseGroup;

public class Gauge extends BaseGroup {

    private final float LEVEL_MIN = 0f;
    private final float LEVEL_MAX = 1f;

    private Image background;
    private Image bar;
    private Image overload;
    private Image frame;
    private float level;
    private boolean overloaded = false;

    public Gauge(AssetManager am) {
        super(am);


        background = new Image(getAsset(Asset.HUD_GAUGE_BACKGROUND, Texture.class));
        bar = new Image(getAsset(Asset.HUD_GAUGE_BAR, Texture.class));
        overload = new Image(getAsset(Asset.HUD_GAUGE_OVERLOAD, Texture.class));
        frame = new Image(getAsset(Asset.HUD_GAUGE_FRAME, Texture.class));

        background.setPosition(0f, 0f);
        bar.setPosition(0f, 0f);
        overload.setPosition(0f, 0f);
        frame.setPosition(0f, 0f);

        overload.addAction(Actions.alpha(0));

        addActor(background);
        addActor(bar);
        addActor(overload);
        addActor(frame);

        setTransform(false);
        setSize(Utils.cell(1), Utils.cell(1.5f));
        setOrigin(background.getWidth() * 0.5f, background.getHeight() * 0.5f);

        reset();
    }

    public void reset () {
        level = 0f;
        bar.setScaleY(level);
        overload.addAction(Actions.alpha(0));
    }

    /**
     * @param increment Nivel del Gauge, representado entre 0 y 1
     */
    public void increase(float increment) {
        level = MathUtils.clamp(level + increment, LEVEL_MIN, LEVEL_MAX);
        bar.setScaleY(level);
    }

    public void decrease (float decrement) {
        increase(-decrement);
    }

    public void setLevel (float level) {
        this.level = MathUtils.clamp(level, LEVEL_MIN, LEVEL_MAX);
        bar.setScaleY(level);
    }

    public void setOverloaded(boolean overloaded) {
        this.overloaded = overloaded;
        if (this.overloaded)
            overload.addAction(Actions.fadeIn(0.35f));
        else
            overload.addAction(Actions.fadeOut(0.35f));
    }

}
