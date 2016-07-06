package com.jmolina.orb.elements;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.jmolina.orb.assets.Asset;

public class Orb extends Element {

    private final float DIAMETER = 1f;
    private final float INFINITE_DAMPING = 10000f;
    private final float HEAT_MIN = 0f;
    private final float HEAT_MAX = 1f;
    private final float HEAT_DEFAULT_INCREMENT = 0.2f;

    private boolean locked = false;
    private float heat = 0f;

    public Orb(AssetManager am, World world) {
        super(am, world, 6, 2, 1f, 1f, 0, Type.GREY, Geometry.CIRCLE);

        getActor().setTexture(am.get(Asset.GAME_ORB, Texture.class));
        getBody().setType(BodyDef.BodyType.DynamicBody);

        // Evita que se quede dormido después de lock(). ¡La Gravedad no despierta a un objeto dormido!
        getBody().setSleepingAllowed(false);
    }

    /**
     * Anula las fuerzas que afectan al Orb aplicando un amortiguamente infinito.
     */
    public void lock() {
        locked = true;
        getBody().setLinearDamping(INFINITE_DAMPING);
        getBody().setAngularDamping(INFINITE_DAMPING);
    }

    public void unlock() {
        locked = false;
        getBody().setLinearDamping(0);
        getBody().setAngularDamping(0);
    }

    public void resetVelocity() {
        getBody().setLinearVelocity(0, 0);
        getBody().setAngularVelocity(0);
    }

    public boolean isLocked() {
        return locked;
    }

    public void increaseHeat() {
        increaseHeat(HEAT_DEFAULT_INCREMENT);
    }

    public void increaseHeat(float increment) {
        heat = MathUtils.clamp(this.heat + increment, HEAT_MIN, HEAT_MAX);
    }

    public void decreaseHeat (float decrement) {
        increaseHeat(-decrement);
    }

    public void resetHeat () {
        heat = HEAT_MIN;
    }

    public float getHeat () {
        return heat;
    }

}
