package com.jmolina.orb.data;

/**
 * Representa el efecto de tick o calentamiento de los elementos calentadores
 */
public class Tick {

    /** Cantidad por tick */
    public float amount;

    /** Periodo del ticking */
    public float period;

    /** Contador de tiempo */
    public float timer;

    /**
     * Constructor
     */
    public Tick() {
        amount = 0f;
        period = 0f;
        timer = 0f;
    }

    /**
     * Indica si el tiempo transcurrido ha superado el tiempo entre ticks
     */
    public boolean expired() {
        return timer > period;
    }

    /**
     * Reinicia el temporizador
     */
    public void reset() {
        timer = 0f;
    }

    /**
     * Incrementa el temporizador
     *
     * @param time Incremento de tiempo
     */
    public void update(float time) {
        this.timer += time;
    }

}
