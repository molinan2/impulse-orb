package com.jmolina.orb.utils;

import com.jmolina.orb.var.Var;

public class Grid {

    public static float cellX(int unit) {
        return (float) unit * Var.GRID_UNIT;
    }

    public static float cellY(int unit) {
        return Var.VIEWPORT_HEIGHT - (float) unit * Var.GRID_UNIT;
    }

    public static float cellX(float unit) {
        return unit * Var.GRID_UNIT;
    }

    public static float cellY(float unit) {
        return Var.VIEWPORT_HEIGHT - unit * Var.GRID_UNIT;
    }

    public static float unit(int unit) {
        return (float) unit * Var.GRID_UNIT;
    }

    public static float unit(float unit) {
        return unit * Var.GRID_UNIT;
    }

}
