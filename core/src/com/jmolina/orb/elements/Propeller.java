package com.jmolina.orb.elements;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.physics.box2d.World;


/**
 * TODO
 * Impulsor: Elemento magnético lineal sin cuerpo.
 */
public class Propeller extends LinearMagnetic {

    public Propeller(AssetManager am, World world, float ppm, Geometry geometry, Flavor flavor, float x, float y, float w, float h, float angle, float threshold, Polarity polarity) {
        super(am, world, ppm, geometry, flavor, x, y, w, h, angle, threshold, polarity);
    }

}
