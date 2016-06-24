package com.jmolina.orb.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.viewport.Viewport;


public class Element {

    private BaseActor actor;
    private Body body;
    private BodyDef bodyDef;
    private FixtureDef fixtureDef;
    private Fixture fixture;

    public void createFixture(Shape shape) {
        // friction=0 provoca que las esferas no roten al chocar
        createFixture(shape, 1, 1, 0.5f);
    }

    public void createFixture(Shape shape, float density, float restitution, float friction) {
        fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = density;
        fixtureDef.restitution = restitution;
        fixtureDef.friction = friction;
    }

    public void createBody (World world, BodyDef.BodyType type, float x, float y) {
        bodyDef = new BodyDef();
        bodyDef.type = type;
        bodyDef.position.set(x, y);
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
        actor = new BaseActor(texture, scaleX, scaleY);
    }

    public Actor getActor() {
        return actor;
    }

    public Body getBody() {
        return body;
    }

    public void setPosition(float x, float y) {
        body.getPosition().set(x, y);
    }

    public void syncBody() {

    }

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

            System.out.println("Body: " + body.getAngle());
            // System.out.println("Actor: " + actor.getRotation());
        }
    }

}
