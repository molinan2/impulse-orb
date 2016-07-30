package com.jmolina.orb.elements;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.physics.box2d.World;


/**
 * TODO
 * Impulsor: Elemento magnético lineal sin cuerpo.
 */
public class Propeller extends LinearMagnetic {

    public Propeller(AssetManager am, World world, float ppm, float x, float y, float w, float angle, float threshold, Polarity polarity) {
        super(am, world, ppm, Flavor.TRANSPARENT, x, y, w, 0, angle, threshold, polarity);
    }

}
