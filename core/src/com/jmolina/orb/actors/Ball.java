package com.jmolina.orb.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;
import com.jmolina.orb.screens.LevelBaseScreen;

public class Ball extends Element {

    public Ball(World world, float x, float y, float diameter) {
        Shape shape = circle(diameter);
        createFixture(shape);
        createBody(world, BodyDef.BodyType.DynamicBody, x, y);

        Texture texture = new Texture(Gdx.files.internal("game/ball.png"), true);
        float scale = diameter / (float) texture.getWidth() / LevelBaseScreen.RATIO_METER_PIXEL;

        createActor(texture, scale);
    }

    private Shape circle(float diameter) {
        CircleShape shape = new CircleShape();
        shape.setRadius(0.5f * diameter);

        return shape;
    }

}
