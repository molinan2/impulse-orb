package com.jmolina.orb.situations;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.physics.box2d.World;
import com.jmolina.orb.elements.Element;


public class Situation101 extends SideWalledSituation {

    public Situation101(AssetManager am, World world) {
        super(am, world);
    }

    protected void createInnerElements () {
        // Obstacle ball
        addElement(new Element(
                getAssetManager(), getWorld(),
                6, 14, 4, 4, 0,
                Element.Behaviour.GREY, Element.Geometry.CIRCLE
        ));

        // Lateral red boxes
        addElement(new Element(
                getAssetManager(), getWorld(),
                0.75f, 10, 0.5f, 4, 0,
                Element.Behaviour.RED, Element.Geometry.SQUARE
        ));

        addElement(new Element(
                getAssetManager(), getWorld(),
                11.25f, 10, 0.5f, 4, 0,
                Element.Behaviour.RED, Element.Geometry.SQUARE
        ));
    }

}
