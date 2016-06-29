package com.jmolina.orb.situations;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.SnapshotArray;
import com.jmolina.orb.elements.Element;

public abstract class Situation {

    private AssetManager assetManager;
    private World world;
    private SnapshotArray<Element> elements;
    private float height = 18f;
    private float width;

    public Situation(AssetManager am, World world) {
        this.elements = new SnapshotArray<Element>();
        this.assetManager = am;
        this.world = world;

        createElements();
    }

    protected abstract void createElements ();

    public void addElement (Element element) {
        this.elements.add(element);
    }

    public SnapshotArray<Element> getElements () {
        return this.elements;
    }

    public void setOrder (int order) {
        for (Element element : getElements()) {
            element.setPosition(
                    element.getBody().getPosition().x,
                    element.getBody().getPosition().y + order * height
            );
        }
    }

    protected AssetManager getAssetManager() {
        return this.assetManager;
    }

    protected World getWorld() {
        return this.world;
    }

}
