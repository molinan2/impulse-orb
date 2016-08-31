package com.jmolina.orb.situations;

import com.jmolina.orb.managers.AssetManager;
import com.badlogic.gdx.physics.box2d.World;
import com.jmolina.orb.elements.Element;

/**
 * Situacion basica de conveniencia. Contiene muros laterales.
 */
public abstract class SideWalls extends Situation {

    /**
     * Constructor
     *
     * @param am AssetManager
     * @param world Mundo fisico
     * @param pixelsPerMeter Factor de correcion pixeles/metros
     */
    public SideWalls(AssetManager am, World world, float pixelsPerMeter) {
        super(am, world, pixelsPerMeter);
    }

    /**
     * Crea los elementos interiores de la situacion
     */
    protected abstract void createInnerElements();

    @Override
    protected void createElements () {
        createInnerElements();

        // Muro izquierdo
        addElement(new Element(
                getAssetManager(), getWorld(),
                getPixelsPerMeter(), Element.Geometry.SQUARE, Element.Flavor.BLACK,
                12, 19, -5.5f, 9, 0
        ));

        // Muro derecho
        addElement(new Element(
                getAssetManager(), getWorld(),
                getPixelsPerMeter(), Element.Geometry.SQUARE, Element.Flavor.BLACK,
                12, 19, 17.5f, 9, 0
        ));
    }

}
