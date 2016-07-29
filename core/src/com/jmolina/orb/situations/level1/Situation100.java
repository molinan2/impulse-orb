package com.jmolina.orb.situations.level1;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.physics.box2d.World;
import com.jmolina.orb.elements.Element;
import com.jmolina.orb.elements.Heater;
import com.jmolina.orb.elements.Init;
import com.jmolina.orb.elements.Magnetic;
import com.jmolina.orb.elements.Movable;
import com.jmolina.orb.elements.RadialMagnetic;
import com.jmolina.orb.elements.WorldElement;
import com.jmolina.orb.situations.SideWallSituation;


public class Situation100 extends SideWallSituation {

    public Situation100(AssetManager am, World world, float pixelsPerMeter) {
        super(am, world, pixelsPerMeter);
    }

    protected void createInnerElements () {
        // Test Rotable
        Movable movable = new Movable(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                Element.Geometry.SQUARE, Element.Flavor.GREY,
                6, 4, 2, 2, 0
        );

        movable.addRotation(0.5f);
        movable.addDisplacement(0.25f, 4);
        addElement(movable);



        // Test Hot
        Heater heater = new Heater(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                0, 0
        );

        addElement(heater);



        // Test platform
        Movable platform = new Movable(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                Element.Geometry.SQUARE, Element.Flavor.GREY,
                6, 7, 10, 1, 0
        );

        addElement(platform);



        // Test triangle
        Movable m2 = new Movable(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                Element.Geometry.TRIANGLE, Element.Flavor.RED,
                10, 2, 3, 3, 0
        );

        m2.addRotation(0.25f);
        addElement(m2);




        // Test magnetic
        RadialMagnetic magnetic = new RadialMagnetic(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                WorldElement.Flavor.TRANSPARENT,
                6, 10f, 1,
                5, Magnetic.Polarity.REPULSIVE
        );

        magnetic.addRotation(0.25f);
        addElement(magnetic);







        // Init
        addElement(new Init(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                6, 4
        ));

        // Bottom walls
        addElement(new Element(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                Element.Geometry.SQUARE, Element.Flavor.BLACK,
                6, -9 + 0.5f, 12, 18, 0
        ));

        addElement(new Element(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                Element.Geometry.SQUARE, Element.Flavor.BLACK,
                -6 + 0.5f, -9 + 0.5f, 12, 18, 0
        ));

        addElement(new Element(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                Element.Geometry.SQUARE, Element.Flavor.BLACK,
                18 - 0.5f, -9 + 0.5f, 12, 18, 0
        ));
    }

}
