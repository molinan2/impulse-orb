package com.jmolina.orb.data;

import com.jmolina.orb.elements.WorldElement;

/**
 * Datos de usuario para guardarlos mediante {@link com.badlogic.gdx.physics.box2d.Fixture#setUserData(Object)}
 * en el Fixture de cada Body.
 */
public class UserData {

    public WorldElement.Flavor flavor;
    public WorldElement.Effect effect;

}
