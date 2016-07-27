package com.jmolina.orb.elements;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.jmolina.orb.var.Asset;


/**
 * Elemento magnético (WIP)
 * De momento, sólo radial
 */
public class Magnetic extends Movable {

    public enum Polarity { ATTRACTIVE, REPULSIVE }

    private float threshold;
    private float strenght;
    private Polarity polarity;

    /**
     * {@inheritDoc}
     *
     * Crea un elemento magnético
     */
    public Magnetic(AssetManager am, World world, float ppm, float x, float y) {
        super(am, world, ppm,
                Geometry.CIRCLE, Flavor.VIOLET,
                x, y, 10, 10, 0
        );
    }

}
