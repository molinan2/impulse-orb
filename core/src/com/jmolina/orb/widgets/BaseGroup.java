package com.jmolina.orb.widgets;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Disposable;
import com.jmolina.orb.interfaces.Reseteable;

public class BaseGroup extends Group implements Reseteable, Disposable {

    public static final int COLOR_WHITE = 0xffffffff;
    public static final int COLOR_BLUE = 0x2E3192ff;
    public static final int COLOR_BLACK = 0x4D4D4Dff;

    private AssetManager assetManager;

    public BaseGroup(AssetManager am) {
        setScale(1.0f, 1.0f);
        setOrigin(0f, 0f);
        setAssetManager(am);
    }

    @Override
    public void draw (Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
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
