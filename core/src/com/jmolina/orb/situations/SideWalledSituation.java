package com.jmolina.orb.situations;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.physics.box2d.World;
import com.jmolina.orb.elements.Element;


public abstract class SideWalledSituation extends Situation {

    public SideWalledSituation(AssetManager am, World world) {
        super(am, world);
    }

    protected abstract void createInnerElements();

    protected void createElements () {
        createInnerElements();

        // Side walls
        addElement(new Element(
                getAssetManager(), getWorld(),
                -6 + 0.5f, 9, 12, 18, 0,
                Element.Type.BLACK, Element.Geometry.SQUARE
        ));

        addElement(new Element(
                getAssetManager(), getWorld(),
                12 + 6 - 0.5f, 9, 12, 18, 0,
                Element.Type.BLACK, Element.Geometry.SQUARE
        ));
    }

}
