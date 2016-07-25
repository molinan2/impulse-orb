package com.jmolina.orb.elements;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.jmolina.orb.var.Asset;
import com.jmolina.orb.var.Var;


/**
 * Un Elemento incluye un cuerpo físico ({@link com.badlogic.gdx.physics.box2d.Body}) situado en el
 * mundo de Box2D y asociado a un {@link Actor} situado en Scene2D.
 */
public class Element extends WorldElement {

    private AssetManager assetManager;
    private Actor actor;
    private float pixelsPerMeter;

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
     * @param type BodyDef.BodyType
     */
    public Element(AssetManager am, World world, float x, float y, float w, float h, float angle, Flavor flavor, Geometry geometry, BodyDef.BodyType type, float pixelsPerMeter) {
        super(world, x, y, w, h, angle, flavor, geometry, type);

        assetManager = am;
        this.pixelsPerMeter = pixelsPerMeter;

        actor = new BaseActor();
        setTexture(createTexture(geometry, flavor), w, h);
    }

    private AssetManager getAssetManager() {
        return assetManager;
    }

    public Actor getActor() {
        return actor;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

    public void syncBody(Viewport viewport) {
        syncBody(viewport, true, true);
    }

    /**
     * Sincroniza la posición y rotación del cuerpo físico con las del actor
     *
     * @param viewport Viewport del mundo físico
     */
    public void syncBody(Viewport viewport, boolean syncPosition, boolean syncAngle) {
        if (actor == null || getBody() == null) return;

        if (syncPosition) syncBodyPosition(viewport);
        if (syncAngle) syncBodyAngle();
    }

    private void syncBodyPosition(Viewport viewport) {
        float actorPositionX = actor.getX() + actor.getOriginX();
        float actorPositionY = actor.getY() + actor.getOriginY();
        float cameraX = viewport.getCamera().position.x;
        float cameraY = viewport.getCamera().position.y;
        float offsetX = 0.5f * Var.SCREEN_WIDTH;
        float offsetY = 0.5f * Var.SCREEN_HEIGHT;

        float bodyPositionX = (actorPositionX - offsetX) / getPixelsPerMeter() + cameraX;
        float bodyPositionY = (actorPositionY - offsetY) / getPixelsPerMeter() + cameraY;

        getBody().setTransform(
                bodyPositionX,
                bodyPositionY,
                getBody().getAngle()
        );
    }

    private void syncBodyAngle() {
        float bodyAngle = MathUtils.degreesToRadians * actor.getRotation();

        getBody().setTransform(
                getBody().getPosition().x,
                getBody().getPosition().y,
                bodyAngle
        );
    }

    /**
     * Sincroniza la posición y rotación del actor con las del cuerpo físico
     *
     * @param viewport Viewport del mundo físico
     */
    public void syncActor(Viewport viewport) {
        if (actor == null) return;

        syncActorPosition(viewport);
        syncActorRotation();
    }

    private void syncActorPosition(Viewport viewport) {
        float cameraX = viewport.getCamera().position.x;
        float cameraY = viewport.getCamera().position.y;
        float offsetX = viewport.getWorldWidth() * 0.5f;
        float offsetY = viewport.getWorldHeight() * 0.5f;
        float bodyPositionX = getBody().getPosition().x;
        float bodyPositionY = getBody().getPosition().y;
        float actorWidth = actor.getWidth();
        float actorHeight = actor.getHeight();

        float actorPositionX = getPixelsPerMeter() * (bodyPositionX - cameraX + offsetX) - 0.5f * actorWidth;
        float actorPositionY = getPixelsPerMeter() * (bodyPositionY - cameraY + offsetY) - 0.5f * actorHeight;

        actor.setPosition(
                actorPositionX,
                actorPositionY
        );
    }

    private void syncActorRotation() {
        float actorRotation = MathUtils.radiansToDegrees * getBody().getAngle();

        actor.setRotation(actorRotation);
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
            case BLACK: return getAssetManager().get(Asset.GAME_CIRCLE_BLACK, Texture.class);
            case GREY: return getAssetManager().get(Asset.GAME_CIRCLE_GREY, Texture.class);
            case RED: return getAssetManager().get(Asset.GAME_CIRCLE_RED, Texture.class);
            default: return getAssetManager().get(Asset.GAME_CIRCLE_GREY, Texture.class);
        }
    }

    private Texture squareTexture(Flavor flavor) {
        switch (flavor) {
            case BLACK: return getAssetManager().get(Asset.GAME_SQUARE_BLACK, Texture.class);
            case GREY: return getAssetManager().get(Asset.GAME_SQUARE_GREY, Texture.class);
            case RED: return getAssetManager().get(Asset.GAME_SQUARE_RED, Texture.class);
            default: return getAssetManager().get(Asset.GAME_SQUARE_GREY, Texture.class);
        }
    }

    private void setTexture(Texture texture, float width, float height) {
        float scaleX = getPixelsPerMeter() * width / texture.getWidth();
        float scaleY = getPixelsPerMeter() * height / texture.getHeight();

        ((BaseActor)actor).setTexture(texture);
        actor.setScale(scaleX, scaleY);
    }

    /**
     * Modifica la textura del actor y reajusta la escala del actor
     */
    public void updateTexture(Texture texture, float width, float height) {
        if (actor instanceof BaseActor) {
            setTexture(texture, width, height);
        }
    }

    public float getPixelsPerMeter() {
        return pixelsPerMeter;
    }

}
