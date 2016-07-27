package com.jmolina.orb.situations;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.SnapshotArray;
import com.jmolina.orb.elements.BaseElement;
import com.jmolina.orb.var.Var;

/**
 * Una lista de elementos creados y configurados, posicionados respecto a (0,0)
 * Mide 18x12 unidades (alto x ancho)
 */
public abstract class Situation {

    private final float HEIGHT = 18.0f;
    private final float WIDTH = 12.0f;

    private AssetManager assetManager;
    private World world;
    private SnapshotArray<BaseElement> elements;
    private float pixelsPerMeter, cellSizeCorrectionFactor;

    public Situation(AssetManager am, World world, float pixelsPerMeter) {
        this.pixelsPerMeter = pixelsPerMeter;
        this.elements = new SnapshotArray<BaseElement>();
        this.assetManager = am;
        this.world = world;
        this.cellSizeCorrectionFactor = pixelsPerMeter / Var.GRID_CELL_SIZE;

        createElements();
    }

    protected abstract void createElements ();

    protected float getPixelsPerMeter() {
        return pixelsPerMeter;
    }

    public void addElement (BaseElement element) {
        this.elements.add(element);
    }

    public SnapshotArray<BaseElement> getElements () {
        return this.elements;
    }

    public void setPosition(int orderY) {
        for (BaseElement element : getElements()) {
            element.setPosition(
                    element.getBody().getPosition().x,
                    element.getBody().getPosition().y + orderY * HEIGHT
            );
        }
    }

    protected AssetManager getAssetManager() {
        return this.assetManager;
    }

    protected World getWorld() {
        return this.world;
    }

    public synchronized <T> T getAsset (String fileName, Class<T> type) {
        return getAssetManager().get(fileName, type);
    }

}
