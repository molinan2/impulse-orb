package com.jmolina.orb.stages;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.jmolina.orb.var.Asset;
import com.jmolina.orb.managers.AssetManager;
import com.jmolina.orb.var.AssetAtlas;
import com.jmolina.orb.var.Var;
import com.jmolina.orb.widgets.game.TiledLayer;

public class ParallaxStage extends Stage {

    private final float LAYER_1_SPEED = 1 / 4.0f;
    private final float LAYER_2_SPEED = 1 / 8.0f;
    private final float LAYER_3_SPEED = 1 / 64.0f;

    private float pixelsPerMeter;
    private TiledLayer layer1;
    private TiledLayer layer2;
    private TiledLayer layer3;

    /**
     * Constructor
     */
    public ParallaxStage(AssetManager assetManager, Viewport viewport, float pixelsPerMeter) {
        super(viewport);

        this.pixelsPerMeter = pixelsPerMeter;

        float width = viewport.getWorldWidth();
        float height = viewport.getWorldHeight();

        TextureRegion region1 = assetManager.getAtlas().findRegion(AssetAtlas.GAME_PARALLAX_LAYER_1);
        TextureRegion region2 = assetManager.getAtlas().findRegion(AssetAtlas.GAME_PARALLAX_LAYER_2);
        TextureRegion region3 = assetManager.getAtlas().findRegion(AssetAtlas.GAME_PARALLAX_LAYER_3);

        layer1 = new TiledLayer(assetManager, region1, width, height);
        layer2 = new TiledLayer(assetManager, region2, width, height);
        layer3 = new TiledLayer(assetManager, region3, width, height);

        layer1.setPosition(-width, -height);
        layer2.setPosition(-width, -height);
        layer3.setPosition(-width, -height);

        // Tiene en cuenta el tamaño global de unidad del grid
        float scale  = pixelsPerMeter / Var.GRID_CELL_SIZE;

        layer1.setScale(scale);
        layer2.setScale(scale);
        layer3.setScale(scale);

        addActor(layer3);
        addActor(layer2);
        addActor(layer1);

        getRoot().setTransform(false);
    }

    public void draw(float x, float y) {
        drawLayer(layer3, LAYER_3_SPEED, x, y);
        drawLayer(layer2, LAYER_2_SPEED, x, y);
        drawLayer(layer1, LAYER_1_SPEED, x, y);
    }

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
