package com.jmolina.orb.var;

public class Vars {

    public static float VIEWPORT_HEIGHT = 1184.0f;
    public static float VIEWPORT_WIDTH = 768.0f;

    public static float span(int span) {
        return VIEWPORT_WIDTH * (float) span / 12.0f;
    }

}
