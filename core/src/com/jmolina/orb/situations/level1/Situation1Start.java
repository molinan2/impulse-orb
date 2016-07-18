package com.jmolina.orb.situations.level1;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.physics.box2d.World;
import com.jmolina.orb.elements.Element;
import com.jmolina.orb.elements.Init;
import com.jmolina.orb.situations.SideWalledSituation;


public class Situation1Start extends SideWalledSituation {

    public Situation1Start(AssetManager am, World world, float pixelsPerMeter) {
        super(am, world, pixelsPerMeter);
    }

    protected void createInnerElements () {
        // Init
        addElement(new Init(
                getAssetManager(), getWorld(),
                6, 4,
                getPixelsPerMeter()
        ));

        // Bottom walls
        addElement(new Element(
                getAssetManager(), getWorld(),
                6, -9 + 0.5f, 12, 18, 0,
                Element.Flavor.BLACK, Element.Geometry.SQUARE,
                getPixelsPerMeter()
        ));

        addElement(new Element(
                getAssetManager(), getWorld(),
                -6 + 0.5f, -9 + 0.5f, 12, 18, 0,
                Element.Flavor.BLACK, Element.Geometry.SQUARE,
                getPixelsPerMeter()
        ));

        addElement(new Element(
                getAssetManager(), getWorld(),
                18 - 0.5f, -9 + 0.5f, 12, 18, 0,
                Element.Flavor.BLACK, Element.Geometry.SQUARE,
                getPixelsPerMeter()
        ));
    }

}
