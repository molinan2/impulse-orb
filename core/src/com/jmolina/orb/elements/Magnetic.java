package com.jmolina.orb.elements;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.jmolina.orb.var.Asset;


/**
 * Elemento magnético (WIP)
 */
public class Magnetic extends Movable {

    /**
     * {@inheritDoc}
     *
     * Crea un elemento magnético
     */
    public Magnetic(AssetManager am, World world, float ppm, float x, float y) {
        super(am, world, ppm,
                Geometry.SQUARE, Flavor.BLUE,
                x, y, 10, 10, 0
        );
    }

}
