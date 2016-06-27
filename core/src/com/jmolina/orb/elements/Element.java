package com.jmolina.orb.elements;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.viewport.Viewport;


public class Element {

    private final float DENSITY_DEFAULT = 1.0f;
    private final float RESTITUTION_DEFAULT = 0.8f;
    private final float FRICTION_DEFAULT = 0.0f; // friction=0 evita rotaciones al chocar


    public enum Type {BLACK, GREY, RED, DUMMY}

    static public class ElementConfig {
        public AssetManager assetManager;
        public World world;
        public float pixelsPerMeter = 1f;
        public float x;
        public float y;
        public float rotation = 0f;
        public Type type;

        protected ElementConfig(AssetManager am, World world) {
            this.assetManager = am;
            this.world = world;
        }

        protected ElementConfig(AssetManager am, World world, float pixelsPerMeter) {
            this.assetManager = am;
            this.world = world;
            this.pixelsPerMeter = pixelsPerMeter;
        }

        /**
         * Convierte a unidades de Grid teniendo en cuenta la cantidad de pixeles por metro
         */
        public void setPosition(float x, float y) {
            this.x = this.pixelsPerMeter * x;
            this.y = this.pixelsPerMeter * y;
        }
    }

    private BaseActor actor;
    private Body body;
    private BodyDef bodyDef;
    private FixtureDef fixtureDef;
    private Fixture fixture;
    private AssetManager assetManager;
    private float pixelsPerMeter = 1f;

    protected Element(ElementConfig config) {
        setAssetManager(config.assetManager);
        pixelsPerMeter = config.pixelsPerMeter;
    }

    public void setAssetManager(AssetManager am) {
        this.assetManager = am;
    }

    public synchronized <T> T getAsset (String fileName) {
        return this.assetManager.get(fileName);
    }

    public synchronized <T> T getAsset (String fileName, Class<T> type) {
        return this.assetManager.get(fileName, type);
    }

    public void createFixture(Shape shape) {
        createFixture(shape, DENSITY_DEFAULT, RESTITUTION_DEFAULT, FRICTION_DEFAULT);
    }

    public void createFixture(Shape shape, float density, float restitution, float friction) {
        fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = density;
        fixtureDef.restitution = restitution;
        fixtureDef.friction = friction;
    }

    public void createBody (World world, BodyDef.BodyType type, float x, float y) {
        createBody(world, type, x, y, 0);
    }

    public void createBody (World world, BodyDef.BodyType type, float x, float y, float rotation) {
        bodyDef = new BodyDef();
        bodyDef.type = type;
        bodyDef.position.set(x, y);
        bodyDef.angle = rotation * MathUtils.degreesToRadians;
        body = world.createBody(bodyDef);
        body.createFixture(fixtureDef);
        fixtureDef.shape.dispose();
    }

    public void createActor(Texture texture) {
        createActor(texture, 1, 1);
    }

    public void createActor(Texture texture, float scale) {
        createActor(texture, scale, scale);
    }

    public void createActor(Texture texture, float scaleX, float scaleY) {
        actor = new BaseActor(texture);
        actor.setScale(scaleX, scaleY);
    }

    public void createActor(Texture texture, float scaleX, float scaleY, float rotation) {
        actor = new BaseActor(texture);
        actor.setScale(scaleX, scaleY);
        actor.setRotation(rotation);
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
        getBody().setTransform(
                x * this.pixelsPerMeter,
                y * this.pixelsPerMeter,
                getBody().getAngle()
        );
    }

    public Vector2 getPosition () {
        Vector2 position = new Vector2();

        position.x = getBody().getPosition().x / this.pixelsPerMeter;
        position.y = getBody().getPosition().y / this.pixelsPerMeter;

        return position;
    }

    public void syncBody() {

    }

    /**
     * Traduce unidades de World a Stage
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

}
