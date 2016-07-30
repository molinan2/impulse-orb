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

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.alpha;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveTo;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.parallel;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.scaleTo;


/**
 * Representación visual de un elemento magnético lineal. Contiene la imagen del propio elemento,
 * el campo de acción y varias partículas que indican la polaridad mediante una animación.
 */
public class LinearField extends BaseGroup {

    private final int PARTICLE_COUNT = 6;
    private final float PARTICLE_PERIOD = 1f;
    private final float PARTICLE_DIAMETER = Utils.cell(0.25f);
    private final float PARTICLE_MAX_ALPHA = 0.5f;
    private final float PARTICLE_SHORT_TIME = 0.1f;

    private Image body, field, fill;
    private ArrayList<Image> particles;
    private float width, height, threshold;
    private Magnetic.Polarity polarity;

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
        super(am);

        width = w;
        height = h;
        this.threshold = threshold;
        this.polarity = polarity;

        fill = new Image();
        fill.setSize(Utils.cell(w), Utils.cell(threshold));
        fill.setPosition(0, 0);

        field = new Image(getAsset(Asset.GAME_MAGNETIC_FIELD_LINEAR, Texture.class));
        field.setSize(Utils.cell(w), Utils.cell(threshold));
        field.setPosition(0, Utils.cell(threshold));

        body = new Image(getBodyTexture(flavor));
        body.setSize(Utils.cell(w), Utils.cell(h));
        body.setPosition(0, Utils.cell(threshold) - 0.5f * Utils.cell(h));

        createParticles();
        addActors();

        setSize(Utils.cell(w), 2 * Utils.cell(threshold));
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
     * Reinicia las animaciones de las partículas
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
        particle.setPosition(axisX(index), axisY());
        particle.addAction(alpha(0));
        particle.act(0);
        particle.clearActions();
        particle.addAction(particleAction(index));
    }

    private float axisX(int index) {
        return index * (Utils.cell(width) - PARTICLE_DIAMETER) / (PARTICLE_COUNT -1);
    }

    private float axisY() {
        return Utils.cell(threshold) - PARTICLE_DIAMETER;
    }

    private float borderX(int index) {
        return axisX(index);
    }

    private float borderY() {
        return Utils.cell(2 * threshold) - PARTICLE_DIAMETER;
    }

    /**
     * Añade los actores
     */
    private void addActors() {
        addActor(field);
        addActor(fill);

        for(Image particle : particles)
            addActor(particle);

        addActor(body);
    }

    /**
     * Crea la animación de una partícula con índice {@param index}.
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
     * Animación de atracción de una partícula
     *
     * @param index Índice de la partícula
     */
    private Action attraction(int index) {
        RepeatAction forever = new RepeatAction();
        forever.setCount(RepeatAction.FOREVER);
        forever.setAction(new SequenceAction(
                parallel(
                        alpha(0),
                        scaleTo(1, 1),
                        moveTo(borderX(index), borderY())
                ),
                parallel(
                        alpha(PARTICLE_MAX_ALPHA, PARTICLE_PERIOD, Interpolation.pow2In),
                        moveTo(axisX(index), axisY(), PARTICLE_PERIOD, Interpolation.pow2In)
                ),
                parallel(
                        scaleTo(0, 0, PARTICLE_SHORT_TIME),
                        alpha(0, PARTICLE_SHORT_TIME)
                )
        ));

        return forever;
    }

    /**
     * Animación de repulsión de una partícula
     *
     * @param index Índice de la partícula
     */
    private Action repulsion(int index) {
        RepeatAction forever = new RepeatAction();
        forever.setCount(RepeatAction.FOREVER);
        forever.setAction(new SequenceAction(
                parallel(
                        scaleTo(0, 0),
                        moveTo(axisX(index), axisY())
                ),
                parallel(
                        alpha(PARTICLE_MAX_ALPHA, PARTICLE_SHORT_TIME),
                        scaleTo(1, 1, PARTICLE_SHORT_TIME)
                ),
                parallel(
                        alpha(0, PARTICLE_PERIOD, Interpolation.pow2Out),
                        moveTo(borderX(index), borderY(), PARTICLE_PERIOD, Interpolation.pow2Out)
                )
        ));

        return forever;
    }

    /**
     * Devuelve la textura del cuerpo, dependiendo del {@link WorldElement.Flavor}.
     *
     * @param flavor Flavor
     */
    private Texture getBodyTexture(WorldElement.Flavor flavor) {
        switch (flavor) {
            case RED: return getAsset(Asset.GAME_SQUARE_RED, Texture.class);
            case VIOLET: return getAsset(Asset.GAME_SQUARE_VIOLET, Texture.class);
            case TRANSPARENT: return getAsset(Asset.GAME_SQUARE_TRANSPARENT, Texture.class);
            default: return getAsset(Asset.GAME_SQUARE_VIOLET, Texture.class);
        }
    }

}
