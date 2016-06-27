package com.jmolina.orb.elements;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;
import com.jmolina.orb.screens.LevelBaseScreen;

public class Ball extends Element {

    static public class BallConfig extends ElementConfig {
        public float diameter;

        public BallConfig(AssetManager am, World world){
            super(am, world);
        }

        public BallConfig(AssetManager am, World world, float ratio){
            super(am, world, ratio);
        }

        /**
         * Convierte a unidades de Grid teniendo en cuenta la cantidad de pixeles por metro
         */
        public void setDiameter(float diameter) {
            this.diameter = pixelsPerMeter * diameter;
        }
    }

    public Ball(BallConfig config) {
        this(config, BodyDef.BodyType.KinematicBody);
    }

    public Ball(BallConfig config, BodyDef.BodyType bodyType) {
        super(config);

        Shape shape = circle(config.diameter);
        createFixture(shape);
        createBody(config.world, bodyType, config.x, config.y);

        Texture texture = getTexture(config.type);
        float scale = config.diameter / (float) texture.getWidth() / LevelBaseScreen.RATIO_METER_PIXEL;
        createActor(texture, scale);
    }

    private Shape circle(float diameter) {
        CircleShape shape = new CircleShape();
        shape.setRadius(0.5f * diameter);

        return shape;
    }

    private Texture getTexture(Type type) {
        switch (type) {
            case BLACK: return getAsset("game/circle_black.png", Texture.class);
            case GREY: return getAsset("game/circle_grey.png", Texture.class);
            case RED: return getAsset("game/circle_red.png", Texture.class);
            case DUMMY: return getAsset("game/circle_dummy.png", Texture.class);
            default: return getAsset("game/circle_grey.png", Texture.class);
        }
    }

}
