package com.jmolina.orb.elements;

import com.badlogic.gdx.Gdx;
import com.jmolina.orb.managers.AssetManager;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.World;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

/**
 *
 */
public class Orb extends Element {

    public static final float INTRO_TIME = 1f;
    public static final float OUTRO_TIME = 1.2f;

    private final float DEFAULT_X = 6;
    private final float DEFAULT_Y = 2;
    private final float DIAMETER = 1.0f;
    private final float INFINITE_DAMPING = 10000f;
    private final float OVER_DAMPING = 10f;
    private final float HEAT_MIN = 0f;
    private final float HEAT_MAX = 1f;
    private final float HEAT_INCREMENT = 0.2f;
    private final float COOLING_RATE = 0.2f;
    private final float OVERLOAD_TIME = 2f;
    private final float FREEZE_TIME = 1f;
    private final float SCALE_CORRECTION = 1.025f;

    private boolean frozen, overloaded;
    private float heat, naturalScale, freezeTime, overloadTime;
    private com.jmolina.orb.actors.Fragments fragments;

    public Orb(AssetManager am, World world, float pixelsPerMeter) {
        super(am, world, pixelsPerMeter, Geometry.CIRCLE, Flavor.GREEN, 1f, 1f, 6, 2, 0);

        heat = 0f;
        frozen = overloaded = false;
        fragments = new com.jmolina.orb.actors.Fragments(am);
        naturalScale = pixelsPerMeter * SCALE_CORRECTION * DIAMETER / fragments.getWidth();
        fragments.setScale(naturalScale);
        setActor(fragments);
        getBody().setSleepingAllowed(false); // Evita que se quede dormido. ¡La Gravedad no despierta!
        setPosition(DEFAULT_X, DEFAULT_Y);
    }

    /**
     * Anula las fuerzas que afectan al Orb aplicando un amortiguamiento infinito
     */
    public void freeze() {
        frozen = true;
        freezeTime = 0f;

        getBody().setLinearDamping(INFINITE_DAMPING);
        getBody().setAngularDamping(INFINITE_DAMPING);
    }

    public void unfreeze() {
        frozen = false;
        freezeTime = 0f;
        getBody().setLinearDamping(0);
        getBody().setAngularDamping(0);
    }

    public void resetVelocity() {
        getBody().setLinearVelocity(0, 0);
        getBody().setAngularVelocity(0);
    }

    public boolean isFrozen() {
        return frozen;
    }

    public void increaseHeat() {
        increaseHeat(HEAT_INCREMENT);
    }

    public void increaseHeat(float increment) {
        if (!isOverloaded()) {
            heat = MathUtils.clamp(this.heat + increment, HEAT_MIN, HEAT_MAX);
        }
    }

    public void decreaseHeat (float decrement) {
        increaseHeat(-decrement);
    }

    private void setHeat(float level) {
        heat = level;
    }

    public void resetHeat () {
        setHeat(HEAT_MIN);
        setOverloaded(false);
    }

    public float getHeat () {
        return heat;
    }

    public boolean isHeatMaxed() {
        return getHeat() >= HEAT_MAX;
    }

    public void destroy() {
        fragments.destroy();
    }

    public boolean isOverloaded() {
        return overloaded;
    }

    public void setOverloaded(boolean overloaded) {
        this.overloaded = overloaded;
        overloadTime = 0f;
    }

    public float getNaturalScale() {
        return naturalScale;
    }

    public void resetAngle() {
        getActor().setRotation(0);
        getBody().setTransform(getBody().getPosition().x, getBody().getPosition().y, 0);
    }

    public void reset(float x, float y) {
        setPosition(x, y);
        resetAngle();
        resetHeat();
        resetVelocity();
        fragments.reset();
        unfreeze();
    }

    public void intro() {
        getActor().addAction(sequence(
                parallel(
                        scaleBy(4 * this.getNaturalScale(), 4 * this.getNaturalScale(), 0),
                        rotateTo(0, 0),
                        alpha(0)
                ),
                parallel(
                        rotateTo(360, INTRO_TIME, Interpolation.exp5),
                        scaleTo(this.getNaturalScale(), this.getNaturalScale(), INTRO_TIME, Interpolation.pow2),
                        fadeIn(INTRO_TIME, Interpolation.pow2)
                )
        ));
    }

    public void outro(Runnable toSuccess) {
        getActor().addAction(sequence(
                parallel(
                        rotateBy(1080, OUTRO_TIME, Interpolation.pow2Out),
                        scaleTo(4 * this.getNaturalScale(), 4 * this.getNaturalScale(), OUTRO_TIME, Interpolation.pow2),
                        fadeOut(OUTRO_TIME, Interpolation.pow2)
                ),
                run(toSuccess)
        ));
    }

    public void updateFreezeTime() {
        if (isFrozen()) {
            freezeTime += Gdx.graphics.getRawDeltaTime();

            if (freezeTime > FREEZE_TIME)
                unfreeze();
        }
    }

    public void updateHeat() {
        if (isOverloaded())
            overloadTime += Gdx.graphics.getRawDeltaTime();
        else
            decreaseHeat(COOLING_RATE * Gdx.graphics.getRawDeltaTime());

        if (overloadTime > OVERLOAD_TIME)
            setOverloaded(false);
    }

}
