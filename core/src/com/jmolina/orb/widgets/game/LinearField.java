package com.jmolina.orb.widgets.game;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.jmolina.orb.managers.AssetManager;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.RepeatAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.jmolina.orb.elements.Magnetic;
import com.jmolina.orb.elements.WorldElement;
import com.jmolina.orb.var.Utils;
import com.jmolina.orb.var.Atlas;
import com.jmolina.orb.var.Var;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.alpha;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveTo;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.parallel;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.scaleTo;


/**
 * Representación visual de un elemento magnético lineal. Contiene la imagen del propio elemento,
 * el campo de acción y varias partículas que indican la polaridad mediante una animación.
 */
public class LinearField extends Field {

    private Image filler, body, field, particle;

    /**
     * Constructor
     *
     * @param am AssetManager
     * @param ppm PixelsPerMeter
     * @param flavor Flavor
     * @param w Ancho del elemento (en unidades del mundo)
     * @param h Alto del elemento (en unidades del mundo)
     * @param threshold Umbral
     * @param polarity Polaridad
     */
    public LinearField(AssetManager am, float ppm, WorldElement.Flavor flavor, float w, float h, float threshold, Magnetic.Polarity polarity) {
        super(am, threshold, polarity);

        filler = new Image();
        filler.setSize(Utils.cell(w), Utils.cell(threshold));
        filler.setPosition(0, 0);

        TextureRegion region = findRegion(Atlas.GAME_MAGNETIC_PARTICLE_LINEAR);
        particle = new Image(region);
        particle.setSize(Utils.cell(0.25f), Utils.cell(0.25f));
        particle.setPosition(0, Utils.cell(threshold));
        particle.setScaleX(ppm * w / region.getRegionWidth());
        particle.setOriginY(Utils.cell(0));

        resetParticleAction();
        particle.addAction(getParticleAction());

        field = new Image(findRegion(Atlas.GAME_MAGNETIC_FIELD_LINEAR));
        field.setSize(Utils.cell(w), Utils.cell(threshold));
        field.setPosition(0, Utils.cell(threshold));

        body = new Image(getBodyTextureRegion(flavor));
        body.setSize(Utils.cell(w), Utils.cell(h));
        body.setPosition(0, Utils.cell(threshold) - 0.5f * Utils.cell(h));

        addActor(field);
        addActor(filler);
        addActor(particle);
        addActor(body);

        setSize(Utils.cell(w), 2 * Utils.cell(threshold));
        setOrigin(0.5f * getWidth(), 0.5f * getHeight());
        setScale(ppm / Var.GRID_CELL_SIZE);
    }

    private void resetParticleAction() {
        particle.clearActions();
        particle.addAction(parallel(
                alpha(0.75f),
                scaleTo(particle.getScaleX(), 0)
        ));
        particle.act(0);
        particle.clearActions();
    }

    @Override
    protected Action getAttraction() {
        RepeatAction forever = new RepeatAction();
        forever.setCount(RepeatAction.FOREVER);
        forever.setAction(new SequenceAction(
                parallel(
                        moveTo(0, Utils.cell(2 * getThreshold()) - particle.getHeight(), 0),
                        alpha(0),
                        scaleTo(particle.getScaleX(), 1)
                ),
                parallel(
                        moveTo(0, Utils.cell(getThreshold()), PERIOD, Interpolation.pow2In),
                        alpha(MAX_ALPHA, PERIOD, Interpolation.pow2In)
                ),
                parallel(
                        scaleTo(particle.getScaleX(), 0, SHORT_TIME)
                )
        ));

        return forever;
    }

    @Override
    protected Action getRepulsion() {
        RepeatAction forever = new RepeatAction();
        forever.setCount(RepeatAction.FOREVER);
        forever.setAction(new SequenceAction(
                parallel(
                        moveTo(0, Utils.cell(getThreshold()), 0),
                        alpha(MAX_ALPHA),
                        scaleTo(particle.getScaleX(), 0)
                ),
                parallel(
                        scaleTo(particle.getScaleX(), 1, SHORT_TIME)
                ),
                parallel(
                        moveTo(0, Utils.cell(2 * getThreshold()) - particle.getHeight(), PERIOD, Interpolation.pow2Out),
                        alpha(0, PERIOD, Interpolation.pow2Out)
                )
        ));

        return forever;
    }

    /**
     * Devuelve la textura del cuerpo, dependiendo del {@link WorldElement.Flavor}.
     *
     * @param flavor Flavor
     */
    private TextureRegion getBodyTextureRegion(WorldElement.Flavor flavor) {
        switch (flavor) {
            case RED: return findRegion(Atlas.GAME_SQUARE_RED);
            case VIOLET: return findRegion(Atlas.GAME_SQUARE_VIOLET);
            case TRANSPARENT: return findRegion(Atlas.GAME_SQUARE_TRANSPARENT);
            default: return findRegion(Atlas.GAME_SQUARE_VIOLET);
        }
    }

}
