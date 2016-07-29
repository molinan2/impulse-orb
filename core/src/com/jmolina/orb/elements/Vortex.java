package com.jmolina.orb.elements;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.physics.box2d.World;


/**
 * TODO
 * Vórtice: Elemento magnético radial sin cuerpo.
 */
public class Vortex extends RadialMagnetic {

    public Vortex(AssetManager am, World world, float ppm, float x, float y, float threshold) {
        this(am, world, ppm, x, y, threshold, Polarity.ATTRACTIVE);
    }

    public Vortex(AssetManager am, World world, float ppm, float x, float y, float threshold, Polarity polarity) {
        super(am, world, ppm, Flavor.TRANSPARENT, x, y, 0, threshold, polarity);
    }

}
