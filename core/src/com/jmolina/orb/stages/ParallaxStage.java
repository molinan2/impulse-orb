package com.jmolina.orb.stages;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.jmolina.orb.situations.Situation;
import com.jmolina.orb.utils.Utils;
import com.jmolina.orb.managers.AssetManager;
import com.jmolina.orb.var.Atlas;
import com.jmolina.orb.var.Var;
import com.jmolina.orb.widgets.game.TiledLayer;

public class ParallaxStage extends Stage {

    private final float LAYER_1_SPEED = 1 / 4.0f;
    private final float LAYER_2_SPEED = 1 / 8.0f;
    private final float LAYER_3_SPEED = 1 / 64.0f;

    private float pixelsPerMeter;
    private TiledLayer layer1, layer2, layer3;

    /**
     * Constructor
     */
    public ParallaxStage(AssetManager assetManager, Viewport viewport, float pixelsPerMeter) {
        super(viewport);

        this.pixelsPerMeter = pixelsPerMeter;

        TextureRegion region1 = assetManager.getGameAtlas().findRegion(Atlas.GAME_PARALLAX_LAYER_1_BLUR);
        TextureRegion region2 = assetManager.getGameAtlas().findRegion(Atlas.GAME_PARALLAX_LAYER_2_BLUR);
        TextureRegion region3 = assetManager.getGameAtlas().findRegion(Atlas.GAME_PARALLAX_LAYER_3);

        float x = -2 * region1.getRegionWidth() + region1.getRegionWidth() * 0.5f - Utils.cell(0.5f);
        float y = -2 * region1.getRegionHeight();
        float scale = pixelsPerMeter / Var.GRID_CELL_SIZE;

        layer1 = new TiledLayer(assetManager, region1, viewport);
        layer2 = new TiledLayer(assetManager, region2, viewport);
        layer3 = new TiledLayer(assetManager, region3, viewport);
        layer1.setPosition(x, y);
        layer2.setPosition(x, y);
        layer3.setPosition(x, y);
        layer1.setScale(scale);
        layer2.setScale(scale);
        layer3.setScale(scale);

        addActor(layer3);
        addActor(layer2);
        addActor(layer1);
        getRoot().setTransform(false);
    }

    public void draw(Camera camera) {
        float x = camera.position.x;
        float y = camera.position.y;

        drawLayer(layer3, LAYER_3_SPEED, x, y);
        drawLayer(layer2, LAYER_2_SPEED, x, y);
        drawLayer(layer1, LAYER_1_SPEED, x, y);
    }

    /**
     * Método de dibujo de {@link Stage#draw()} modificado para que varíe la cámara de cada capa.
     *
     * @param layer
     * @param speed
     * @param x
     * @param y
     */
    private void drawLayer (TiledLayer layer, float speed, float x, float y) {
        Camera camera = getViewport().getCamera();
        camera.position.x = x * pixelsPerMeter * speed;
        camera.position.y = y * pixelsPerMeter * speed;
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
