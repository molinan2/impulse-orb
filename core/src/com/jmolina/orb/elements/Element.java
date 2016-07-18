package com.jmolina.orb.elements;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.jmolina.orb.assets.Asset;
import com.jmolina.orb.data.UserData;


public class Element {

    public enum Flavor { BLACK, GREY, RED, BLUE }
    public enum Effect { NONE, EXIT, DESTROY, HEAT, COOL, CUSTOM }
    public enum Geometry { CIRCLE, SQUARE }

    private final float DENSITY = 1.0f;
    private final float RESTITUTION = 0.6f;
    private final float FRICTION = 0.8f; // FRICTION = 0 evita rotaciones al colisionar

    private float pixelsPerMeter;
    private AssetManager assetManager;
    private BaseActor actor;
    private Body body;
    private float width, height;

    public Element(AssetManager am, World world, float x, float y, float w, float h, float pixelsPerMeter) {
        this(am, world, x, y, w, h, 0, Flavor.GREY, Geometry.SQUARE, BodyDef.BodyType.KinematicBody, pixelsPerMeter);
    }

    public Element(AssetManager am, World world, float x, float y, float w, float h, float angle, float pixelsPerMeter) {
        this(am, world, x, y, w, h, angle, Flavor.GREY, Geometry.SQUARE, BodyDef.BodyType.KinematicBody, pixelsPerMeter);
    }

    public Element(AssetManager am, World world, float x, float y, float w, float h, float angle, Flavor flavor, float pixelsPerMeter) {
        this(am, world, x, y, w, h, angle, flavor, Geometry.SQUARE, BodyDef.BodyType.KinematicBody, pixelsPerMeter);
    }

    public Element(AssetManager am, World world, float x, float y, float w, float h, float angle, Flavor flavor, Geometry geometry, float pixelsPerMeter) {
        this(am, world, x, y, w, h, angle, flavor, geometry, BodyDef.BodyType.KinematicBody, pixelsPerMeter);
    }

    /**
     * Constructor
     *
     * @param am AssetManager
     * @param world World
     * @param x Position x coord (World units)
     * @param y Position y coord (World units)
     * @param w Width of the element (World units)
     * @param h Heigth of the element (World units)
     * @param angle Rotation of the element in degrees counterclockwise
     * @param flavor Flavor
     * @param geometry Geometry
     * @param bodyType BodyDef.BodyType
     */
    public Element(AssetManager am, World world, float x, float y, float w, float h, float angle, Flavor flavor, Geometry geometry, BodyDef.BodyType bodyType, float pixelsPerMeter) {
        assetManager = am;
        this.pixelsPerMeter = pixelsPerMeter;
        width = w;
        height = h;

        FixtureDef fixtureDef = new FixtureDef();
        BodyDef bodyDef = new BodyDef();
        actor = new BaseActor();
        actor.setRotation(angle);

        fixtureDef.density = DENSITY;
        fixtureDef.restitution = RESTITUTION;
        fixtureDef.friction = FRICTION;
        fixtureDef.shape = createShape(geometry, width, height);
        bodyDef.type = bodyType;
        bodyDef.position.set(x, y);
        bodyDef.angle = angle * MathUtils.degreesToRadians;
        body = world.createBody(bodyDef);
        body.createFixture(fixtureDef);
        fixtureDef.shape.dispose();

        setUserData(flavor);
        setTexture(createTexture(geometry, flavor));
    }

    public synchronized <T> T getAsset (String fileName) {
        return this.assetManager.get(fileName);
    }

    public synchronized <T> T getAsset (String fileName, Class<T> type) {
        return this.assetManager.get(fileName, type);
    }

    public Actor getActor() {
        return actor;
    }

    public Body getBody() {
        return body;
    }

    public void setPosition(float x, float y) {
        getBody().setTransform(x, y, getBody().getAngle());
    }

