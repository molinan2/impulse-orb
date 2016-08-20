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
     * Forma geométrica del elemento
     *
     * CIRCLE: Círculo
     * SQUARE: Rectángulo
     * TRIANGLE: Triángulo equilátero
     */
    public enum Geometry { CIRCLE, SQUARE, TRIANGLE }

    /**
     * Tipo de elemento
     *
     * BLACK: Elementos estáticos (muros y escenarios estáticos)
     * GREEN: Elementos dinámicos (sólo el {@link Orb})
     * GREY: Elementos kinéticos genéricos
     * RED: Elementos kinéticos destructivos
     * BLUE: Elementos kinéticos etéreos visibles
     * VIOLET: Elementos kinéticos magnéticos
     * AIR: Elementos kinéticos etéreos invisibles
     */
    public enum Flavor { BLACK, GREEN, GREY, RED, BLUE, VIOLET, AIR }

    /**
     * Efecto del elemento al contacto con el Orb.
     *
     * NONE: Sin efecto
     * EXIT: Provoca la salida del nivel
     * DESTROY: Provoca la destrucción del orbe
     * HEAT: Provoca el calentamiento del orbe
     */
    public enum Effect { NONE, EXIT, DESTROY, HEAT }

    private final float DENSITY = 1.0f;
    private final float RESTITUTION = 0.6f;
    private final float FRICTION = 0.8f; // FRICTION = 0 evita rotaciones al colisionar

    private float width, height;
    private float initialX, initialY, initialAngle;
    private Body body;
    private UserData userData;
    private Geometry geometry;
    private Flavor flavor;

    /**
     * Constructor
     *  @param world World
     * @param w Width of the element (World units)
     * @param h Heigth of the element (World units)
     * @param x Position x coord (World units)
     * @param y Position y coord (World units)
     * @param angle Rotation of the element in degrees counterclockwise
     * @param geometry Geometry
     * @param flavor Flavor
     */
    public WorldElement(World world, float w, float h, float x, float y, float angle, Geometry geometry, Flavor flavor) {
        userData = new UserData();
        FixtureDef fixtureDef = new FixtureDef();
        BodyDef bodyDef = new BodyDef();

        width = w;
        height = h;
        setUserData(flavor);
        this.geometry = geometry;
        this.flavor = flavor;

        fixtureDef.density = DENSITY;
        fixtureDef.restitution = RESTITUTION;
        fixtureDef.friction = FRICTION;
        fixtureDef.shape = shape(geometry);
        bodyDef.type = type(flavor);
        bodyDef.position.set(x, y);
        bodyDef.angle = angle * MathUtils.degreesToRadians;
        body = world.createBody(bodyDef);
        body.createFixture(fixtureDef);
        body.setUserData(getUserData());
        fixtureDef.shape.dispose();

        if (flavor == Flavor.AIR) setAsSensor(true);
    }

    private BodyDef.BodyType type(Flavor flavor) {
        switch (flavor) {
            case BLACK: return BodyDef.BodyType.StaticBody;
            case GREEN: return BodyDef.BodyType.DynamicBody;
            case GREY: return BodyDef.BodyType.KinematicBody;
            default: return BodyDef.BodyType.KinematicBody;
        }
    }

    private Shape shape(Geometry geometry) {
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

    protected void setEffect(Effect effect) {
        userData.effect = effect;
    }

    protected UserData getUserData() {
        return userData;
    }

    protected void setAsSensor(boolean sensor) {
        getBody().getFixtureList().first().setSensor(sensor);
    }

    public Body getBody() {
        return body;
    }

    public void setPosition(float x, float y) {
        setInitialX(x);
        setInitialY(y);
        getBody().setTransform(x, y, getBody().getAngle());
    }

    public Vector2 getPosition() {
        return new Vector2(
                getBody().getPosition().x,
                getBody().getPosition().y
        );
    }

    public float getRotation() {
        return MathUtils.radiansToDegrees * getBody().getAngle();
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

    public Flavor getFlavor() {
        return flavor;
    }

    /**
     * Valores iniciales. Éstos pueden variar cuando el elemento se añade a una situación, que tiene
     * una altura base que se sumará a la del elemento. Los valores iniciales se deben restaurar al
     * resetear el elemento (por ejemplo, si es variante).
     *
     * @param x
     */
    public void setInitialX(float x) {
        initialX = x;
    }

    public float getInitialX() {
        return initialX;
    }

    public void setInitialY(float y) {
        initialY = y;
    }

    public float getInitialY() {
        return initialY;
    }

    /**
     * Angulo inicial en grados. Es importante si el objeto es variante, ya que debe resetearse y
     * recuperar su angulo inicial. Si no es variante, nunca se resetea.
     *
     * @param angle Angulo en grados.
     */
    public void setInitialAngle(float angle) {
        initialAngle = angle;
    }

    public float getInitialAngle() {
        return initialAngle;
    }

}
