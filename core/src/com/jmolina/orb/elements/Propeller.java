package com.jmolina.orb.elements;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.physics.box2d.World;


/**
 * Impulsor: Elemento magnético lineal sin cuerpo.
 */
public class Propeller extends LinearMagnetic {

    public Propeller(AssetManager am, World world, float ppm, float w, float x, float y, float angle, float threshold, Polarity polarity) {
        super(am, world, ppm, Flavor.TRANSPARENT, w, 0, x, y, angle, threshold, polarity);
    }

}
