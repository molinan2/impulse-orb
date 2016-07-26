package com.jmolina.orb.elements;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.physics.box2d.World;
import com.jmolina.orb.utils.Utils;
import com.jmolina.orb.var.Var;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.forever;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveBy;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.rotateBy;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;


/**
 * Elemento movible y rotable
 */
public class HotElement extends Element {

    private final float TICK = 0.1f;
    private final float FREQUENCY = 2;

    /**
     * {@inheritDoc}
     *
     * Crea un elemento calentador
     */
    public HotElement(AssetManager am, World world, float ppm, Geometry geometry, Flavor flavor, float x, float y, float w, float h, float angle) {
        super(am, world, ppm, geometry, flavor, x, y, w, h, angle);
        setAsSensor(true);
        setEffect(Effect.HEAT);
    }


}
