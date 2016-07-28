package com.jmolina.orb.elements;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.jmolina.orb.actors.BaseActor;
import com.jmolina.orb.var.Asset;


/**
 * Elemento magnético
 *
 * TODO: Extender de esta clase el RadialMagnetic y el LinearMagnetic
 */
public class Magnetic extends Movable {

    private final float DEFAULT_STRENGTH = 10f;
    private final float DEFAULT_THRESHOLD = 4f;
    private final Polarity DEFAULT_POLARITY = Polarity.ATTRACTIVE;

    public enum Polarity { ATTRACTIVE, REPULSIVE }

    private float threshold;
    private float strength;
    private Polarity polarity;

    public Magnetic(AssetManager am, World world, float ppm, Geometry geometry, float x, float y, float w, float h, float angle) {
        super(am, world, ppm,
                geometry, Flavor.VIOLET,
                x, y, w, h, angle
        );

        setThreshold(DEFAULT_THRESHOLD);
        setStrength(DEFAULT_STRENGTH);
        setPolarity(DEFAULT_POLARITY);
    }

    public void setThreshold(float threshold) {
        this.threshold = MathUtils.clamp(threshold, 0, threshold);
    }

    public float getThreshold() {
        return threshold;
    }

    public void setStrength(float strength) {
        this.strength = MathUtils.clamp(strength, 0, strength);
    }

    public float getStrength() {
        return strength;
    }

    public void setPolarity(Polarity polarity) {
        this.polarity = polarity;
    }

    public Polarity getPolarity() {
        return polarity;
    }

}
