package com.jmolina.orb.situations;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.physics.box2d.World;
import com.jmolina.orb.elements.Element;


public class Situation106 extends SideWalledSituation {

    public Situation106(AssetManager am, World world) {
        super(am, world);
    }

    protected void createInnerElements () {
        // Red boxes
        addElement(new Element(
                getAssetManager(), getWorld(),
                0, 7, 7, 7, 45,
                Element.Behaviour.RED, Element.Geometry.SQUARE
        ));

        addElement(new Element(
                getAssetManager(), getWorld(),
                12, 12, 7, 7, 45,
                Element.Behaviour.RED, Element.Geometry.SQUARE
        ));
    }

}
