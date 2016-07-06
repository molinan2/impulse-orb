package com.jmolina.orb.data;

public class Attempt {

    /** Tiempo invertido */
    private float time;

    /** Distancia recorrida */
    private float distance;

    /** Si el Orb resulto destruido en el transcurso */
    private boolean failed;

    /** Si el Orb alcanzo la salida */
    private boolean successful;

    /**
     * Constructor
     */
    public Attempt() {
        time = 0f;
        distance = 0f;
        failed = false;
        successful = false;
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

    public boolean isFailed() {
        return failed;
    }

    public boolean isSuccessful() {
        return successful;
    }

    public void setFailed(boolean failed) {
        this.failed = failed;
    }

    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }

}
