package com.jmolina.orb.elements;

import com.jmolina.orb.managers.AssetManager;
import com.badlogic.gdx.physics.box2d.World;
import com.jmolina.orb.var.Atlas;

/**
 * Flechas de direccion. Son un elemento solo visual, que no interactua.
 */
public class Up extends Element {

    /**
     * Constructor
     *
     * @param am AssetManager
     * @param world Mundo fisico
     * @param pixelsPerMeter Ratio de conversion pixeles/metros
     * @param x Coordenada X de la posicion en unidades del mundo
     * @param y Coordenada Y de la posicion en unidades del mundo
     */
    public Up(AssetManager am, World world, float pixelsPerMeter, float x, float y) {
        super(am, world, am.getGameAtlas().findRegion(Atlas.GAME_UP), pixelsPerMeter,
                Geometry.SQUARE, Flavor.BLUE,
                10, 4, x, y, 0
        );

        setAsSensor(true);
        setEffect(Effect.NONE);
    }

}
