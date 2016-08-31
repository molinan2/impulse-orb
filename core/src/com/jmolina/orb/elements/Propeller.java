package com.jmolina.orb.elements;

import com.jmolina.orb.managers.AssetManager;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Impulsor. Es un elemento magnético lineal sin cuerpo.
 */
public class Propeller extends LinearMagnetic {

    /**
     * Constructor
     *
     * @param am AssetManager
     * @param world Mundo fisico
     * @param ppm Ratio de conversion pixeles/metros
     * @param w Anchura
     * @param x Coordenada X de la posicion en unidades del mundo
     * @param y Coordenada Y de la posicion en unidades del mundo
     */
    public Propeller(AssetManager am, World world, float ppm, float w, float x, float y, float angle, float threshold, Polarity polarity) {
        super(am, world, ppm, Flavor.AIR, w, 0, x, y, angle, threshold, polarity);
    }

}
