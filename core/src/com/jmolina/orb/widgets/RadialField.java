package com.jmolina.orb.widgets;

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
import java.util.ArrayList;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;


/**
 * Representación visual de un elemento magnético radial. Contiene la imagen del propio elemento,
 * el campo de acción y varias partículas que indican la polaridad mediante una animación.
 */
public class RadialField extends BaseGroup {

    private final int PARTICLE_COUNT = 12;
    private final float PARTICLE_PERIOD = 1f;
    private final float PARTICLE_DIAMETER = Utils.cell(0.25f);
    private final float PARTICLE_MAX_ALPHA = 0.5f;
    private final float PARTICLE_SHORT_TIME = 0.1f;

    private Image body, field;
    private ArrayList<Image> particles;
    private float diameter, threshold;
    private Magnetic.Polarity polarity;

    /**
     * Constructor
     *
     * @param am AssetManager
     * @param ppm PixelsPerMeter
     * @param flavor Flavor
     * @param diameter Diámetro del cuerpo
     * @param threshold Umbral (radio de acción)
     * @param polarity Polaridad
     */
    public RadialField(AssetManager am, float ppm, WorldElement.Flavor flavor, float diameter, float threshold, Magnetic.Polarity polarity) {
        super(am);

        this.diameter = diameter;
        this.threshold = threshold;
        this.polarity = polarity;

        field = new Image(getAsset(Asset.GAME_MAGNETIC_FIELD_RADIAL, Texture.class));
        field.setSize(Utils.cell(2 * threshold), Utils.cell(2 * threshold));
        field.setPosition(0, 0);

        body = new Image(getBodyTexture(flavor));
        body.setSize(Utils.cell(diameter), Utils.cell(diameter));
        body.setPosition(Utils.cell(threshold) - 0.5f * Utils.cell(diameter), Utils.cell(threshold) - 0.5f * Utils.cell(diameter));

        createParticles();
        addActors();

        setSize(2 * Utils.cell(threshold), 2 * Utils.cell(threshold));
        setOrigin(0.5f * getWidth(), 0.5f * getHeight());
        setScale(ppm / Var.GRID_CELL_SIZE);
    }

    /**
     * Crea la partículas
     */
    private void createParticles() {
        particles = new ArrayList<Image>();

        for (int i=0; i<PARTICLE_COUNT; i++) {
            Image particle = new Image(getAsset(Asset.GAME_MAGNETIC_PARTICLE, Texture.class));
            particle.setSize(PARTICLE_DIAMETER, PARTICLE_DIAMETER);
            particle.setOrigin(0.5f * PARTICLE_DIAMETER, 0.5f * PARTICLE_DIAMETER);
            initParticle(particle, i);
            particles.add(particle);
        }
    }

    /**
     * Reinicia la animación de las partículas
     */
    public void reset() {
        for (int i=0; i<particles.size(); i++) {
            Image particle = particles.get(i);
            initParticle(particle, i);
        }
    }

    /**
     * Inicializa la posición de una partícula
     *
     * @param particle Partícula
     * @param index Índice de la partícula
     */
    private void initParticle(Image particle, int index) {
        particle.clearActions();
        particle.setPosition(centerX(), centerY());
        particle.addAction(alpha(0));
        particle.act(0);
        particle.clearActions();
        particle.addAction(particleAction(index));
    }

    /**
     * Añade los actores, en orden.
     */
    private void addActors() {
        addActor(field);

        for(Image particle : particles)
            addActor(particle);

        addActor(body);
    }

    /**
     * Devuelve la coordenada X de la posición de una partícula situada en el centro
     */
    private float centerX() {
        return Utils.cell(threshold) - 0.5f * PARTICLE_DIAMETER;
    }

    /**
     * Devuelve la coordenada Y de la posición de una partícula situada en el centro
     */
    private float centerY() {
        return Utils.cell(threshold) - 0.5f * PARTICLE_DIAMETER;
    }

    /**
     * Devuelve la coordenada X de la posición de una partícula situada en el borde del umbral.
     *
     * @param index Índice de la partícula
     */
    private float borderX(int index) {
        return Utils.cell(threshold) * (1 + MathUtils.cosDeg(angle(index))) - 0.5f * PARTICLE_DIAMETER;
    }

    /**
     * Devuelve la coordenada Y de la posición de una partícula situada en el borde del umbral.
     *
     * @param index Índice de la partícula
     */
    private float borderY(int index) {
        return Utils.cell(threshold) * (1 + MathUtils.sinDeg(angle(index))) - 0.5f * PARTICLE_DIAMETER;
    }

    /**
     * Crea la acción de atracción o repulsión de una partícula.
     *
     * @param index Índice de la partícula
     */
    private Action particleAction(int index) {
        switch (polarity) {
            case ATTRACTIVE: return attraction(index);
            case REPULSIVE: return repulsion(index);
            default: return attraction(index);
        }
    }

    /**
     * Crea una acción de atracción repetida infinitamente, para ser aplicada a una partícula.
     *
     * @param index Índice de la p artícula
     */
    private Action attraction(int index) {
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

    /**
     * Crea una acción de repulsión repetida infinitamente, para ser aplicada a una partícula.
     *
     * @param index Índice de la p artícula
     */
    private Action repulsion(int index) {
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
     * Calcula el ángulo en base al índice de sector circular
     *
     * @param i Índice de sector circular
     */
    private float angle(int i) {
        return 360.0f * (float) i / (float) PARTICLE_COUNT;
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

}
