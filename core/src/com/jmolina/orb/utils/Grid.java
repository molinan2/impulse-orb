package com.jmolina.orb.utils;

import com.jmolina.orb.var.Var;

public class Grid {

    /**
     * Translates grid units to pixels
     *
     * @param unit int
     * @return float
     */
    public static float unit(int unit) {
        return (float) unit * Var.GRID_UNIT;
    }

    /**
     * Translates grid units to pixels
     *
     * @param unit unit
     * @return float
     */
    public static float unit(float unit) {
        return unit * Var.GRID_UNIT;
    }

    /**
     * Equivalent to Var.VIEWPORT_HEIGHT
     *
     * @return float
     */
    public static float height () {
        return unit(18.5f);
    }

    /**
     * Equivalent to Var.VIEWPORT_WIDTH
     *
     * @return float
     */
    public static float width () {
        return unit(12f);
    }

}
