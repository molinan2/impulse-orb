package com.jmolina.orb.data;

import com.jmolina.orb.elements.WorldElement;

/**
 * Datos de usuario para guardarlos mediante {@link com.badlogic.gdx.physics.box2d.Fixture#setUserData(Object)}
 * en el Fixture de cada Body.
 */
public class UserData {

    /** Sabor del elemento */
    public WorldElement.Flavor flavor;

    /** Efecto del elemento */
    public WorldElement.Effect effect;

    /** Características del tick (en caso de ser calentador) */
    public Tick tick;

    /**
     * Constructor
     */
    public UserData() {
        tick = new Tick();
    }

}
