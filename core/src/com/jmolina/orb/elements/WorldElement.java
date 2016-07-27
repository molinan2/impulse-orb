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

    /**
     * Flavor
     *
     * BLACK: Elementos estáticos (muros y escenarios estáticos)
     * GREY: Elementos kinéticos
     * RED: Elementos destructivos
     * BLUE: Elementos etéreos
     */
    public enum Flavor { BLACK, GREY, RED, BLUE }

    /**
     * Effect
     *
     * NONE: Sin efecto
     * EXIT: Provoca la salida del nivel
     * DESTROY: Provoca la destrucción del orbe
     * HEAT: Provoca el calentamiento del orbe
     */
    public enum Effect { NONE, EXIT, DESTROY, HEAT, CUSTOM }

    /**
     * Geometry
     *
     * CIRCLE: Elemento elíptico
     * SQUARE: Elemento rectangular
     * TRIANGLE: Elemento triangular equilátero
     */
    public enum Geometry { CIRCLE, SQUARE, TRIANGLE }

    private final float DENSITY = 1.0f;
    private final float RESTITUTION = 0.6f;
    private final float FRICTION = 0.8f; // FRICTION = 0 evita rotaciones al colisionar

    private float width, height;
    private Body body;
    private UserData userData;
    private Geometry geometry;

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
        this.geometry = geometry;

        fixtureDef.density = DENSITY;
        fixtureDef.restitution = RESTITUTION;
        fixtureDef.friction = FRICTION;
        fixtureDef.shape = createShape(geometry);
        bodyDef.type = type;
        bodyDef.position.set(x, y);
        bodyDef.angle = angle * MathUtils.degreesToRadians;
        body = world.createBody(bodyDef);
        body.createFixture(fixtureDef);
        body.setUserData(getUserData());
        fixtureDef.shape.dispose();
    }

    private Shape createShape (Geometry geometry) {
        switch (geometry) {
            case SQUARE: return square();
            case CIRCLE: return circle();
            case TRIANGLE: return triangle();
            default: return square();
        }
    }

    /**
     * Crea un rectángulo. El punto de origen es su centroide.
     */
    private Shape square() {
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(0.5f * width, 0.5f * height);

        return shape;
    }

    /**
     * Crea un círculo de radio = {@link #width}. El parámetro {@link #height} se ignora.
     * El punto de origen es su centro.
     */
    private Shape circle() {
        CircleShape shape = new CircleShape();
        shape.setRadius(0.5f * width);

        return shape;
    }

    /**
     * Crea un triángulo equilátero de lado {@link #width}. El parámetro {@link #height} se ignora.
     * El punto de origen es su centroide (punto de cruce de las 3 alturas).
     */
    private Shape triangle() {
        Vector2[] points = new Vector2[3];
        points[0] = new Vector2(-0.5f * width, - width * MathUtils.sinDeg(60) / 3);
        points[1] = new Vector2(0.5f * width, - width * MathUtils.sinDeg(60) / 3);
        points[2] = new Vector2(0, width * MathUtils.sinDeg(60) / 3 * 2);

        PolygonShape shape = new PolygonShape();
        shape.set(points);

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

    public Geometry getGeometry() {
        return this.geometry;
    }

}
