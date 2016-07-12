package com.jmolina.orb.situations;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.SnapshotArray;
import com.jmolina.orb.elements.Element;

/**
 * Una lista de elementos creados y configurados, posicionados respecto a (0,0)
 *
 * Inicialmente, todas de 18 unidades de alto y 12 de ancho. Mas adelante:
 *
 * - Que carguen de JSON
 * - Que guarde su altura y se consulte cuando se vayan a apilar las situaciones
 * - Que guarde su anchura e idem (mas complicado)
 */
public abstract class Situation {

    private AssetManager assetManager;
    private World world;
    private SnapshotArray<Element> elements;
    private float height = 18f;
    private float width;
    private float ratioMeterPixel;

    public Situation(AssetManager am, World world, float ratioMeterPixel) {
        this.ratioMeterPixel = ratioMeterPixel;
        this.elements = new SnapshotArray<Element>();
        this.assetManager = am;
        this.world = world;

        createElements();
    }

    protected abstract void createElements ();

    protected float getRatioMeterPixel() {
        return ratioMeterPixel;
    }

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

    public synchronized <T> T getAsset (String fileName, Class<T> type) {
        return getAssetManager().get(fileName, type);
    }

}
