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

    /** Puesto en el ranking */
    private int rank;

    /**
     * Constructor
     */
    public Attempt() {
        time = 0f;
        distance = 0f;
        failed = false;
        successful = false;
        rank = 0;
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

    public void setTime(float time) {
        this.time = time;
    }

    public float getTime() {
        return time;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public boolean isFailed() {
        return failed;
    }

    public boolean isSuccessful() {
        return successful;
    }

    public boolean isCompleted() {
        return isFailed() || isSuccessful();
    }

    public void setFailed(boolean failed) {
        this.failed = failed;
    }

    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getRank() {
        return rank;
    }

}
