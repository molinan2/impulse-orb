package com.jmolina.orb.elements;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.jmolina.orb.var.Asset;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveBy;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.rotateBy;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;


/**
 * Elemento movible y rotable
 */
public class Heater extends BaseElement {

    private final float TICK_AMOUNT = 0.2f;
    private final float TICK_PERIOD = 0.75f;

    /**
     * {@inheritDoc}
     *
     * Crea un elemento etéreo calentador de dimensiones fijas: 10x10.
     */
    public Heater(AssetManager am, World world, float ppm, float x, float y) {
        super(am, world, am.get(Asset.GAME_HEAT, Texture.class), ppm,
                Geometry.SQUARE, Flavor.BLUE, BodyDef.BodyType.KinematicBody,
                x, y, 10, 10, 0
        );

        setAsSensor(true);
        setEffect(Effect.HEAT);
        getUserData().tick.amount = TICK_AMOUNT;
        getUserData().tick.period = TICK_PERIOD;
    }

}
