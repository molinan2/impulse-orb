package com.jmolina.orb.elements;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.jmolina.orb.assets.Asset;

public class Orb {

    private final float DENSITY = 1.0f;
    private final float RESTITUTION = 0.6f;
    private final float FRICTION = 0.8f; // friction=0 evita rotaciones al chocar
    private final float DIAMETER = 1f;
    private final float DEFAULT_X = 0f;
    private final float DEFAULT_Y = 0f;
    private final float INFINITE_DAMPING = 10000f;


    private BaseActor actor;
    private Body body;
    private BodyDef bodyDef;
    private FixtureDef fixtureDef;
    private Fixture fixture;
    private AssetManager assetManager;
    private float pixelsPerMeter;
    private boolean locked;

    public Orb(AssetManager am, World world, float ppm) {
        assetManager = am;
        pixelsPerMeter = ppm;

        createFixture();
        createBody(world);

        Texture texture = assetManager.get(Asset.GAME_ORB, Texture.class);
        createActor(texture, DIAMETER);

        locked = false;
    }

    public void createFixture() {
        fixtureDef = new FixtureDef();
        CircleShape circle = new CircleShape();
        circle.setRadius(0.5f * DIAMETER);
        fixtureDef.shape = circle;
        fixtureDef.density = DENSITY;
        fixtureDef.restitution = RESTITUTION;
        fixtureDef.friction = FRICTION;
    }

    /**
     * @param world Mundo físico
     */
    public void createBody (World world) {
        bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(DEFAULT_X, DEFAULT_Y);
        bodyDef.angle = 0;
        body = world.createBody(bodyDef);
        body.createFixture(fixtureDef);
        fixtureDef.shape.dispose();
    }

    /**
     *
     * @param texture
     * @param diameter Diametro en unidades del Mundo
     */
    public void createActor(Texture texture, float diameter) {
        actor = new BaseActor(texture);

        float scale = pixelsPerMeter * diameter / texture.getWidth();
        actor.setScale(scale);
    }

    public Actor getActor() {
        return actor;
    }

    public Body getBody() {
        return body;
    }

    /**
     * Grid position
     */
    public void setPosition(float x, float y) {
        getBody().setTransform(x, y, getBody().getAngle());
    }

    /**
     * Traduce unidades de World a Stage
     * Camera#project no funciona correctamente
     */
    public void syncActor(Viewport viewport, float sceneWidth, float sceneHeight, float ratio) {
        if (actor != null) {
            float offsetX = sceneWidth * 0.5f;
            float offsetY = sceneHeight * 0.5f;
            float inverseRatio = 1f / ratio;

            actor.setPosition(
                    inverseRatio * (body.getPosition().x - (viewport.getCamera().position.x - offsetX)) - 0.5f * actor.getWidth(),
                    inverseRatio * (body.getPosition().y - (viewport.getCamera().position.y - offsetY)) - 0.5f * actor.getHeight()
            );

            actor.setRotation(MathUtils.radiansToDegrees * body.getAngle());
        }
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
