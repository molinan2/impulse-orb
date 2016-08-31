package com.jmolina.orb.situations;

import com.jmolina.orb.managers.AssetManager;
import com.badlogic.gdx.physics.box2d.World;
import com.jmolina.orb.elements.Element;

/**
 * Situacion basica de conveniencia. Contiene muros laterales y superiores.
 */
public class TopWalls extends SideWalls {

    /**
     * Constructor
     *
     * @param am AssetManager
     * @param world Mundo fisico
     * @param pixelsPerMeter Factor de correcion pixeles/metros
     */
    public TopWalls(AssetManager am, World world, float pixelsPerMeter) {
        super(am, world, pixelsPerMeter);
    }

    @Override
    protected void createInnerElements () {
        // Muro arriba centro
        addElement(new Element(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                Element.Geometry.SQUARE, Element.Flavor.BLACK,
                13, 18, 6, 27, 0
        ));

        // Muro arriba izquierda
        addElement(new Element(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                Element.Geometry.SQUARE, Element.Flavor.BLACK,
                13, 19, -5.5f, 27, 0
        ));

        // Muro arriba derecha
        addElement(new Element(
                getAssetManager(), getWorld(), getPixelsPerMeter(),
                Element.Geometry.SQUARE, Element.Flavor.BLACK,
                13, 19, 17.5f, 27, 0
        ));
    }

}
