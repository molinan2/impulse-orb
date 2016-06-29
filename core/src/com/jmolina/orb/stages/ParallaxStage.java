package com.jmolina.orb.stages;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.jmolina.orb.assets.Asset;
import com.jmolina.orb.managers.AssetManager;
import com.jmolina.orb.screens.BaseScreen;
import com.jmolina.orb.screens.LevelBaseScreen;
import com.jmolina.orb.widgets.ParallaxLayer;

public class ParallaxStage extends Stage {

    private final float VIEWPORT_WIDTH = BaseScreen.VIEWPORT_WIDTH;
    private final float VIEWPORT_HEIGHT = BaseScreen.VIEWPORT_HEIGHT;
    private final float PIXELS_PER_METER = LevelBaseScreen.PIXELS_PER_METER;

    private Image plane1;
    private Image plane2;
    private Image plane3;

    private ParallaxLayer layer1;
    private ParallaxLayer layer2;
    private ParallaxLayer layer3;

    public ParallaxStage(Viewport vp, AssetManager am) {
        super(vp);

        Texture layer1Texture = am.get(Asset.GAME_BG_LAYER1, Texture.class);
        Texture layer2Texture = am.get(Asset.GAME_BG_LAYER2, Texture.class);
        Texture layer3Texture = am.get(Asset.GAME_BG_LAYER3, Texture.class);

        layer1 = new ParallaxLayer(am, layer1Texture, VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
        layer2 = new ParallaxLayer(am, layer2Texture, VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
        layer3 = new ParallaxLayer(am, layer3Texture, VIEWPORT_WIDTH, VIEWPORT_HEIGHT);

        layer1.setPosition(-VIEWPORT_WIDTH, -VIEWPORT_HEIGHT);
        layer2.setPosition(-VIEWPORT_WIDTH, -VIEWPORT_HEIGHT);
        layer3.setPosition(-VIEWPORT_WIDTH, -VIEWPORT_HEIGHT);

        addActor(layer1);
        addActor(layer2);
        addActor(layer3);
    }

    public void draw(float x, float y) {
        Camera camera = getViewport().getCamera();
        Batch batch = this.getBatch();

        // Layer 3
        camera.position.x = x * PIXELS_PER_METER / 64f;
        camera.position.y = y * PIXELS_PER_METER / 64f;
        camera.update();

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        layer3.draw(batch, 1);
        batch.end();

        // Layer 2
        camera.position.x = x * PIXELS_PER_METER / 8f;
        camera.position.y = y * PIXELS_PER_METER / 8f;
        camera.update();

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        layer2.draw(batch, 1);
        batch.end();

        // Layer 1
        camera.position.x = x * PIXELS_PER_METER / 4f;
        camera.position.y = y * PIXELS_PER_METER / 4f;
        camera.update();

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        layer1.draw(batch, 1);
        batch.end();
    }

}
