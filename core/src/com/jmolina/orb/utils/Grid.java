package com.jmolina.orb.utils;

import com.jmolina.orb.var.Var;

public class Grid {

    private final static float CELL_SIZE = Var.GRID_CELL_SIZE;

    /**
     * Translates grid cells to pixels
     *
     * @param cells int
     * @return float
     */
    public static float unit(int cells) {
        return (float) cells * CELL_SIZE;
    }

    /**
     * Translates grid cells to pixels
     *
     * @param cells float
     * @return float
     */
    public static float unit(float cells) {
        return cells * CELL_SIZE;
    }

    /**
     * Height of the grid viewport
     *
     * @return float
     */
    public static float height () {
        return unit(18.5f);
    }

    /**
     * Width of the grid viewport
     *
     * @return float
     */
    public static float width () {
        return unit(12f);
    }

}
