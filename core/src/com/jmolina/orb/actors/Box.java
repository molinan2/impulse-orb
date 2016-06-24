package com.jmolina.orb.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;
import com.jmolina.orb.screens.LevelBaseScreen;

public class Box extends Element {

    public Box(World world, float x, float y, float w, float h) {
        Shape shape = box(w, h);
        createFixture(shape);
        createBody(world, BodyDef.BodyType.StaticBody, x, y);

        Texture texture = new Texture(Gdx.files.internal("game/square.png"), true);
        float scaleX = w / (float) texture.getWidth() / LevelBaseScreen.RATIO_METER_PIXEL;
        float scaleY = h / (float) texture.getHeight() / LevelBaseScreen.RATIO_METER_PIXEL;

        createActor(texture, scaleX, scaleY);
    }

    private Shape box(float width, float height) {
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(0.5f * width, 0.5f * height);

        return shape;
    }

}
