package com.jmolina.orb.interfaces;

/**
 * Example of Singleton "global" class
 */
public class Singleton {
    private static Singleton ourInstance = new Singleton();

    public static Singleton getInstance() {
        return ourInstance;
    }

    private Singleton() {
    }
}
