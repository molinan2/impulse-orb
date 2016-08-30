package com.jmolina.orb.data;

/**
 * Guarda información relativa a la rotacion de un elemento movil ({@link com.jmolina.orb.elements.Movable})
 */
public class Rotation {

    /** Frecuencia de la rotacion */
    public float frequency;

    /** Indica si la rotacion es en sentido de las agujas del reloj */
    public boolean clockwise;

    /**
     * Constructor
     */
    public Rotation() {
        frequency = 0f;
        clockwise = true;
    }

}
