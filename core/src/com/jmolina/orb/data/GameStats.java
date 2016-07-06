package com.jmolina.orb.data;

import java.util.ArrayList;

public class GameStats {

    private int destructions;
    private ArrayList<Attempt> attempts;

    public GameStats() {
        destructions = 0;
        attempts = new ArrayList<Attempt>();
    }

    public void addDestruction() {
        destructions++;
    }

    public void newTry() {
        Attempt attempt = new Attempt();
        attempts.add(attempt);
    }

    private boolean hasAttemps () {
        if (attempts.size() != 0) return true;
        else return false;
    }

    public void addTime(float time) {
        if (hasAttemps()) {
            attempts.get(attempts.size()-1).addTime(time);
        }
    }

    public void addDistance(float distance) {
        if (hasAttemps()) {
            attempts.get(attempts.size()-1).addDistance(distance);
        }
    }

    public int getDestructions () {
        return destructions;
    }

    public ArrayList<Attempt> getAttems () {
        return attempts;
    }

}
