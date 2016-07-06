package com.jmolina.orb.data;

public class Attempt {

    private float time;
    private float distance;

    public Attempt() {
        time = 0f;
        distance = 0f;
    }

    public void addTime(float timeIncrement) {
        time += timeIncrement;
    }

    public void addDistance(float distanceIncrement) {
        distance += distanceIncrement;
    }

}
