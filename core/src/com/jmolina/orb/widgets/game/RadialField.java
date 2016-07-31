package com.jmolina.orb.widgets.game;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.RepeatAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.jmolina.orb.elements.Magnetic;
import com.jmolina.orb.elements.WorldElement;
import com.jmolina.orb.utils.Utils;
import com.jmolina.orb.var.Asset;
import com.jmolina.orb.var.Var;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;


/**
 * Representación visual de un elemento magnético radial. Contiene la imagen del propio elemento,
 * el campo de acción y varias partículas que indican la polaridad mediante una animación.
 */
public class RadialField extends com.jmolina.orb.widgets.game.Field {

    private float diameter;
    private Image body, field;

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

        diameter = d;

        field = new Image(getAsset(Asset.GAME_MAGNETIC_FIELD_RADIAL, Texture.class));
        field.setSize(Utils.cell(2 * threshold), Utils.cell(2 * threshold));
        field.setPosition(0, 0);

        body = new Image(getBodyTexture(flavor));
        body.setSize(Utils.cell(d), Utils.cell(d));
        body.setPosition(Utils.cell(threshold) - 0.5f * Utils.cell(d), Utils.cell(threshold) - 0.5f * Utils.cell(d));

        createParticles(12);
        addActors();

        setSize(2 * Utils.cell(threshold), 2 * Utils.cell(threshold));
        setOrigin(0.5f * getWidth(), 0.5f * getHeight());
        setScale(ppm / Var.GRID_CELL_SIZE);
    }

    @Override
    protected void initParticle(Image particle, int index) {
        particle.clearActions();
        particle.setPosition(centerX(), centerY());
        particle.addAction(alpha(0));
        particle.act(0);
        particle.clearActions();
        particle.addAction(particleAction(index));
    }

    @Override
    protected Action attraction(int index) {
        RepeatAction forever = new RepeatAction();
        forever.setCount(RepeatAction.FOREVER);
        forever.setAction(new SequenceAction(
                parallel(
                        scaleTo(1, 1),
                        alpha(0),
                        moveTo(borderX(index), borderY(index))
                ),
                parallel(
                        alpha(PARTICLE_MAX_ALPHA, PARTICLE_PERIOD, Interpolation.pow2In),
                        moveTo(centerX(), centerY(), PARTICLE_PERIOD, Interpolation.pow2In)
                ),
                scaleTo(0, 0, PARTICLE_SHORT_TIME)
        ));

        return forever;
    }

    @Override
    protected Action repulsion(int index) {
        RepeatAction forever = new RepeatAction();
        forever.setCount(RepeatAction.FOREVER);
        forever.setAction(new SequenceAction(
                scaleTo(0, 0),
                scaleTo(1, 1, PARTICLE_SHORT_TIME),
                parallel(
                        alpha(PARTICLE_MAX_ALPHA),
                        moveTo(centerX(), centerY())
                ),
                parallel(
                        alpha(0, PARTICLE_PERIOD, Interpolation.pow2Out),
                        moveTo(borderX(index), borderY(index), PARTICLE_PERIOD, Interpolation.pow2Out)
                )
        ));

        return forever;
    }

    /**
     * Añade los actores, en orden.
     */
    private void addActors() {
        addActor(field);

        for(Image particle : getParticles())
            addActor(particle);

        addActor(body);
    }

    /**
     * Devuelve la textura del cuerpo, dependiendo del {@link com.jmolina.orb.elements.WorldElement.Flavor}.
     *
     * @param flavor Flavor
     */
    private Texture getBodyTexture(WorldElement.Flavor flavor) {
        switch (flavor) {
            case RED: return getAsset(Asset.GAME_CIRCLE_RED, Texture.class);
            case VIOLET: return getAsset(Asset.GAME_CIRCLE_VIOLET, Texture.class);
            case TRANSPARENT: return getAsset(Asset.GAME_CIRCLE_TRANSPARENT, Texture.class);
            default: return getAsset(Asset.GAME_CIRCLE_VIOLET, Texture.class);
        }
    }

    /**
     * Devuelve la coordenada X de la posición de una partícula situada en el centro
     */
    private float centerX() {
        return Utils.cell(getThreshold()) - 0.5f * PARTICLE_DIAMETER;
    }

    /**
     * Devuelve la coordenada Y de la posición de una partícula situada en el centro
     */
    private float centerY() {
        return Utils.cell(getThreshold()) - 0.5f * PARTICLE_DIAMETER;
    }

    /**
     * Devuelve la coordenada X de la posición de una partícula situada en el borde del umbral.
     *
     * @param index Índice de la partícula
     */
    private float borderX(int index) {
        return Utils.cell(getThreshold()) * (1 + MathUtils.cosDeg(angle(index))) - 0.5f * PARTICLE_DIAMETER;
    }

    /**
     * Devuelve la coordenada Y de la posición de una partícula situada en el borde del umbral.
     *
     * @param index Índice de la partícula
     */
    private float borderY(int index) {
        return Utils.cell(getThreshold()) * (1 + MathUtils.sinDeg(angle(index))) - 0.5f * PARTICLE_DIAMETER;
    }

    /**
     * Calcula el ángulo en base al índice de sector circular
     *
     * @param i Índice de sector circular
     */
    private float angle(int i) {
        return 360.0f * (float) i / (float) getParticleCount();
    }

}
