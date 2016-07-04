package com.jmolina.orb.elements;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.jmolina.orb.assets.Asset;

public class Orb extends Element {

    private final float DIAMETER = 1f;
    private final float DEFAULT_X = 0f;
    private final float DEFAULT_Y = 0f;
    private final float INFINITE_DAMPING = 10000f;

    private boolean locked;

    public Orb(AssetManager am, World world) {
        super(am, world, 0, 0, 1f, 1f, 0, Behaviour.RED, Geometry.CIRCLE);
        getActor().setTexture(am.get(Asset.GAME_ORB, Texture.class));
        getBody().setType(BodyDef.BodyType.DynamicBody);
        locked = false;
    }

    /**
     * Opciones:
     *
     * - Hacer cero LinearVelocity y AngularVelocity
     * - Hacer infinito LinearDamping y AngularDamping
     * - setActive(false)
     */
    public void lock() {
        getBody().setLinearDamping(INFINITE_DAMPING);
        getBody().setAngularDamping(INFINITE_DAMPING);
        locked = true;
    }

    public void unlock() {
        getBody().setLinearDamping(0);
        getBody().setAngularDamping(0);
        locked = false;
    }

    public boolean isLocked() {
        return locked;
    }

}
