package com.jmolina.orb.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
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

    public Element() {
        bodyDef = new BodyDef();
        fixtureDef = new FixtureDef();
    }

    public void createFixture(Shape shape) {
        fixtureDef.shape = shape;
        fixtureDef.density = 0.5f;
        fixtureDef.restitution = 0.8f;
        fixtureDef.friction = 0.4f;
    }

    public void createBody (World world, BodyDef.BodyType type, float x, float y) {
        bodyDef.type = type;
        bodyDef.position.set(x, y);
        body = world.createBody(bodyDef);
        body.createFixture(fixtureDef);
    }

    public void createActor(Texture texture) {
        actor = new BaseActor(texture);
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
        }
    }

}
