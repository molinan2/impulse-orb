package com.jmolina.orb.elements;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.World;
import com.jmolina.orb.managers.AssetManager;
import com.jmolina.orb.var.Asset;
import com.jmolina.orb.var.AssetAtlas;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveBy;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.rotateBy;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;


/**
 * Elemento calentador
 *
 * TODO
 * Extender de Movable
 * Cambiar textura: que sea lisa
 * Que las proporciones sea variables (w, h)
 */
public class Heater extends Element {

    private final float TICK_AMOUNT = 0.2f;
    private final float TICK_PERIOD = 0.75f;

    /**
     * {@inheritDoc}
     *
     * Crea un elemento etéreo calentador de dimensiones fijas: 10x10.
     */
    public Heater(AssetManager am, World world, float ppm, float x, float y) {
        super(am, world, am.getAtlas().findRegion(AssetAtlas.GAME_HEAT), ppm,
                Geometry.SQUARE, Flavor.BLUE,
                10, 10, x, y, 0
        );

        setAsSensor(true);
        setEffect(Effect.HEAT);
        getUserData().tick.amount = TICK_AMOUNT;
        getUserData().tick.period = TICK_PERIOD;
    }

}
