package com.jmolina.orb.var;

public class Utils {

    public static float xGrid(int unit) {
        return (float) unit * 64.0f;
    }

    public static float yGrid(int unit) {
        return Vars.VIEWPORT_HEIGHT - (float) unit * 64.0f;
    }

}
