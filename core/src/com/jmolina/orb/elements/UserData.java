package com.jmolina.orb.elements;

public class UserData {

    public Element.Type type = Element.Type.GREY;
    public Element.Effect effect = Element.Effect.NONE;

    public String toString() {
        return type + ": " + effect + ".";
    }

}
