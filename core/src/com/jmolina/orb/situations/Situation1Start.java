package com.jmolina.orb.situations;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.physics.box2d.World;
import com.jmolina.orb.elements.Element;
import com.jmolina.orb.elements.Init;


public class Situation1Start extends SideWalledSituation {

    public Situation1Start(AssetManager am, World world) {
        super(am, world);
    }

    protected void createInnerElements () {
        // Init
        addElement(new Init(
                getAssetManager(), getWorld(),
                6, 4
        ));

        // Bottom walls
        addElement(new Element(
                getAssetManager(), getWorld(),
                6, -9 + 0.5f, 12, 18, 0,
                Element.Type.BLACK, Element.Geometry.SQUARE
        ));

        addElement(new Element(
                getAssetManager(), getWorld(),
                -6 + 0.5f, -9 + 0.5f, 12, 18, 0,
                Element.Type.BLACK, Element.Geometry.SQUARE
        ));

        addElement(new Element(
                getAssetManager(), getWorld(),
                18 - 0.5f, -9 + 0.5f, 12, 18, 0,
                Element.Type.BLACK, Element.Geometry.SQUARE
        ));
    }

}
