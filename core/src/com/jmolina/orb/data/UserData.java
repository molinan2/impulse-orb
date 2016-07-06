package com.jmolina.orb.data;

import com.jmolina.orb.elements.Element;

/**
 * Datos de usuario para guardarlos como UserData en un Fixture
 */
public class UserData {

    public Element.Type type = Element.Type.GREY;
    public Element.Effect effect = Element.Effect.NONE;

    public String toString() {
        return type + ": " + effect + ".";
    }

}
