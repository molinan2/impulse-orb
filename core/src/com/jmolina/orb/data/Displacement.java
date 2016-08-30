package com.jmolina.orb.data;

/**
 * Guarda información relativa al desplazamiento de un elemento movil ({@link com.jmolina.orb.elements.Movable})
 */
public class Displacement {

    /** Frecuencia del desplazamiento */
    public float frequency = 0f;

    /** Desplazamiento total en el eje XX' */
    public float x = 0f;

    /** Desplazamiento total en el eje YY' */
    public float y = 0f;

    /**
     * Constructor
     */
    public Displacement() {
        frequency = 0f;
        x = 0f;
        y = 0f;
    }

}
