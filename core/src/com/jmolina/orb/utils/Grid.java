package com.jmolina.orb.utils;

public class Grid {

    private final static float CELL_SIZE = 64.0f;

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
