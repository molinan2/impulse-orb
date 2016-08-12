package com.jmolina.orb.widgets.game;

import com.jmolina.orb.managers.AssetManager;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.jmolina.orb.elements.Magnetic;
import com.jmolina.orb.utils.Utils;
import com.jmolina.orb.var.Atlas;
import com.jmolina.orb.widgets.BaseGroup;

import java.util.ArrayList;

public abstract class Field extends BaseGroup {

    protected final float PARTICLE_PERIOD = 1f;
    protected final float PARTICLE_DIAMETER = Utils.cell(0.25f);
    protected final float PARTICLE_MAX_ALPHA = 0.5f;
    protected final float PARTICLE_SHORT_TIME = 0.1f;

    private int particleCount;
    private float threshold;
    private ArrayList<Image> particles;
    private Magnetic.Polarity polarity;

    /**
     * Constructor
     *
     * @param am AssetManager
     * @param threshold Umbral
     * @param polarity Polaridad
     */
    public Field(AssetManager am, float threshold, Magnetic.Polarity polarity) {
        super(am);
        setThreshold(threshold);
        setPolarity(polarity);
    }

    /**
     * Crea la partículas
     */
    protected void createParticles(int count) {
        setParticleCount(count);
        particles = new ArrayList<Image>();

        for (int i = 0; i< particleCount; i++) {
            Image particle = new Image(findRegion(Atlas.GAME_MAGNETIC_PARTICLE));
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
        for (int i=0; i<getParticles().size(); i++) {
            Image particle = getParticles().get(i);
            initParticle(particle, i);
        }
    }

    /**
     * Inicializa la posición de una partícula
     *
     * @param particle Partícula
     * @param index Índice de la partícula
     */
    protected abstract void initParticle(Image particle, int index);

    /**
     * Crea la acción de atracción o repulsión de una partícula.
     *
     * @param index Índice de la partícula
     */
    protected Action particleAction(int index) {
        switch (getPolarity()) {
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
    protected abstract Action attraction(int index);

    /**
     * Crea una acción de repulsión repetida infinitamente, para ser aplicada a una partícula.
     *
     * @param index Índice de la p artícula
     */
    protected abstract Action repulsion(int index);

    /**
     * Devuelve la lista de partículas
     */
    protected ArrayList<Image> getParticles() {
        return particles;
    }

    /**
     * Define la cantidad de partículas
     * @param count Cantidad de partículas
     */
    private void setParticleCount(int count) {
        particleCount = count;
    }

    /**
     * Devuelve la cantidad de partículas
     */
    protected int getParticleCount() {
        return particleCount;
    }

    /**
     * Define el umbral
     *
     * @param threshold Umbral
     */
    private void setThreshold(float threshold) {
        this.threshold = threshold;
    }

    /**
     * Devuelve el umbral
     */
    protected float getThreshold() {
        return threshold;
    }

    /**
     * Define la polaridad
     *
     * @param polarity Polaridad
     */
    private void setPolarity(Magnetic.Polarity polarity) {
        this.polarity = polarity;
    }

    /**
     * Devuelve la polaridad
     */
    protected Magnetic.Polarity getPolarity() {
        return polarity;
    }

    @Override
    public void draw (Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
    }

}
