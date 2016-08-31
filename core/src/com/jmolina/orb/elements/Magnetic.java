package com.jmolina.orb.elements;

import com.jmolina.orb.managers.AssetManager;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;


/**
 * Elemento magnético base
 */
public abstract class Magnetic extends Movable {

    public enum Polarity { ATTRACTIVE, REPULSIVE }

    private final float MIN_THRESHOLD = 1f;
    protected float MAX_FORCE = 1.4f;

    /** Umbral de accion del campo magnetico */
    private float threshold;

    /** Polaridad del campo magnetico */
    private Polarity polarity;

    /**
     * Constructor
     *
     * @param am AssetManager
     * @param world Mundo fisico
     * @param ppm Ratio de conversion pixeles/metros
     * @param geometry Geometria
     * @param flavor Sabor
     * @param w Anchura en unidades del mundo
     * @param h Altura en unidades del mundo
     * @param x Coordenada X de la posicion en unidades del mundo
     * @param y Coordenada Y de la posicion en unidades del mundo
     * @param angle Angulo
     * @param threshold Umbral en unidades del mundo
     * @param polarity Polaridad
     */
    public Magnetic(AssetManager am, World world, float ppm, Geometry geometry, Flavor flavor, float w, float h, float x, float y, float angle, float threshold, Polarity polarity) {
        super(
                am, world, ppm,
                geometry, flavor,
                w, h, x, y, angle
        );

        setThreshold(MathUtils.clamp(threshold, MIN_THRESHOLD, threshold));
        setPolarity(polarity);
    }

    /**
     * Fija el valor del umbral
     *
     * @param threshold Umbral en unidades del mundo
     */
    public void setThreshold(float threshold) {
        this.threshold = MathUtils.clamp(threshold, 0, threshold);
    }

    /**
     * Devuelve el valor del umbral
     */
    public float getThreshold() {
        return threshold;
    }

    /**
     * Fija la polaridad
     *
     * @param polarity Polaridad
     */
    public void setPolarity(Polarity polarity) {
        this.polarity = polarity;
    }

    /**
     * Devuelve la polaridad
     */
    public Polarity getPolarity() {
        return polarity;
    }

    /**
     * Devuelve la fuerza que ejerce este elemento sobre un punto dado.
     *
     * @param point Punto expresado en unidades del mundo
     */
    public abstract Vector2 getForce(Vector2 point);

}
