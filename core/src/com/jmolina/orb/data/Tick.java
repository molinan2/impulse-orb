package com.jmolina.orb.data;

public class Tick {

    public float amount;
    public float period;
    public float timer;

    public Tick() {
        amount = 0f;
        period = 0f;
        timer = 0f;
    }

    public boolean expired() {
        return timer > period;
    }

    public void reset() {
        timer = 0f;
    }

    public void update(float time) {
        timer += time;
    }

}
