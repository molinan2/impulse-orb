package com.jmolina.orb.interfaces;

/**
 * Visitor pattern
 * Allows performing a callback
 *
 * It could handle an Object parameter: run(Object object)
 *
 * TODO
 * Es exactamente lo mismo que un Runnable
 */
public interface Visitor {
    void run();
}
