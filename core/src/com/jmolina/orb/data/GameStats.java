package com.jmolina.orb.data;

import java.util.ArrayList;

public class GameStats {

    private ArrayList<Attempt> attempts;

    private Attempt getLastAttemp() {
        return attempts.get(attempts.size()-1);
    }

    private boolean hasAttemps () {
        if (attempts.size() != 0)
            return true;
        else
            return false;
    }

    public GameStats() {
        attempts = new ArrayList<Attempt>();
    }

    public void newTry() {
        Attempt attempt = new Attempt();
        attempts.add(attempt);
    }

    public void addTime(float time) {
        if (hasAttemps()) {
            getLastAttemp().addTime(time);
        }
    }

    public void addDistance(float distance) {
        if (hasAttemps()) {
            getLastAttemp().addDistance(distance);
        }
    }

    public void setDestroyed() {
        if (hasAttemps()) {
            getLastAttemp().setDestroyed();
        }
    }

    public float getCurrentDistance() {
        if (hasAttemps())
            return getLastAttemp().getDistance();
        else
            return 0f;
    }

    public float getCurrentTime() {
        if (hasAttemps())
            return getLastAttemp().getTime();
        else
            return 0f;
    }

    public float getFullDistance() {
        float fullDistance = 0f;

        for (Attempt attempt : attempts) {
            fullDistance += attempt.getDistance();
        }

        return fullDistance;
    }

    public float getFullTime() {
        float fullTime = 0f;

        for (Attempt attempt : attempts) {
            fullTime += attempt.getTime();
        }

        return fullTime;
    }

    public int getFullDestroyed() {
        int fullDestroyed = 0;

        for (Attempt attempt : attempts) {
            if (attempt.isDestroyed())
                fullDestroyed++;
        }

        return fullDestroyed;
    }

}
