package com.jmolina.orb.widgets;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.jmolina.orb.var.Asset;
import com.jmolina.orb.actors.BaseActor;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.alpha;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeIn;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeOut;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.parallel;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.scaleTo;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;


/**
 * Seria mas apropiado que heredase de BaseActor
 */
public class Tap extends BaseGroup {

    private final float DIAMETER = 1.0f;
    private final float SCALE_MAX = 3.0f;
    private final float FADE_TIME = 0.35f;
    private final float SCALE_TIME = 0.25f;

    private BaseActor image;
    private float initialScale;

    public Tap(AssetManager am, float pixelsPerMeter) {
        super(am);

        Texture texture = getAsset(Asset.GAME_GESTURE_BASE, Texture.class);
        initialScale = (pixelsPerMeter * DIAMETER) / texture.getWidth();
        image = new BaseActor(texture);
        image.setPosition(0, 0);
        image.setScale(initialScale);

        setTransform(false);
        setSize(image.getWidth(), image.getHeight());
        setOrigin(0.5f * image.getWidth(), 0.5f * image.getHeight());
        addActor(image);
    }

    public void start() {
        reset();
        image.addAction(sequence(
                parallel(
                        alpha(1),
                        scaleTo(initialScale, initialScale)
                ),
                parallel(
                        fadeOut(FADE_TIME),
                        scaleTo(SCALE_MAX * initialScale, SCALE_MAX * initialScale, SCALE_TIME)
                )
        ));
    }

    public void reset() {
        image.clearActions();
        image.addAction(alpha(0));
    }

}
