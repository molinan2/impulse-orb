package com.jmolina.orb.situations.god;

import com.jmolina.orb.managers.AssetManager;
import com.badlogic.gdx.physics.box2d.World;
import com.jmolina.orb.elements.Element;
import com.jmolina.orb.elements.Magnetic;
import com.jmolina.orb.elements.Movable;
import com.jmolina.orb.elements.RadialMagnetic;
import com.jmolina.orb.elements.WorldElement;
import com.jmolina.orb.situations.SideWalls;
import com.jmolina.orb.var.Constant;


/**
 * El pasillo de la muerte xD
 */
public class Situation510 extends SideWalls {

    public Situation510(AssetManager am, World world, float pixelsPerMeter) {
        super(am, world, pixelsPerMeter);
    }

    @Override
    protected void createInnerElements () {
        float octave = 18f / 8;

        Element boxBottomL = new Element(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.RED,
                9, 9, 0, 1 * octave, 45
        );

        Element boxBottomR = new Element(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.RED,
                9, 9, 12, 3 * octave, 45
        );

        Element boxTopL = new Element(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.RED,
                9, 9, 0, 5 * octave, 45
        );

        Element boxTopR = new Element(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Geometry.SQUARE, WorldElement.Flavor.RED,
                9, 9, 12, 7 * octave, 45
        );


        addElement(boxBottomL);
        addElement(boxBottomR);
        addElement(boxTopL);
        addElement(boxTopR);

    }

}
