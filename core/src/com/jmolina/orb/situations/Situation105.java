package com.jmolina.orb.situations;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.physics.box2d.World;
import com.jmolina.orb.elements.Element;


public class Situation105 extends SideWalledSituation {

    public Situation105(AssetManager am, World world) {
        super(am, world);
    }

    protected void createInnerElements () {
        // Center box obstacle with red borders
        addElement(new Element(
                getAssetManager(), getWorld(),
                6, 10, 4, 4, 45,
                Element.Type.GREY, Element.Geometry.SQUARE
        ));

        addElement(new Element(
                getAssetManager(), getWorld(),
                7.5f, 11.5f, 4, 0.5f, -45,
                Element.Type.RED, Element.Geometry.SQUARE
        ));

        addElement(new Element(
                getAssetManager(), getWorld(),
                4.5f, 8.5f, 4, 0.5f, -45,
                Element.Type.RED, Element.Geometry.SQUARE
        ));
    }

}
