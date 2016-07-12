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
import com.badlogic.gdx.utils.viewport.Viewport;
import com.jmolina.orb.assets.Asset;
import com.jmolina.orb.screens.Level;


public class Element {

    public enum Type {BLACK, GREY, RED, BLUE}
    public enum Effect {NONE, EXIT, DESTROY, HEAT, COOL, CUSTOM}
    public enum Geometry {CIRCLE, SQUARE}

    private final float DENSITY = 1.0f;
    private final float RESTITUTION = 0.6f;
    private final float FRICTION = 0.8f; // friction=0 evita rotaciones al chocar

    private float pixelsPerMeter;
    private AssetManager assetManager;
    private BaseActor actor;
    private Body body;
    private float width, height;

    public Element(AssetManager am, World world, float x, float y, float w, float h, float ratioMeterPixel) {
        this(am, world, x, y, w, h, 0, Type.GREY, Geometry.SQUARE, BodyDef.BodyType.KinematicBody, ratioMeterPixel);
    }

    public Element(AssetManager am, World world, float x, float y, float w, float h, float angle, float ratioMeterPixel) {
        this(am, world, x, y, w, h, angle, Type.GREY, Geometry.SQUARE, BodyDef.BodyType.KinematicBody, ratioMeterPixel);
    }

    public Element(AssetManager am, World world, float x, float y, float w, float h, float angle, Type type, float ratioMeterPixel) {
        this(am, world, x, y, w, h, angle, type, Geometry.SQUARE, BodyDef.BodyType.KinematicBody, ratioMeterPixel);
    }

    public Element(AssetManager am, World world, float x, float y, float w, float h, float angle, Type type, Geometry geometry, float ratioMeterPixel) {
        this(am, world, x, y, w, h, angle, type, geometry, BodyDef.BodyType.KinematicBody, ratioMeterPixel);
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
     * @param type Type
     * @param geometry Geometry
     * @param bodyType BodyDef.BodyType
     */
    public Element(AssetManager am, World world, float x, float y, float w, float h, float angle, Type type, Geometry geometry, BodyDef.BodyType bodyType, float ratioMeterPixel) {
        assetManager = am;
        pixelsPerMeter = 1 / ratioMeterPixel;
        width = w;
        height = h;

        FixtureDef fixtureDef = new FixtureDef();
        BodyDef bodyDef = new BodyDef();
        actor = new BaseActor();

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

        setUserData(type);

        Texture texture = createTexture(geometry, type);
        setActorTexture(texture);
        actor.setRotation(angle);
    }

    public synchronized <T> T getAsset (String fileName) {
        return this.assetManager.get(fileName);
    }

    public synchronized <T> T getAsset (String fileName, Class<T> type) {
        return this.assetManager.get(fileName, type);
    }

    public BaseActor getActor() {
        return actor;
    }

    public Body getBody() {
        return body;
    }

    /**
     * Grid position
     */
    public void setPosition(float x, float y) {
        getBody().setTransform(x, y, getBody().getAngle()
        );
    }

    public Vector2 getPosition () {
        Vector2 position = new Vector2();

        position.x = getBody().getPosition().x;
        position.y = getBody().getPosition().y;

        return position;
    }

    public void syncBody() {
        // TODO
        // Iguala la posicion

        // Iguala solo la rotacion
        // TODO Esto generara un error de Java al hacer dispose()
        if (actor != null && body != null) {
            body.setTransform(body.getPosition().x, body.getPosition().y, MathUtils.degreesToRadians * actor.getRotation());
        }
    }

    public void syncActor(Viewport viewport, float worldWidth, float worldHeight, float pixelsPerMeter) {
        if (actor != null) {
            float offsetX = worldWidth * 0.5f;
            float offsetY = worldHeight * 0.5f;

            actor.setPosition(
                    pixelsPerMeter * (body.getPosition().x - (viewport.getCamera().position.x - offsetX)) - 0.5f * actor.getWidth(),
                    pixelsPerMeter * (body.getPosition().y - (viewport.getCamera().position.y - offsetY)) - 0.5f * actor.getHeight()
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

    /**
     * TODO
     *
     * Es confuso que no tengan correlacion los tipos con las texturas que se cargan
     * La textura deberia ser un parametro. Si no se pasa textura, se utiliza este metodo
     */
    private Texture createTexture (Geometry geometry, Type type) {
        /*
        if (type == Type.BLUE) {
            return getAsset(Asset.GAME_EXIT, Texture.class);
        }
        */

        if (geometry == Geometry.CIRCLE) {
            switch (type) {
                case BLACK: return getAsset(Asset.GAME_CIRCLE_BLACK, Texture.class);
                case GREY: return getAsset(Asset.GAME_CIRCLE_GREY, Texture.class);
                case RED: return getAsset(Asset.GAME_CIRCLE_RED, Texture.class);
            }
        }
        else if (geometry == Geometry.SQUARE) {
            switch (type) {
                case BLACK: return getAsset(Asset.GAME_SQUARE_BLACK, Texture.class);
                case GREY: return getAsset(Asset.GAME_SQUARE_GREY, Texture.class);
                case RED: return getAsset(Asset.GAME_SQUARE_RED, Texture.class);
            }
        }

        return getAsset(Asset.GAME_SQUARE_GREY, Texture.class); // No deberia ocurrir
    }

    private Shape createShape (Geometry geometry, float width, float height) {
        switch (geometry) {
            case SQUARE: return square(width, height);
            case CIRCLE: return circle(width);
        }

        return square(width, height); // No deberia ocurrir
    }

    /**
     * Modifica la textura del actor y reajusta la escala del actor
     */
    public void setActorTexture (Texture texture) {
        float scaleX = pixelsPerMeter * width / texture.getWidth();
        float scaleY = pixelsPerMeter * height / texture.getHeight();

        actor.setTexture(texture);
        actor.setScale(scaleX, scaleY);
    }

    public void setUserData(Type type) {
        com.jmolina.orb.data.UserData userData = new com.jmolina.orb.data.UserData();

        if (type == Type.RED)
            userData.effect = Effect.DESTROY;
        else
            userData.effect = Effect.NONE;

        userData.type = type;
        body.getFixtureList().first().setUserData(userData);
    }

    public void setUserData(Type type, Effect effect) {
        com.jmolina.orb.data.UserData userData = new com.jmolina.orb.data.UserData();
        userData.type = type;
        userData.effect = effect;
        body.getFixtureList().first().setUserData(userData);
    }

    public void resetAngle() {
        actor.setRotation(0);
        body.setTransform(body.getPosition().x, body.getPosition().y, 0);
    }

}
