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

package com.jmolina.orb.situations;

import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.SnapshotArray;
import com.jmolina.orb.elements.Element;
import com.jmolina.orb.managers.AssetManager;

/**
 * Una lista de elementos creados y configurados, posicionados respecto a (0,0). Se utiliza como
 * pieza basica para crear un nivel. Mide siempre 12x18 unidades.
 */
public abstract class Situation {

    public static final float HEIGHT = 18.0f;
    public static final float WIDTH = 12.0f;

    /** Elementos de la situacion */
    private SnapshotArray<Element> elements;

    /** Ordinal de altura de la situacion, empezando por 0 */
    private int positionY;

    private AssetManager assetManager;
    private World world;
    private float pixelsPerMeter;

    /**
     * Constructor
     *
     * @param am AssetManager
     * @param world Mundo fisico
     * @param pixelsPerMeter Factor de correcion pixeles/metros
     */
    public Situation(AssetManager am, World world, float pixelsPerMeter) {
        this.pixelsPerMeter = pixelsPerMeter;
        this.world = world;
        assetManager = am;
        elements = new SnapshotArray<Element>();
        positionY = 0;

        createElements();
    }

    /**
     * Crea los elementos de la situacion
     */
    protected abstract void createElements ();

    /**
     * Devuelve el factor de correccion pixeles/metros
     */
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

    /**
     * Devuelve el array de elementos de la situacion
     */
    public SnapshotArray<Element> getElements () {
        return this.elements;
    }

    /**
     * Posición de la Situation dentro de un Level. El offset de cada Element vendrá dado por
     * {@link #positionY} * {@link #HEIGHT}.
     *
     * @param positionY Posición ordinal, empezando por 0.
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

    /**
     * Devuelve la posicion ordinal de esta situacion
     */
    public int getPositionY() {
        return positionY;
    }

    /**
     * Devuelve el AssetManager
     */
    protected AssetManager getAssetManager() {
        return this.assetManager;
    }

    /**
     * Devuelve el mundo fisico
     */
    protected World getWorld() {
        return this.world;
    }

    /**
     * Libera los recursos de los elementos de esta situacion y elimina referencias
     */
    public void dispose() {
        for (Element element : getElements())
            element.dispose();

        assetManager = null;
        world = null;
        elements = null;
    }

}
