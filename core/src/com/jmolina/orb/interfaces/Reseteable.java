package com.jmolina.orb.interfaces;

public interface Reseteable {

    /**
     * Resetea a su estado inicial las Actions de un Group y sus Actors
     * Un Group debe saber como resetear sus Actors y resetearse a si mismo
     */
    public void reset();

}
