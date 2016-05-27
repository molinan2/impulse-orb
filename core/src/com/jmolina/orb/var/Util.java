package com.jmolina.orb.var;

public class Util {

    public static float xGrid(int unit) {
        return (float) unit * 64.0f;
    }

    public static float yGrid(int unit) {
        return Var.VIEWPORT_HEIGHT - (float) unit * 64.0f;
    }

    public static float xGrid(float unit) {
        return unit * 64.0f;
    }

    public static float yGrid(float unit) {
        return Var.VIEWPORT_HEIGHT - unit * 64.0f;
    }

}
