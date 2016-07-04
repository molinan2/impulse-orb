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
import com.jmolina.orb.screens.LevelScreen;


public class Element {

    public enum Behaviour {BLACK, GREY, RED}
    public enum Geometry {CIRCLE, SQUARE}

    private final float DENSITY = 1.0f;
    private final float RESTITUTION = 0.6f;
    private final float FRICTION = 0.8f; // friction=0 evita rotaciones al chocar
    private final float PIXELS_PER_METER = LevelScreen.PIXELS_PER_METER;

    private AssetManager assetManager;
    private BaseActor actor;
    private Body body;

    /**
     * @param am AssetManager
     * @param world World
     * @param x Position x coord
     * @param y Position y coord
     * @param width Width of the element
     * @param height Heigth of the element
     * @param rotation Rotation of the element in degrees counterclockwise
     * @param behaviour Behaviour
     * @param geometry Geometry
     */
    public Element(AssetManager am, World world, float x, float y, float width, float height, float rotation, Behaviour behaviour, Geometry geometry) {
        assetManager = am;

        FixtureDef fixtureDef = new FixtureDef();
        BodyDef bodyDef = new BodyDef();
        actor = new BaseActor();
        float scaleX, scaleY;

        fixtureDef.density = DENSITY;
        fixtureDef.restitution = RESTITUTION;
        fixtureDef.friction = FRICTION;
        fixtureDef.shape = createShape(geometry, width, height);
        bodyDef.type = BodyDef.BodyType.KinematicBody;
        bodyDef.position.set(x, y);
        bodyDef.angle = rotation * MathUtils.degreesToRadians;

        body = world.createBody(bodyDef);
        body.createFixture(fixtureDef);
        fixtureDef.shape.dispose();

        Texture texture = createTexture(geometry, behaviour);
        scaleX = PIXELS_PER_METER * width / texture.getWidth();
        scaleY = PIXELS_PER_METER * height / texture.getHeight();

        actor.setTexture(texture);
        actor.setScale(scaleX, scaleY);
        actor.setRotation(rotation);
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

    }

    /**
     * Traduce unidades de World a Stage
     */
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

    private Texture createTexture (Geometry geometry, Behaviour behaviour) {
        if (geometry == Geometry.CIRCLE) {
            switch (behaviour) {
                case BLACK: return getAsset(Asset.GAME_CIRCLE_BLACK, Texture.class);
                case GREY: return getAsset(Asset.GAME_CIRCLE_GREY, Texture.class);
                case RED: return getAsset(Asset.GAME_CIRCLE_RED, Texture.class);
            }
        }
        else if (geometry == Geometry.SQUARE) {
            switch (behaviour) {
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

}
