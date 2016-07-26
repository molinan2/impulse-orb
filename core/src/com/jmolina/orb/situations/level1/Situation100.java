package com.jmolina.orb.situations.level1;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.physics.box2d.World;
import com.jmolina.orb.elements.Element;
import com.jmolina.orb.elements.HotElement;
import com.jmolina.orb.elements.Init;
import com.jmolina.orb.elements.MovingElement;
import com.jmolina.orb.situations.SideWalledSituation;


public class Situation100 extends SideWalledSituation {

    public Situation100(AssetManager am, World world, float pixelsPerMeter) {
        super(am, world, pixelsPerMeter);
    }

    protected void createInnerElements () {
        // Test Rotable
        MovingElement movingElement = new MovingElement(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                Element.Geometry.SQUARE, Element.Flavor.GREY,
                6, 8, 2, 2, 0
        );

        movingElement.addRotation(0.5f);
        movingElement.addDisplacement(0.25f, 4);
        addElement(movingElement);



        // Test Hot
        HotElement hotElement = new HotElement(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                0, 0
        );

        addElement(hotElement);





        // Test (0,0)
        addElement(new Element(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                Element.Geometry.SQUARE, Element.Flavor.GREY,
                0, 0, 1, 1, 0
        ));


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
