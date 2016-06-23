package com.jmolina.orb.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;

public class Ball extends Element {

    // TODO no tiene sentido pasar el radio, si la textura es fija

    public Ball(World world, float x, float y, float radius) {
        super();

        createFixture(circle(0.25f));
        createBody(world, BodyDef.BodyType.DynamicBody, x, y);
        createActor(new Texture(Gdx.files.internal("ball.png"), true));
    }

    public Shape circle(float radius) {
        CircleShape shape = new CircleShape();
        shape.setRadius(radius);

        return shape;
    }

}
