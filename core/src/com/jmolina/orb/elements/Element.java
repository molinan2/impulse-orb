package com.jmolina.orb.elements;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.jmolina.orb.managers.AssetManager;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.jmolina.orb.actors.BaseActor;
import com.jmolina.orb.var.Atlas;
import com.jmolina.orb.var.Var;


/**
 * Unión de WorldElement y SceneElement (hipotética, porque Java no admite herencia múltiple).
 *
 * Un Element incluye un cuerpo físico ({@link com.badlogic.gdx.physics.box2d.Body}) situado en el
 * mundo de Box2D, asociado a un {@link Actor} situado en la Scene2D. El cuerpo Body pertenece a la
 * "capa de simulación" y el Actor a la "capa de visualización".
 */
public class Element extends WorldElement {

    private AssetManager assetManager;
    private Actor actor;
    private float pixelsPerMeter;

    /**
     * Constructor simplificado. La textura se elige por defecto
     */
    public Element(AssetManager am, World world, float pixelsPerMeter, Geometry geometry, Flavor flavor, float w, float h, float x, float y, float angle) {
        super(world, w, h, x, y, angle, geometry, flavor);

        this.assetManager = am;
        this.pixelsPerMeter = pixelsPerMeter;
        this.actor = new BaseActor();
        setTextureRegion(defaultTexture(geometry, flavor));
    }

    /**
     * Constructor completo. La textura se le suministra al constructor
     * @param am AssetManager
     * @param world Box2D World which the Element's body will be added to
     * @param region Visible Element texture
     * @param geometry {@link Geometry}
     * @param flavor {@link Flavor}
     * @param w Width of the element (World units)
     * @param h Heigth of the element (World units)
     * @param x Position x coord (World units)
     * @param y Position y coord (World units)
     * @param angle Initial angle of the Element in degrees counterclockwise
     */
    public Element(AssetManager am, World world, TextureRegion region, float pixelsPerMeter, Geometry geometry, Flavor flavor, float w, float h, float x, float y, float angle) {
        super(world, w, h, x, y, angle, geometry, flavor);

        this.assetManager = am;
        this.pixelsPerMeter = pixelsPerMeter;
        this.actor = new BaseActor();
        setTextureRegion(region);
    }

    /**
     * Gets the AssetManager
     */
    private AssetManager getAssetManager() {
        return assetManager;
    }

    /**
     * Gets the current actor
     */
    public Actor getActor() {
        return actor;
    }

    /**
     * Replaces the current actor. Used only by {@link Orb}
     */
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
     * @param position A 'true' si hay que sincronizar la posición
     * @param angle A 'true' si hay que sincronizar el ángulo/rotación
     */
    public void syncBody(Viewport viewport, boolean position, boolean angle) {
        if (actor == null || getBody() == null) return;

        if (position) syncBodyPosition(viewport);
        if (angle) syncBodyAngle();
    }

    private void syncBodyPosition(Viewport viewport) {
        float actorPositionX = actor.getX() + actor.getOriginX();
        float actorPositionY = actor.getY() + actor.getOriginY();
        float cameraX = viewport.getCamera().position.x;
        float cameraY = viewport.getCamera().position.y;
        float offsetX = 0.5f * Var.SCREEN_WIDTH;
        float offsetY = 0.5f * Var.SCREEN_HEIGHT;

        float bodyPositionX = (actorPositionX - offsetX) / getPPM() + cameraX;
        float bodyPositionY = (actorPositionY - offsetY) / getPPM() + cameraY;

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

        float actorPositionX = getPPM() * (bodyPositionX - cameraX + offsetX) - 0.5f * actorWidth;
        float actorPositionY = getPPM() * (bodyPositionY - cameraY + offsetY) - 0.5f * actorHeight;

        actor.setPosition(
                actorPositionX,
                actorPositionY
        );
    }

    private void syncActorRotation() {
        float actorRotation = MathUtils.radiansToDegrees * getBody().getAngle();
        actor.setRotation(actorRotation);
    }

    private TextureRegion defaultTexture(Geometry geometry, Flavor flavor) {
        switch (geometry) {
            case CIRCLE: return circleTexture(flavor);
            case SQUARE: return squareTexture(flavor);
            case TRIANGLE: return triangleTexture(flavor);
            default: return squareTexture(flavor);
        }
    }

    private TextureRegion circleTexture(Flavor flavor) {
        switch (flavor) {
            case BLACK: return findRegion(Atlas.GAME_CIRCLE_BLACK);
            case GREY: return findRegion(Atlas.GAME_CIRCLE_GREY);
            case RED: return findRegion(Atlas.GAME_CIRCLE_RED);
            case VIOLET: return findRegion(Atlas.GAME_CIRCLE_VIOLET);
            case TRANSPARENT: return findRegion(Atlas.GAME_CIRCLE_TRANSPARENT);
            default: return findRegion(Atlas.GAME_CIRCLE_GREY);
        }
    }

    private TextureRegion squareTexture(Flavor flavor) {
        switch (flavor) {
            case BLACK: return findRegion(Atlas.GAME_SQUARE_BLACK);
            case GREY: return findRegion(Atlas.GAME_SQUARE_GREY);
            case RED: return findRegion(Atlas.GAME_SQUARE_RED);
            default: return findRegion(Atlas.GAME_SQUARE_GREY);
        }
    }

    private TextureRegion triangleTexture(Flavor flavor) {
        switch (flavor) {
            case GREY: return findRegion(Atlas.GAME_TRIANGLE_GREY);
            case RED: return findRegion(Atlas.GAME_TRIANGLE_RED);
            default: return findRegion(Atlas.GAME_TRIANGLE_GREY);
        }
    }

    private void setTextureRegion(TextureRegion region) {
        float scaleX = getPPM() * getWidth() / region.getRegionWidth();
        float scaleY = getPPM() * getHeight() / region.getRegionHeight();

        ((BaseActor)actor).setTextureRegion(region);
        actor.setScale(scaleX, scaleY);

        // Corrección específica para la textura del triángulo, que es distinta para que el origen
        // del actor coincida con el centroide del triángulo equilátero
        if (getGeometry() == Geometry.TRIANGLE) {
            float correction = 400f / 344f;
            actor.setScale(
                    actor.getScaleX() * correction,
                    actor.getScaleY() * correction
            );
        }
    }

    public float getPPM() {
        return pixelsPerMeter;
    }

    public void dispose() {
        World world = getBody().getWorld();
        world.destroyBody(getBody());
        getActor().remove();

        assetManager = null;
        actor = null;
    }

    protected TextureRegion findRegion(String name) {
        return getAssetManager().getGameAtlas().findRegion(name);
    }

}