    public Vector2 getPosition () {
        return new Vector2(
                getBody().getPosition().x,
                getBody().getPosition().y
        );
    }

    public void syncBody() {
        // Iguala la posicion
        // TODO

        // Iguala la rotacion
        if (actor != null && body != null) {
            body.setTransform(
                    body.getPosition().x,
                    body.getPosition().y,
                    MathUtils.degreesToRadians * actor.getRotation()
            );
        }
    }

    public void syncActor(Viewport viewport) {
        if (actor != null) {
            float offsetX = viewport.getWorldWidth() * 0.5f;
            float offsetY = viewport.getWorldHeight() * 0.5f;

            actor.setPosition(
                    getPixelsPerMeter() * (body.getPosition().x - (viewport.getCamera().position.x - offsetX)) - 0.5f * actor.getWidth(),
                    getPixelsPerMeter() * (body.getPosition().y - (viewport.getCamera().position.y - offsetY)) - 0.5f * actor.getHeight()
            );

            actor.setRotation(MathUtils.radiansToDegrees * body.getAngle());
        }
    }

    private Shape square(float width, float height) {
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(0.5f * width, 0.5f * height);

        return shape;
    }

    private Shape circle(float diameter) {
        CircleShape shape = new CircleShape();
        shape.setRadius(0.5f * diameter);

        return shape;
    }

    private Texture createTexture(Geometry geometry, Flavor flavor) {
        switch (geometry) {
            case CIRCLE: return circleTexture(flavor);
            case SQUARE: return squareTexture(flavor);
            default: return squareTexture(flavor);
        }
    }

    private Texture circleTexture(Flavor flavor) {
        switch (flavor) {
            case BLACK: return getAsset(Asset.GAME_CIRCLE_BLACK, Texture.class);
            case GREY: return getAsset(Asset.GAME_CIRCLE_GREY, Texture.class);
            case RED: return getAsset(Asset.GAME_CIRCLE_RED, Texture.class);
            default: return getAsset(Asset.GAME_CIRCLE_GREY, Texture.class);
        }
    }

    private Texture squareTexture(Flavor flavor) {
        switch (flavor) {
            case BLACK: return getAsset(Asset.GAME_SQUARE_BLACK, Texture.class);
            case GREY: return getAsset(Asset.GAME_SQUARE_GREY, Texture.class);
            case RED: return getAsset(Asset.GAME_SQUARE_RED, Texture.class);
            default: return getAsset(Asset.GAME_SQUARE_GREY, Texture.class);
        }
    }

    private Shape createShape (Geometry geometry, float width, float height) {
        switch (geometry) {
            case SQUARE: return square(width, height);
            case CIRCLE: return circle(width);
            default: return square(width, height);
        }
    }

    /**
     * Modifica la textura del actor y reajusta la escala del actor
     */
    public void setTexture(Texture texture) {
        float scaleX = getPixelsPerMeter() * width / texture.getWidth();
        float scaleY = getPixelsPerMeter() * height / texture.getHeight();

        actor.setTexture(texture);
        actor.setScale(scaleX, scaleY);
    }

    public void setUserData(Flavor flavor) {
        UserData userData = new UserData();

        if (flavor == Flavor.RED)
            userData.effect = Effect.DESTROY;
        else
            userData.effect = Effect.NONE;

        userData.flavor = flavor;
        body.getFixtureList().first().setUserData(userData);
    }

    public void setUserData(Flavor flavor, Effect effect) {
        UserData userData = new UserData();
        userData.flavor = flavor;
        userData.effect = effect;
        body.getFixtureList().first().setUserData(userData);
    }

    public void resetAngle() {
        actor.setRotation(0);
        body.setTransform(body.getPosition().x, body.getPosition().y, 0);
    }

    public void setAsSensor(boolean isSensor) {
        getBody().getFixtureList().first().setSensor(isSensor);
    }

    public float getPixelsPerMeter() {
        return pixelsPerMeter;
    }

}
