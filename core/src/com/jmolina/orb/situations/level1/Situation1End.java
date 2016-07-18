package com.jmolina.orb.situations.level1;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.physics.box2d.World;
import com.jmolina.orb.elements.Element;
import com.jmolina.orb.elements.Exit;
import com.jmolina.orb.situations.SideWalledSituation;


public class Situation1End extends SideWalledSituation {

    public Situation1End(AssetManager am, World world, float ratioMeterPixel) {
        super(am, world, ratioMeterPixel);
    }

    protected void createInnerElements () {
        // Exit
        addElement(new Exit(
                getAssetManager(), getWorld(),
                6, 13,
                getRatioMeterPixel()
        ));

        // Top walls
        addElement(new Element(
                getAssetManager(), getWorld(),
                6, 27, 12, 18, 0,
                Element.Flavor.BLACK, Element.Geometry.SQUARE,
                getRatioMeterPixel()
        ));

        addElement(new Element(
                getAssetManager(), getWorld(),
                -6 + 0.5f, 27, 12, 18, 0,
                Element.Flavor.BLACK, Element.Geometry.SQUARE,
                getRatioMeterPixel()
        ));

        addElement(new Element(
                getAssetManager(), getWorld(),
                18 - 0.5f, 27, 12, 18, 0,
                Element.Flavor.BLACK, Element.Geometry.SQUARE,
                getRatioMeterPixel()
        ));
    }

}
