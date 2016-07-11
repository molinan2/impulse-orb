package com.jmolina.orb.elements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.jmolina.orb.assets.Asset;
import com.jmolina.orb.widgets.FragmentedOrb;

/**
 * TODO
 * Heat:
 * Cuando se llegue al 100%, se mantiene bloqueado (OVERLOAD) al 100% durante X segundos.
 * Si estando en estado OVERLOAD se recibe otro tap, el orbe se destruye.
 * Pasados X segundos, se reanuda el COOLING.
 *
 * TODO
 * Pintar de rojo intermitente la barra cuando este overload
 * Comprobar en level que esta overload. Si esta overload, disminuir el tiempo de overload, pero no la barra
 *
 * TODO
 * Los Override denotan que Orb no deriva exactamente de Element
 * Habria que refactorizar (BasicElement -> Orb, BasicElement -> Element -> (Todos))
 *
 * TODO
 * Cuando se llegue a Overload, que se ponga naranja (cambio de textura en FragmentedOrb
 */
public class Orb extends Element {

    private final float DIAMETER = 1f;
    private final float INFINITE_DAMPING = 10000f;
    public static final float HEAT_MIN = 0f;
    public static final float HEAT_MAX = 1f;
    public static final float HEAT_DEFAULT_INCREMENT = 0.2f;
    public static final float OVERLOAD_TIME = 5f;

    private boolean locked = false;
    private boolean overloaded = false;
    private float heat = 0f;
    private FragmentedOrb fragmentedActor;

    public boolean disposing = false;

    public Orb(AssetManager am, World world) {
        super(am, world, 6, 2, 1f, 1f, 0, Type.GREY, Geometry.CIRCLE);

        getActor().setTexture(am.get(Asset.GAME_ORB, Texture.class));
        getBody().setType(BodyDef.BodyType.DynamicBody);

        // Evita que se quede dormido después de lock().
        // ¡La Gravedad no despierta a un objeto dormido!
        getBody().setSleepingAllowed(false);

        fragmentedActor = new FragmentedOrb(am);
        fragmentedActor.setTouchable(Touchable.disabled);
        // fragmentedActor.setVisible(false);
        fragmentedActor.setPositionGrid(3, 3);
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
        if (!isOverloaded()) {
            heat = MathUtils.clamp(this.heat + increment, HEAT_MIN, HEAT_MAX);
        }
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

    public Actor getFragmentedActor() {
        return fragmentedActor;
    }

    @Override
    public void syncBody() {
        // TODO
        // Iguala la posicion

        // Iguala solo la rotacion
        if (fragmentedActor != null) {
            // TODO
            // La llamada a Body#setTransform peta la JVM cuando se hace Level#dispose()
            // Comprobamos que no se ha hecho Level#dispose() antes de ejecutar Body#setTransform
            if (!disposing) {
                getBody().setTransform(
                        getBody().getPosition().x,
                        getBody().getPosition().y,
                        MathUtils.degreesToRadians * fragmentedActor.getRotation()
                );
            }
        }
    }

    @Override
    public void syncActor(Viewport viewport, float worldWidth, float worldHeight, float pixelsPerMeter) {
        super.syncActor(viewport, worldWidth, worldHeight, pixelsPerMeter);

        if (fragmentedActor != null) {
            float offsetX = worldWidth * 0.5f;
            float offsetY = worldHeight * 0.5f;

            fragmentedActor.setPosition(
                    pixelsPerMeter * (getBody().getPosition().x - (viewport.getCamera().position.x - offsetX)) - 0.5f * fragmentedActor.getWidth(),
                    pixelsPerMeter * (getBody().getPosition().y - (viewport.getCamera().position.y - offsetY)) - 0.5f * fragmentedActor.getHeight()
            );

            fragmentedActor.setRotation(MathUtils.radiansToDegrees * getBody().getAngle());
        }
    }

    public void destroy() {
        fragmentedActor.destroy();
    }

    public void resetFragments() {
        fragmentedActor.reset();
    }

    public boolean isOverloaded() {
        return overloaded;
    }

    public void setOverloaded(boolean overloaded) {
        this.overloaded = overloaded;
    }

}
