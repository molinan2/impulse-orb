package com.jmolina.orb.situations;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.physics.box2d.World;
import com.jmolina.orb.elements.Element;


public class Situation107 extends SideWalledSituation {

    public Situation107(AssetManager am, World world) {
        super(am, world);
    }

    protected void createInnerElements () {
        // Grey stripe
        addElement(new Element(
                getAssetManager(), getWorld(),
                3.5f, 5, 10, 1, 45,
                Element.Type.GREY, Element.Geometry.SQUARE
        ));

        // Red stripe
        addElement(new Element(
                getAssetManager(), getWorld(),
                8.5f, 13, 10, 1, -45,
                Element.Type.RED, Element.Geometry.SQUARE
        ));
    }

}
