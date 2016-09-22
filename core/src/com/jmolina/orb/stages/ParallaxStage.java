/*
 * IMPULSE ORB
 * Copyright (C) 2016 Juan M. Molina
 *
 * This file is part of the IMPULSE ORB source code.
 *
 * IMPULSE ORB is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * IMPULSE ORB is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.jmolina.orb.stages;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.jmolina.orb.managers.AssetManager;
import com.jmolina.orb.var.Atlas;
import com.jmolina.orb.var.Utils;
import com.jmolina.orb.var.Var;
import com.jmolina.orb.widgets.game.TiledLayer;

/**
 * Stage donde se dibuja el fondo parallax. Se contemplan hasta 3 planos de scroll paralelos.
 */
public class ParallaxStage extends Stage {

    /** Velocidad de la camara para cada capa del fondo parallax */
    private final float LAYER_1_SPEED = 1 / 4.0f;
    private final float LAYER_2_SPEED = 1 / 8.0f;
    private final float LAYER_3_SPEED = 1 / 64.0f;

    private float pixelsPerMeter;

    /** Capas del fondo parallax */
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
        float y = -8 * region1.getRegionHeight();
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

    /**
     * Dibuja las 3 capas parallax
     *
     * @param camera Camara
     */
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
     * @param layer Capa
     * @param speed Velocidad de movimiento de la camara para la capa
     * @param x Posicion actual X
     * @param y Posicion actual Y
     */
    private void drawLayer(TiledLayer layer, float speed, float x, float y) {
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
