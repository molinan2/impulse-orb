package com.jmolina.orb.widgets.game;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.jmolina.orb.managers.AssetManager;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.RepeatAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.jmolina.orb.elements.Magnetic;
import com.jmolina.orb.elements.WorldElement;
import com.jmolina.orb.var.Utils;
import com.jmolina.orb.var.Atlas;
import com.jmolina.orb.var.Var;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;


/**
 * Representación visual de un elemento magnético radial. Contiene la imagen del propio elemento,
 * el campo de acción y varias partículas que indican la polaridad mediante una animación.
 */
public class RadialField extends Field {

    private Image body, field, particle;

    /**
     * Constructor
     *
     * @param am AssetManager
     * @param ppm PixelsPerMeter
     * @param flavor Flavor
     * @param d Diámetro del cuerpo
     * @param threshold Umbral (radio de acción)
     * @param polarity Polaridad
     */
    public RadialField(AssetManager am, float ppm, WorldElement.Flavor flavor, float d, float threshold, Magnetic.Polarity polarity) {
        super(am, threshold, polarity);

        TextureRegion region = findRegion(Atlas.GAME_MAGNETIC_PARTICLE_RADIAL);
        particle = new Image(region);
        particle.setSize(Utils.cell(2 * threshold), Utils.cell(2 * threshold));
        particle.setPosition(0, 0);
        particle.setOrigin(Utils.cell(threshold), Utils.cell(threshold));

        resetParticleAction();
        particle.addAction(getParticleAction());

        field = new Image(findRegion(Atlas.GAME_MAGNETIC_FIELD_RADIAL));
        field.setSize(Utils.cell(2 * threshold), Utils.cell(2 * threshold));
        field.setPosition(0, 0);

        body = new Image(getBodyTextureRegion(flavor));
        body.setSize(Utils.cell(d), Utils.cell(d));
        body.setPosition(Utils.cell(threshold) - 0.5f * Utils.cell(d), Utils.cell(threshold) - 0.5f * Utils.cell(d));

        addActor(field);
        addActor(particle);
        addActor(body);

        setSize(2 * Utils.cell(threshold), 2 * Utils.cell(threshold));
        setOrigin(0.5f * getWidth(), 0.5f * getHeight());
        setScale(ppm / Var.GRID_CELL_SIZE);
    }

    /**
     * Aplica una accion para resetear la particula
     */
    private void resetParticleAction() {
        particle.clearActions();
        particle.addAction(parallel(
                alpha(0),
                scaleTo(1, 1)
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
                        scaleTo(1, 1),
                        alpha(0)
                ),
                parallel(
                        scaleTo(0, 0, PERIOD, Interpolation.pow2In),
                        alpha(MAX_ALPHA, PERIOD, Interpolation.pow2In)
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
                        scaleTo(0, 0),
                        alpha(MAX_ALPHA)
                ),
                parallel(
                        scaleTo(1, 1, PERIOD, Interpolation.pow2Out),
                        alpha(0, PERIOD, Interpolation.pow2Out)
                )
        ));

        return forever;
    }

    /**
     * Devuelve la textura del cuerpo, dependiendo del {@link com.jmolina.orb.elements.WorldElement.Flavor}.
     *
     * @param flavor Flavor
     */
    private TextureRegion getBodyTextureRegion(WorldElement.Flavor flavor) {
        switch (flavor) {
            case RED: return findRegion(Atlas.GAME_CIRCLE_RED);
            case VIOLET: return findRegion(Atlas.GAME_CIRCLE_VIOLET);
            case AIR: return findRegion(Atlas.GAME_CIRCLE_TRANSPARENT);
            default: return findRegion(Atlas.GAME_CIRCLE_VIOLET);
        }
    }

}
