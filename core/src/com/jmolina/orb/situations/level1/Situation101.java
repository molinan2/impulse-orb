package com.jmolina.orb.situations.level1;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.physics.box2d.World;
import com.jmolina.orb.elements.Element;
import com.jmolina.orb.situations.SideWalledSituation;


public class Situation101 extends SideWalledSituation {

    public Situation101(AssetManager am, World world, float ratioMeterPixel) {
        super(am, world, ratioMeterPixel);
    }

    protected void createInnerElements () {
        // Obstacle ball
        addElement(new Element(
                getAssetManager(), getWorld(),
                6, 14, 4, 4, 0,
                Element.Flavor.GREY, Element.Geometry.CIRCLE,
                getRatioMeterPixel()
        ));

        // Lateral red boxes
        addElement(new Element(
                getAssetManager(), getWorld(),
                0.75f, 10, 0.5f, 4, 0,
                Element.Flavor.RED, Element.Geometry.SQUARE,
                getRatioMeterPixel()
        ));

        addElement(new Element(
                getAssetManager(), getWorld(),
                11.25f, 10, 0.5f, 4, 0,
                Element.Flavor.RED, Element.Geometry.SQUARE,
                getRatioMeterPixel()
        ));
    }

}
