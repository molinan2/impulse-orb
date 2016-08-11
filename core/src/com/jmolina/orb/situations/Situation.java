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

    public static final float HEIGHT = 18.0f;
    public static final float WIDTH = 12.0f;

    private AssetManager assetManager;
    private World world;
    private SnapshotArray<Element> elements;
    private float pixelsPerMeter;
    private int positionY;

    public Situation(AssetManager am, World world, float pixelsPerMeter) {
        this.pixelsPerMeter = pixelsPerMeter;
        this.world = world;
        assetManager = am;
        elements = new SnapshotArray<Element>();
        positionY = 0;

        createElements();
    }

    protected abstract void createElements ();

    protected float getPixelsPerMeter() {
        return pixelsPerMeter;
    }

    /**
     * Indexa un elemento dentro de la situación para que se pueda modificar su posición relativa
     * basada en la situación. Si no se hace addElement, el elemento permanece situado en la posición
     * que tuviera, pero se dibuja en la posición de la situación.
     *
     * Sólo con crear un elemento ya se añade al mundo con una posición dada. addElement sólo lo
     * indexa en la situación.
     */
    public void addElement (Element element) {
        this.elements.add(element);
    }

    public SnapshotArray<Element> getElements () {
        return this.elements;
    }

    /**
     * Posición de la Situation dentro de un Level. El offset de cada Element vendrá dado por
     * {@link #positionY} * {@link #HEIGHT}.
     *
     * @param positionY Posición, empezando por 0.
     */
    public void setPositionY(int positionY) {
        this.positionY = positionY;
        float offsetY = positionY * HEIGHT;

        for (Element element : getElements()) {
            element.setPosition(
                    element.getBody().getPosition().x,
                    element.getBody().getPosition().y + offsetY
            );
        }
    }

    public int getPositionY() {
        return positionY;
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

    public void dispose() {
        for (Element element : getElements()) {
            element.dispose();
        }

        assetManager = null;
        world = null;
        elements = null;
    }

}
