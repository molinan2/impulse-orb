package com.jmolina.orb.situations;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.physics.box2d.World;
import com.jmolina.orb.elements.Element;


public class Situation103 extends SideWalledSituation {

    public Situation103(AssetManager am, World world) {
        super(am, world);
    }

    protected void createInnerElements () {
        // Obstacle bars
        addElement(new Element(
                getAssetManager(), getWorld(),
                4.5f, 3.5f, 9, 1, 0,
                Element.Behaviour.GREY, Element.Geometry.SQUARE
        ));

        addElement(new Element(
                getAssetManager(), getWorld(),
                7.5f, 7.5f, 9, 1, 0,
                Element.Behaviour.GREY, Element.Geometry.SQUARE
        ));

        addElement(new Element(
                getAssetManager(), getWorld(),
                4.5f, 11.5f, 9, 1, 0,
                Element.Behaviour.GREY, Element.Geometry.SQUARE
        ));

        addElement(new Element(
                getAssetManager(), getWorld(),
                7.5f, 15.5f, 9, 1, 0,
                Element.Behaviour.GREY, Element.Geometry.SQUARE
        ));
    }

}
