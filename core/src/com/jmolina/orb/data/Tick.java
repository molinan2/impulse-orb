package com.jmolina.orb.data;

public class Tick {

    public float amount;
    public float period;
    public float time;

    public Tick() {
        amount = 0f;
        period = 0f;
        time = 0f;
    }

    public boolean expired() {
        return time > period;
    }

    public void reset() {
        time = 0f;
    }

    public void update(float time) {
        this.time += time;
    }

}
