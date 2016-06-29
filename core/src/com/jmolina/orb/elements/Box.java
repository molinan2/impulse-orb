package com.jmolina.orb.elements;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;
import com.jmolina.orb.assets.Asset;
import com.jmolina.orb.screens.LevelBaseScreen;

public class Box extends Element {

    static public class BoxConfig extends ElementConfig {
        public float width;
        public float height;

        public BoxConfig(AssetManager am, World world){
            super(am, world);
        }

        public BoxConfig(AssetManager am, World world, float ratio){
            super(am, world, ratio);
        }

        /**
         * Convierte a unidades de Grid teniendo en cuenta la cantidad de pixeles por metro
         */
        public void setSize(float width, float height) {
            this.width = pixelsPerMeter * width;
            this.height = pixelsPerMeter * height;
        }
    }

    public Box(BoxConfig config) {
        super(config);

        Shape shape = box(config.width, config.height);
        createFixture(shape);
        createBody(config.world, BodyDef.BodyType.StaticBody, config.x, config.y, config.rotation);

        Texture texture = getTexture(config.type);
        float scaleX = config.width / (float) texture.getWidth() / LevelBaseScreen.RATIO_METER_PIXEL;
        float scaleY = config.height / (float) texture.getHeight() / LevelBaseScreen.RATIO_METER_PIXEL;
        createActor(texture, scaleX, scaleY, config.rotation);
    }

    private Shape box(float width, float height) {
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(0.5f * width, 0.5f * height);

        return shape;
    }

    private Texture getTexture(Type type) {
        switch (type) {
            case BLACK: return getAsset(Asset.GAME_SQUARE_BLACK, Texture.class);
            case GREY: return getAsset(Asset.GAME_SQUARE_GREY, Texture.class);
            case RED: return getAsset(Asset.GAME_SQUARE_RED, Texture.class);
            default: return getAsset(Asset.GAME_SQUARE_GREY, Texture.class);
        }
    }

}
