package com.jmolina.orb.elements;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;


/**
 * Elemento magnético
 */
public abstract class Magnetic extends Movable {

    public enum Polarity { ATTRACTIVE, REPULSIVE }

    private final float MIN_THRESHOLD = 1f;
    protected final float MAX_FORCE = 8f;

    private float threshold;
    private Polarity polarity;

    public Magnetic(AssetManager am, World world, float ppm, Geometry geometry, Flavor flavor, float w, float h, float x, float y, float angle, float threshold, Polarity polarity) {
        super(am, world, ppm,
                geometry, flavor,
                w, h, x, y, angle
        );

        setThreshold(MathUtils.clamp(threshold, MIN_THRESHOLD, threshold));
        setPolarity(polarity);
    }

    public void setThreshold(float threshold) {
        this.threshold = MathUtils.clamp(threshold, 0, threshold);
    }

    public float getThreshold() {
        return threshold;
    }

    public void setPolarity(Polarity polarity) {
        this.polarity = polarity;
    }

    public Polarity getPolarity() {
        return polarity;
    }

    /**
     * Devuelve la fuerza que ejerce este elemento sobre un punto dado.
     *
     * @param point Punto expresado en unidades del mundo
     */
    public abstract Vector2 force(Vector2 point);

}
