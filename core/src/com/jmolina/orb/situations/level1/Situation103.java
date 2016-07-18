package com.jmolina.orb.situations.level1;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.physics.box2d.World;
import com.jmolina.orb.elements.Element;
import com.jmolina.orb.situations.SideWalledSituation;


public class Situation103 extends SideWalledSituation {

    public Situation103(AssetManager am, World world, float ratioMeterPixel) {
        super(am, world, ratioMeterPixel);
    }

    protected void createInnerElements () {
        // Obstacle bars
        addElement(new Element(
                getAssetManager(), getWorld(),
                4.5f, 3.5f, 9, 1, 0,
                Element.Flavor.GREY, Element.Geometry.SQUARE,
                getRatioMeterPixel()
        ));

        addElement(new Element(
                getAssetManager(), getWorld(),
                7.5f, 7.5f, 9, 1, 0,
                Element.Flavor.GREY, Element.Geometry.SQUARE,
                getRatioMeterPixel()
        ));

        addElement(new Element(
                getAssetManager(), getWorld(),
                4.5f, 11.5f, 9, 1, 0,
                Element.Flavor.GREY, Element.Geometry.SQUARE,
                getRatioMeterPixel()
        ));

        addElement(new Element(
                getAssetManager(), getWorld(),
                7.5f, 15.5f, 9, 1, 0,
                Element.Flavor.GREY, Element.Geometry.SQUARE,
                getRatioMeterPixel()
        ));
    }

}
