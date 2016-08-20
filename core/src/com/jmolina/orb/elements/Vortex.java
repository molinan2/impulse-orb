package com.jmolina.orb.elements;

import com.jmolina.orb.managers.AssetManager;
import com.badlogic.gdx.physics.box2d.World;


/**
 * Vórtice: Elemento magnético radial sin cuerpo.
 */
public class Vortex extends RadialMagnetic {

    public Vortex(AssetManager am, World world, float ppm, float x, float y, float threshold, Polarity polarity) {
        super(am, world, ppm, Flavor.AIR, 0, x, y, threshold, polarity);
    }

}
