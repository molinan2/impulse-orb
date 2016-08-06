package com.jmolina.orb.situations;

import com.jmolina.orb.managers.AssetManager;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.SnapshotArray;
import com.jmolina.orb.elements.Element;
import com.jmolina.orb.var.Var;

/**
 * Una lista de elementos creados y configurados, posicionados respecto a (0,0)
 * Mide 18x12 unidades (alto x ancho)
 */
public abstract class Situation {

    protected final float HEIGHT = 18.0f;
    protected final float WIDTH = 12.0f;

    private AssetManager assetManager;
    private World world;
    private SnapshotArray<Element> elements;
    private float pixelsPerMeter, cellSizeCorrectionFactor;

    public Situation(AssetManager am, World world, float pixelsPerMeter) {
        this.pixelsPerMeter = pixelsPerMeter;
        this.elements = new SnapshotArray<Element>();
        this.assetManager = am;
        this.world = world;
        this.cellSizeCorrectionFactor = pixelsPerMeter / Var.GRID_CELL_SIZE;

        createElements();
    }

    protected abstract void createElements ();

    protected float getPixelsPerMeter() {
        return pixelsPerMeter;
    }

    /**
     * TODO
     * Sólo con crear un elemento ya se añade al mundo. addElement sólo lo indexa en la situación
     * para que se pueda modificar su posición relativa basada en la situación. Si no se hace
     * addElement, el elemento se sitúa con su posición de base, pero se dibuja en su situación.
     * ¿Es esto normal?
     */
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
