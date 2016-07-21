package com.jmolina.orb.situations.level1;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.RepeatAction;
import com.jmolina.orb.elements.Element;
import com.jmolina.orb.elements.Init;
import com.jmolina.orb.situations.SideWalledSituation;
import com.jmolina.orb.utils.Utils;


public class Situation1Start extends SideWalledSituation {

    public Situation1Start(AssetManager am, World world, float pixelsPerMeter) {
        super(am, world, pixelsPerMeter);
    }

    protected void createInnerElements () {
        // Test Rotable
        Element rotable = new Element(
                getAssetManager(), getWorld(),
                6, 8, 2, 2, 0,
                Element.Flavor.GREY, Element.Geometry.SQUARE,
                getPixelsPerMeter()
        );

        addElement(rotable);

        float correctionFactor = getPixelsPerMeter() / Utils.cell(1);

        rotable.getActor().addAction(Actions.repeat(RepeatAction.FOREVER, Actions.rotateBy(360, 4)));
        rotable.getActor().addAction(Actions.moveBy(cells(4), 0, 1f));
        // todo es necesario corregir los desplazamientos de las acciones por zoom




        // Test (0,0)
        addElement(new Element(
                getAssetManager(), getWorld(),
                0, 0, 1, 1, 0,
                Element.Flavor.GREY, Element.Geometry.SQUARE,
                getPixelsPerMeter()
        ));


        // Init
        addElement(new Init(
                getAssetManager(), getWorld(),
                6, 4,
                getPixelsPerMeter()
        ));

        // Bottom walls
        addElement(new Element(
                getAssetManager(), getWorld(),
                6, -9 + 0.5f, 12, 18, 0,
                Element.Flavor.BLACK, Element.Geometry.SQUARE,
                getPixelsPerMeter()
        ));

        addElement(new Element(
                getAssetManager(), getWorld(),
                -6 + 0.5f, -9 + 0.5f, 12, 18, 0,
                Element.Flavor.BLACK, Element.Geometry.SQUARE,
                getPixelsPerMeter()
        ));

        addElement(new Element(
                getAssetManager(), getWorld(),
                18 - 0.5f, -9 + 0.5f, 12, 18, 0,
                Element.Flavor.BLACK, Element.Geometry.SQUARE,
                getPixelsPerMeter()
        ));
    }

}
