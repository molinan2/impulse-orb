package com.jmolina.orb.situations;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.physics.box2d.World;
import com.jmolina.orb.elements.Element;
import com.jmolina.orb.elements.Exit;


public class Situation1End extends SideWalledSituation {

    public Situation1End(AssetManager am, World world) {
        super(am, world);
    }

    protected void createInnerElements () {
        // Exit
        addElement(new Exit(
                getAssetManager(), getWorld(),
                6, 13
        ));

        // Top walls
        addElement(new Element(
                getAssetManager(), getWorld(),
                6, 27, 12, 18, 0,
                Element.Type.BLACK, Element.Geometry.SQUARE
        ));

        addElement(new Element(
                getAssetManager(), getWorld(),
                -6 + 0.5f, 27, 12, 18, 0,
                Element.Type.BLACK, Element.Geometry.SQUARE
        ));

        addElement(new Element(
                getAssetManager(), getWorld(),
                18 - 0.5f, 27, 12, 18, 0,
                Element.Type.BLACK, Element.Geometry.SQUARE
        ));
    }

}
