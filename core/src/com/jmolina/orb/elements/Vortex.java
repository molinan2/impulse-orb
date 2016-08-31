package com.jmolina.orb.elements;

import com.jmolina.orb.managers.AssetManager;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Vórtice. Es un elemento magnético radial sin cuerpo.
 */
public class Vortex extends RadialMagnetic {

    /**
     * Constructor
     *
     * @param am AssetManager
     * @param world Mundo fisico
     * @param ppm Ratio de conversion pixeles/metros
     * @param x Coordenada X de la posicion en unidades del mundo
     * @param y Coordenada Y de la posicion en unidades del mundo
     */
    public Vortex(AssetManager am, World world, float ppm, float x, float y, float threshold, Polarity polarity) {
        super(am, world, ppm, Flavor.AIR, 0, x, y, threshold, polarity);
    }

}
