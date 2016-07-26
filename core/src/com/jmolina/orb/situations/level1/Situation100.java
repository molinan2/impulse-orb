package com.jmolina.orb.situations.level1;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.RepeatAction;
import com.jmolina.orb.elements.Element;
import com.jmolina.orb.elements.Init;
import com.jmolina.orb.situations.SideWalledSituation;


public class Situation100 extends SideWalledSituation {

    public Situation100(AssetManager am, World world, float pixelsPerMeter) {
        super(am, world, pixelsPerMeter);
    }

    protected void createInnerElements () {
        // Test Rotable
        Element rotable = new Element(
                getAssetManager(), getWorld(),
                getPixelsPerMeter(), 6, 8, 2, 2, 0,
                Element.Geometry.SQUARE, Element.Flavor.GREY
        );

        addElement(rotable);

        rotable.getActor().addAction(Actions.repeat(RepeatAction.FOREVER, Actions.rotateBy(360, 4)));
        rotable.getActor().addAction(Actions.moveBy(cells(4), 0, 1f));





        // Test (0,0)
        addElement(new Element(
                getAssetManager(), getWorld(),
                getPixelsPerMeter(), 0, 0, 1, 1, 0,
                Element.Geometry.SQUARE, Element.Flavor.GREY
        ));


        // Init
        addElement(new Init(
                getAssetManager(), getWorld(),
                6, 4,
                getPixelsPerMeter()
        ));

        // Bottom walls
        addElement(new Element(
                getAssetManager(), getWorld(),
                getPixelsPerMeter(), 6, -9 + 0.5f, 12, 18, 0,
                Element.Geometry.SQUARE, Element.Flavor.BLACK
        ));

        addElement(new Element(
                getAssetManager(), getWorld(),
                getPixelsPerMeter(), -6 + 0.5f, -9 + 0.5f, 12, 18, 0,
                Element.Geometry.SQUARE, Element.Flavor.BLACK
        ));

        addElement(new Element(
                getAssetManager(), getWorld(),
                getPixelsPerMeter(), 18 - 0.5f, -9 + 0.5f, 12, 18, 0,
                Element.Geometry.SQUARE, Element.Flavor.BLACK
        ));
    }

}
