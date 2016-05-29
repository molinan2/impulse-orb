package com.jmolina.orb.utils;

import com.jmolina.orb.var.Var;

public class Grid {

    public static float x(int unit) {
        return (float) unit * 64.0f;
    }

    public static float y(int unit) {
        return Var.VIEWPORT_HEIGHT - (float) unit * 64.0f;
    }

    public static float x(float unit) {
        return unit * 64.0f;
    }

    public static float y(float unit) {
        return Var.VIEWPORT_HEIGHT - unit * 64.0f;
    }

}
