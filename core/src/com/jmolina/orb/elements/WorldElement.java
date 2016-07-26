package com.jmolina.orb.elements;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;
import com.jmolina.orb.data.UserData;


public class WorldElement {

    public enum Flavor { BLACK, GREY, RED, BLUE }
    public enum Effect { NONE, EXIT, DESTROY, HEAT, COOL, CUSTOM }
    public enum Geometry { CIRCLE, SQUARE }

    private final float DENSITY = 1.0f;
    private final float RESTITUTION = 0.6f;
    private final float FRICTION = 0.8f; // FRICTION = 0 evita rotaciones al colisionar

    private float width, height;
    private Body body;
    private UserData userData;

    /**
     * Constructor
     *
     * @param world World
     * @param x Position x coord (World units)
     * @param y Position y coord (World units)
     * @param w Width of the element (World units)
     * @param h Heigth of the element (World units)
     * @param angle Rotation of the element in degrees counterclockwise
     * @param flavor Flavor
     * @param geometry Geometry
     * @param type BodyDef.BodyType
     */
    public WorldElement(World world, float x, float y, float w, float h, float angle, Flavor flavor, Geometry geometry, BodyDef.BodyType type) {
        userData = new UserData();
        FixtureDef fixtureDef = new FixtureDef();
        BodyDef bodyDef = new BodyDef();

        width = w;
        height = h;
        setUserData(flavor);

        fixtureDef.density = DENSITY;
        fixtureDef.restitution = RESTITUTION;
        fixtureDef.friction = FRICTION;
        fixtureDef.shape = createShape(geometry);
        bodyDef.type = type;
        bodyDef.position.set(x, y);
        bodyDef.angle = angle * MathUtils.degreesToRadians;
        body = world.createBody(bodyDef);
        body.createFixture(fixtureDef);
        body.getFixtureList().first().setUserData(getUserData());
        fixtureDef.shape.dispose();

    }

    private Shape createShape (Geometry geometry) {
        switch (geometry) {
            case SQUARE: return square();
            case CIRCLE: return circle();
            default: return square();
        }
    }

    private Shape square() {
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(0.5f * width, 0.5f * height);

        return shape;
    }

    private Shape circle() {
        CircleShape shape = new CircleShape();
        shape.setRadius(0.5f * width);

        return shape;
    }

    private void setUserData(Flavor flavor) {
        Effect effect;

        switch (flavor) {
            case RED: effect = Effect.DESTROY; break;
            default: effect = Effect.NONE;
        }

        userData.flavor = flavor;
        userData.effect = effect;
    }

    public void setEffect(Effect effect) {
        userData.effect = effect;
    }

    public UserData getUserData() {
        return userData;
    }

    public void setAsSensor(boolean sensor) {
        getBody().getFixtureList().first().setSensor(sensor);
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

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

}
