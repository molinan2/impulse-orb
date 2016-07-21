package com.jmolina.orb.elements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.jmolina.orb.actions.GameAction;
import com.jmolina.orb.widgets.OrbFragments;

/**
 * TODO: De color naranja cuando esté en Overload
 */
public class Orb extends Element {

    private final float DIAMETER = 1.0f;
    private final float INFINITE_DAMPING = 10000f;
    private final float HEAT_MIN = 0f;
    private final float HEAT_MAX = 1f;
    private final float HEAT_INCREMENT = 0.2f;
    private final float OVERLOAD_TIME = 3f;
    private final float COOLING_RATE = 0.1f;
    private final float FREEZE_TIME = 0.90f;

    private boolean frozen, overloaded;
    private float heat, naturalScale, freezeTime, overloadTime;
    private OrbFragments fragments;

    public Orb(AssetManager am, World world, float pixelsPerMeter) {
        // No se pueden pasar constantes al constructor de super() antes de inicializar el objeto
        super(am, world, 6, 2, 1f, 1f, 0, Flavor.GREY, Geometry.CIRCLE, BodyDef.BodyType.DynamicBody, pixelsPerMeter);

        heat = 0f;
        frozen = overloaded = false;
        fragments = new OrbFragments(am);
        naturalScale = pixelsPerMeter * DIAMETER / fragments.getWidth();
        fragments.setScale(naturalScale);
        getBody().setSleepingAllowed(false); // Evita que se quede dormido. ¡La Gravedad no despierta!
    }

    /**
     * Anula las fuerzas que afectan al Orb aplicando un amortiguamiento infinito
     */
    public void freeze() {
        frozen = true;
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


    @Override
    public Actor getActor() {
        return fragments;
    }

    /**
     * No se sincroniza la posición, ya que la cámara siempre sigue.
     */
    @Override
    public void syncBody(Viewport viewport) {
        if (fragments != null) {
            getBody().setTransform(
                    getBody().getPosition().x,
                    getBody().getPosition().y,
                    MathUtils.degreesToRadians * fragments.getRotation()
            );
        }
    }

    @Override
    public void syncActor(Viewport viewport) {
        if (fragments != null) {
            float offsetX = viewport.getWorldWidth() * 0.5f;
            float offsetY = viewport.getWorldHeight() * 0.5f;

            fragments.setPosition(
                    getPixelsPerMeter() * (getBody().getPosition().x - (viewport.getCamera().position.x - offsetX)) - 0.5f * fragments.getWidth(),
                    getPixelsPerMeter() * (getBody().getPosition().y - (viewport.getCamera().position.y - offsetY)) - 0.5f * fragments.getHeight()
            );

            fragments.setRotation(MathUtils.radiansToDegrees * getBody().getAngle());
        }
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
        getActor().addAction(GameAction.orbIntro(this));
    }

    public void outro(Runnable switchToSuccess) {
        getActor().addAction(GameAction.orbOutro(this, switchToSuccess));
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
