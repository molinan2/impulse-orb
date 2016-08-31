package com.jmolina.orb.elements;

import com.jmolina.orb.managers.AssetManager;
import com.badlogic.gdx.physics.box2d.World;
import com.jmolina.orb.var.Atlas;

/**
 * Elemento de salida
 */
public class Exit extends Element {

    /**
     * Constructor
     *
     * @param am AssetManager
     * @param world Mundo fisico
     * @param pixelsPerMeter Ratio de conversion pixeles/metros
     * @param x Coordenada X de la posición en unidades del mundo
     * @param y Coordenada Y de la posición en unidades del mundo
     */
    public Exit(AssetManager am, World world, float pixelsPerMeter, float x, float y) {
        super(am, world, am.getGameAtlas().findRegion(Atlas.GAME_EXIT), pixelsPerMeter,
                Geometry.SQUARE, Flavor.BLUE,
                10, 4, x, y, 0
        );

        setAsSensor(true);
        setEffect(Effect.EXIT);
    }

}
