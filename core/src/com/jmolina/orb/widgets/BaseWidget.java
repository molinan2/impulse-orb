package com.jmolina.orb.widgets;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Disposable;
import com.jmolina.orb.interfaces.Reseteable;
import com.jmolina.orb.utils.Grid;

public class BaseWidget extends Group implements Reseteable, Disposable {

    private AssetManager assetManager;

    public BaseWidget() {
        setScale(1.0f, 1.0f);
        setOrigin(0f, 0f);
    }

    public BaseWidget(AssetManager am) {
        this();
        setAssetManager(am);
    }

    @Override
    public void draw (Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
    }

    public void setGridPosition (int xGrid, int yGrid) {
        this.setPosition(Grid.cellX(xGrid), Grid.cellY(yGrid));
    }

    public void setGridPosition (float xGrid, float yGrid) {
        this.setPosition(Grid.cellX(xGrid), Grid.cellY(yGrid));
    }

    @Override
    public void reset() {

    }

    public AssetManager getAssetManager() {
        return this.assetManager;
    }

    public void setAssetManager(AssetManager am) {
        this.assetManager = am;
    }

    public synchronized <T> T getAsset (String fileName, Class<T> type) {
        return getAssetManager().get(fileName, type);
    }

    @Override
    public void dispose() {

    }

}
