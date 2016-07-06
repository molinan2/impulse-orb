package com.jmolina.orb.data;

public class Attempt {

    /** Tiempo invertido */
    private float time;

    /** Distancia recorrida */
    private float distance;

    /** Si el Orb resulto destruido en el transcurso */
    private boolean destroyed;

    public Attempt() {
        time = 0f;
        distance = 0f;
        destroyed = false;
    }

    public void addTime(float timeIncrement) {
        time += timeIncrement;
    }

    public void addDistance(float distanceIncrement) {
        distance += distanceIncrement;
    }

    public float getDistance() {
        return distance;
    }

    public float getTime() {
        return time;
    }

    public boolean isDestroyed() {
        return destroyed;
    }

    public void setDestroyed() {
        destroyed = true;
    }

}
