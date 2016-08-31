package com.jmolina.orb.widgets.game;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.jmolina.orb.managers.AssetManager;
import com.jmolina.orb.widgets.BaseActor;
import com.jmolina.orb.var.Atlas;
import com.jmolina.orb.widgets.BaseGroup;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.alpha;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeIn;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeOut;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.parallel;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.scaleTo;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

/**
 * Pulso que se dibuja con el gesto de paralizacion
 */
public class Pulse extends BaseGroup {

    private final float DIAMETER = 1.0f;
    private final float SCALE_MAX = 3.0f;
    private final float FADE_TIME = 0.35f;
    private final float SCALE_TIME = 0.25f;

    /** Imagen del pulso */
    private BaseActor image;

    /** Escala inicial */
    private float initialScale;

    /**
     * Constructor
     *
     * @param am AssetManager
     * @param pixelsPerMeter Ratio de pixels/metros
     */
    public Pulse(AssetManager am, float pixelsPerMeter) {
        super(am);

        TextureRegion region = findRegion(Atlas.GAME_GESTURE_BASE);
        initialScale = (pixelsPerMeter * DIAMETER) / region.getRegionWidth();
        image = new BaseActor(region);
        image.setPosition(0, 0);
        image.setScale(initialScale);

        setTransform(false);
        setSize(image.getWidth(), image.getHeight());
        setOrigin(0.5f * image.getWidth(), 0.5f * image.getHeight());
        addActor(image);
    }

    /**
     * Inicia el pulso
     */
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

    /**
     * Restablece el puso
     */
    public void reset() {
        image.clearActions();
        image.addAction(alpha(0));
        act(0);
    }

}
