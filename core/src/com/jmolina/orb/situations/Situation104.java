package com.jmolina.orb.situations;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.physics.box2d.World;
import com.jmolina.orb.elements.Element;


public class Situation104 extends SideWalledSituation {

    public Situation104(AssetManager am, World world) {
        super(am, world);
    }

    protected void createInnerElements () {
        // Obstacle balls
        addElement(new Element(
                getAssetManager(), getWorld(),
                4f, 5f, 4, 4, 0,
                Element.Behaviour.GREY, Element.Geometry.CIRCLE
        ));

        addElement(new Element(
                getAssetManager(), getWorld(),
                8f, 12f, 4, 4, 0,
                Element.Behaviour.GREY, Element.Geometry.CIRCLE
        ));
    }

}
