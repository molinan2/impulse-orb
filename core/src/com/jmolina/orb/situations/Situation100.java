package com.jmolina.orb.situations;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.physics.box2d.World;
import com.jmolina.orb.elements.Element;


public class Situation100 extends SideWalledSituation {

    public Situation100(AssetManager am, World world) {
        super(am, world);
    }

    protected void createInnerElements () {
        // Bottom walls
        addElement(new Element(
                getAssetManager(), getWorld(),
                6, -9 + 0.5f, 12, 18, 0,
                Element.Behaviour.BLACK, Element.Geometry.SQUARE
        ));

        addElement(new Element(
                getAssetManager(), getWorld(),
                -6 + 0.5f, -9 + 0.5f, 12, 18, 0,
                Element.Behaviour.BLACK, Element.Geometry.SQUARE
        ));

        addElement(new Element(
                getAssetManager(), getWorld(),
                18 - 0.5f, -9 + 0.5f, 12, 18, 0,
                Element.Behaviour.BLACK, Element.Geometry.SQUARE
        ));
    }

}
