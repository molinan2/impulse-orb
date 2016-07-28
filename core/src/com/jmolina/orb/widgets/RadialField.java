package com.jmolina.orb.widgets;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.RepeatAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
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

    private final int PARTICLE_QUANTITY = 12;
    private final float PARTICLE_PERIOD = 1f;
    private final float PARTICLE_WIDTH = Utils.cell(0.25f);
    private final float PARTICLE_HEIGHT = Utils.cell(0.25f);

    private Image body, field;
    private ArrayList<Image> particles;
    private float diameter, threshold, strength;

    /**
     * Constructor
     *
     * @param am AssetManager
     * @param ppm PixelsPerMeter
     * @param diameter Diámetro del cuerpo
     * @param threshold Umbral (radio de acción)
     * @param strength Fuerza de acción
     */
    public RadialField(AssetManager am, float ppm, float diameter, float threshold, float strength) {
        super(am);

        this.diameter = diameter;
        this.threshold = threshold;
        this.strength = strength;

        field = new Image(getAsset(Asset.GAME_MAGNETIC_FIELD, Texture.class));
        field.setSize(Utils.cell(2 * threshold), Utils.cell(2 * threshold));
        field.setPosition(0, 0);
        body = new Image(getAsset(Asset.GAME_CIRCLE_VIOLET, Texture.class));
        body.setSize(Utils.cell(diameter), Utils.cell(diameter));
        body.setPosition(centerX(body), centerY(body));

        createParticles();
        addActors();

        setSize(2 * Utils.cell(threshold), 2 * Utils.cell(threshold));
        setOrigin(0.5f * getWidth(), 0.5f * getHeight());
        setScale(ppm / Var.GRID_CELL_SIZE);
    }

    /**
     * Reinicia la animación de las partículas
     */
    public void reset() {
        for (int i=0; i<particles.size(); i++) {
            Image particle = particles.get(i);
            particle.clearActions();
            particle.setPosition(centerX(), centerY());
            particle.addAction(attraction(angle(i)));
        }
    }

    /**
     * Crea la partículas
     */
    private void createParticles() {
        particles = new ArrayList<Image>();

        for (int i=0; i<PARTICLE_QUANTITY; i++) {
            Image particle = new Image(getAsset(Asset.GAME_MAGNETIC_PARTICLE, Texture.class));
            particle.setSize(PARTICLE_WIDTH, PARTICLE_HEIGHT);
            particle.setPosition(centerX(), centerY());
            particle.addAction(attraction(angle(i)));
            particles.add(particle);
        }
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
        return Utils.cell(threshold) - 0.5f * PARTICLE_WIDTH;
    }

    /**
     * Devuelve la coordenada Y de la posición de una partícula situada en el centro
     */
    private float centerY() {
        return Utils.cell(threshold) - 0.5f * PARTICLE_HEIGHT;
    }

    private float centerX(Actor actor) {
        return Utils.cell(threshold) - 0.5f * actor.getWidth();
    }

    private float centerY(Actor actor) {
        return Utils.cell(threshold) - 0.5f * actor.getHeight();
    }

    /**
     * Devuelve la coordenada X de la posición de una partícula situada en el borde del umbral, a
     * un ángulo dado.
     *
     * @param angle Ángulo en grados counterclockwise
     */
    private float borderX(float angle) {
        return Utils.cell(threshold) * (1 + MathUtils.cosDeg(angle)) - 0.5f * PARTICLE_WIDTH;
    }

    /**
     * Devuelve la coordenada Y de la posición de una partícula situada en el borde del umbral, a
     * un ángulo dado.
     *
     * @param angle Ángulo en grados counterclockwise
     */
    private float borderY(float angle) {
        return Utils.cell(threshold) * (1 + MathUtils.sinDeg(angle)) - 0.5f * PARTICLE_HEIGHT;
    }

    /**
     * Crea una acción de atracción repetida infinitamente, para ser aplicada a una partícula con
     * ángulo {@param angle}.
     *
     * @param angle Ángulo en grados counterclockwise
     */
    private Action attraction(float angle) {
        RepeatAction forever = new RepeatAction();
        forever.setCount(RepeatAction.FOREVER);
        forever.setAction(new SequenceAction(
                parallel(
                        alpha(0),
                        moveTo(borderX(angle), borderY(angle))
                ),
                parallel(
                        alpha(0.5f, PARTICLE_PERIOD, Interpolation.pow2In),
                        moveTo(centerX(), centerY(), PARTICLE_PERIOD, Interpolation.pow2In)
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
        return 360.0f * (float) i / (float) PARTICLE_QUANTITY;
    }

}
