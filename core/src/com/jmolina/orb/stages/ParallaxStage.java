package com.jmolina.orb.stages;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.jmolina.orb.assets.Asset;
import com.jmolina.orb.managers.AssetManager;
import com.jmolina.orb.screens.Level;
import com.jmolina.orb.widgets.TiledLayer;

public class ParallaxStage extends Stage {

    private final float PIXELS_PER_METER = Level.PIXELS_PER_METER;
    private final float LAYER_1_SPEED = 1 / 4.0f;
    private final float LAYER_2_SPEED = 1 / 8.0f;
    private final float LAYER_3_SPEED = 1 / 64.0f;

    private TiledLayer layer1;
    private TiledLayer layer2;
    private TiledLayer layer3;

    /**
     * FIX: evita que se dibuje la Stage después de haber llamado a dispose(), ya que
     * Level#render() continúa ejecutándose al menos 1 frame después de Level#dispose().
     * Moar: https://bitbucket.org/molinan2/orb2/issues/38/crash
     */
    private boolean disposing = false;

    /**
     * Constructor
     */
    public ParallaxStage(AssetManager am, Viewport vp) {
        super(vp);

        float width = vp.getWorldWidth();
        float height = vp.getWorldHeight();

        Texture layer1Texture = am.get(Asset.GAME_BG_LAYER1, Texture.class);
        Texture layer2Texture = am.get(Asset.GAME_BG_LAYER2, Texture.class);
        Texture layer3Texture = am.get(Asset.GAME_BG_LAYER3, Texture.class);

        layer1 = new TiledLayer(am, layer1Texture, width, height);
        layer2 = new TiledLayer(am, layer2Texture, width, height);
        layer3 = new TiledLayer(am, layer3Texture, width, height);

        layer1.setPosition(-width, -height);
        layer2.setPosition(-width, -height);
        layer3.setPosition(-width, -height);

        addActor(layer3);
        addActor(layer2);
        addActor(layer1);
    }

    @Override
    public void dispose() {
        disposing = true;
        super.dispose();
    }

    public void draw(float x, float y) {
        if (!disposing) {
            drawLayer(layer3, LAYER_3_SPEED, x, y);
            drawLayer(layer2, LAYER_2_SPEED, x, y);
            drawLayer(layer1, LAYER_1_SPEED, x, y);
        }
    }

    private void drawLayer (TiledLayer layer, float speed, float x, float y) {
        Camera camera = getViewport().getCamera();
        camera.position.x = x * PIXELS_PER_METER * speed;
        camera.position.y = y * PIXELS_PER_METER * speed;
        camera.update();

        if (!getRoot().isVisible()) return;

        Batch batch = getBatch();

        if (batch != null) {
            batch.setProjectionMatrix(camera.combined);
            batch.begin();
            layer.draw(batch, 1);
            batch.end();
        }
    }

}
