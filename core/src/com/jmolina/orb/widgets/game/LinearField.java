package com.jmolina.orb.widgets.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.RepeatAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.jmolina.orb.elements.Magnetic;
import com.jmolina.orb.elements.WorldElement;
import com.jmolina.orb.managers.AssetManager;
import com.jmolina.orb.utils.Utils;
import com.jmolina.orb.var.Asset;
import com.jmolina.orb.var.AssetAtlas;
import com.jmolina.orb.var.Var;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.alpha;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveTo;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.parallel;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.scaleTo;


/**
 * Representación visual de un elemento magnético lineal. Contiene la imagen del propio elemento,
 * el campo de acción y varias partículas que indican la polaridad mediante una animación.
 */
public class LinearField extends com.jmolina.orb.widgets.game.Field {

    private float width, height;
    private Image body, field, fill;

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

        width = w;
        height = h;

        fill = new Image();
        fill.setSize(Utils.cell(w), Utils.cell(threshold));
        fill.setPosition(0, 0);

        field = new Image(am.getAtlas().findRegion(AssetAtlas.GAME_MAGNETIC_FIELD_LINEAR));
        field.setSize(Utils.cell(w), Utils.cell(threshold));
        field.setPosition(0, Utils.cell(threshold));

        body = new Image(getBodyTexture(flavor));
        body.setSize(Utils.cell(w), Utils.cell(h));
        body.setPosition(0, Utils.cell(threshold) - 0.5f * Utils.cell(h));

        createParticles((int) w);
        addActors();

        setSize(Utils.cell(w), 2 * Utils.cell(threshold));
        setOrigin(0.5f * getWidth(), 0.5f * getHeight());
        setScale(ppm / Var.GRID_CELL_SIZE);
    }

    @Override
    protected void initParticle(Image particle, int index) {
        particle.clearActions();
        particle.setPosition(axisX(index), axisY());
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

    @Override
    protected Action repulsion(int index) {
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
     * Añade los actores
     */
    private void addActors() {
        addActor(field);
        addActor(fill);

        for(Image particle : getParticles())
            addActor(particle);

        addActor(body);
    }

    /**
     * Devuelve la textura del cuerpo, dependiendo del {@link WorldElement.Flavor}.
     *
     * @param flavor Flavor
     */
    private Texture getBodyTexture(WorldElement.Flavor flavor) {
        switch (flavor) {
            case RED: return getAssetManager().getAtlas().findRegion(AssetAtlas.GAME_SQUARE_RED).getTexture();
            case VIOLET: return getAssetManager().getAtlas().findRegion(AssetAtlas.GAME_SQUARE_VIOLET).getTexture();
            case TRANSPARENT: return getAssetManager().getAtlas().findRegion(AssetAtlas.GAME_SQUARE_TRANSPARENT).getTexture();
            default: return getAssetManager().getAtlas().findRegion(AssetAtlas.GAME_SQUARE_VIOLET).getTexture();
        }
    }

    /**
     * Calcula la coordenada X del punto interior de una partícula (en el eje)
     *
     * @param index Índice de la partícula
     */
    private float axisX(int index) {
        return index * (Utils.cell(width) - PARTICLE_DIAMETER) / (getParticleCount() -1);
    }

    /**
     * Calcula la coordenada Y del punto interior de una partícula (en el eje)
     */
    private float axisY() {
        return Utils.cell(getThreshold()) - 0.5f * PARTICLE_DIAMETER;
    }

    /**
     * Calcula la coordenada X del punto exterior de una partícula (en el borde)
     *
     * @param index Índice de la partícula
     */
    private float borderX(int index) {
        return axisX(index);
    }

    /**
     * Calcula la coordenada Y del punto exterior de una partícula (en el borde)
     */
    private float borderY() {
        return Utils.cell(2 * getThreshold()) - PARTICLE_DIAMETER;
    }

}
