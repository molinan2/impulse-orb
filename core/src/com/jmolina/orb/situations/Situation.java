package com.jmolina.orb.situations;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.SnapshotArray;
import com.jmolina.orb.elements.Element;

/**
 * Una lista de elementos creados y configurados, posicionados respecto a (0,0)
 * Mide 18x12 (alto x ancho)
 *
 * TODO
 * Que carguen de JSON
 */
public abstract class Situation {

    private final float HEIGHT = 18.0f;
    private final float WIDTH = 12.0f;

    private AssetManager assetManager;
    private World world;
    private SnapshotArray<Element> elements;
    private float pixelsPerMeter;

    public Situation(AssetManager am, World world, float pixelsPerMeter) {
        this.pixelsPerMeter = pixelsPerMeter;
        this.elements = new SnapshotArray<Element>();
        this.assetManager = am;
        this.world = world;

        createElements();
    }

    protected abstract void createElements ();

    protected float getPixelsPerMeter() {
        return pixelsPerMeter;
    }

    public void addElement (Element element) {
        this.elements.add(element);
    }

    public SnapshotArray<Element> getElements () {
        return this.elements;
    }

    public void setPosition(int orderY) {
        for (Element element : getElements()) {
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
