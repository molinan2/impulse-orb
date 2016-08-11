package com.jmolina.orb.elements;

import com.jmolina.orb.managers.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.jmolina.orb.actors.BaseActor;
import com.jmolina.orb.var.Asset;
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
        setTexture(defaultTexture(geometry, flavor));
    }

    /**
     * Constructor completo. La textura se le suministra al constructor
     * @param am AssetManager
     * @param world Box2D World which the Element's body will be added to
     * @param texture Visible Element texture
     * @param geometry {@link Geometry}
     * @param flavor {@link Flavor}
     * @param w Width of the element (World units)
     * @param h Heigth of the element (World units)
     * @param x Position x coord (World units)
     * @param y Position y coord (World units)
     * @param angle Initial angle of the Element in degrees counterclockwise
     */
    public Element(AssetManager am, World world, Texture texture, float pixelsPerMeter, Geometry geometry, Flavor flavor, float w, float h, float x, float y, float angle) {
        super(world, w, h, x, y, angle, geometry, flavor);

        this.assetManager = am;
        this.pixelsPerMeter = pixelsPerMeter;
        this.actor = new BaseActor();
        setTexture(texture);
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

    private Texture defaultTexture(Geometry geometry, Flavor flavor) {
        switch (geometry) {
            case CIRCLE: return circleTexture(flavor);
            case SQUARE: return squareTexture(flavor);
            case TRIANGLE: return triangleTexture(flavor);
            default: return squareTexture(flavor);
        }
    }

    private Texture circleTexture(Flavor flavor) {
        switch (flavor) {
            case BLACK: return getAssetManager().get(Asset.GAME_CIRCLE_BLACK, Texture.class);
            case GREY: return getAssetManager().get(Asset.GAME_CIRCLE_GREY, Texture.class);
            case RED: return getAssetManager().get(Asset.GAME_CIRCLE_RED, Texture.class);
            case VIOLET: return getAssetManager().get(Asset.GAME_CIRCLE_VIOLET, Texture.class);
            case TRANSPARENT: return getAssetManager().get(Asset.GAME_CIRCLE_TRANSPARENT, Texture.class);
            default: return getAssetManager().get(Asset.GAME_CIRCLE_GREY, Texture.class);
        }
    }

    private Texture squareTexture(Flavor flavor) {
        switch (flavor) {
            case BLACK:
                Texture black = getAssetManager().get(Asset.GAME_SQUARE_BLACK, Texture.class);
                black.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
                return black;
            case GREY: return getAssetManager().get(Asset.GAME_SQUARE_GREY, Texture.class);
            case RED: return getAssetManager().get(Asset.GAME_SQUARE_RED, Texture.class);
            default: return getAssetManager().get(Asset.GAME_SQUARE_GREY, Texture.class);
        }
    }

    private Texture triangleTexture(Flavor flavor) {
        switch (flavor) {
            case GREY: return getAssetManager().get(Asset.GAME_TRIANGLE_GREY, Texture.class);
            case RED: return getAssetManager().get(Asset.GAME_TRIANGLE_RED, Texture.class);
            default: return getAssetManager().get(Asset.GAME_TRIANGLE_GREY, Texture.class);
        }
    }

    private void setTexture(Texture texture) {
        float scaleX = getPPM() * getWidth() / texture.getWidth();
        float scaleY = getPPM() * getHeight() / texture.getHeight();

        ((BaseActor)actor).setTexture(texture);
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

}
