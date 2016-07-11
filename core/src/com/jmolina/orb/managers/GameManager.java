package com.jmolina.orb.managers;

import com.jmolina.orb.data.Attempt;
import com.jmolina.orb.screens.Level;

public class GameManager {

    // interface: pause, resume, restart, destroy, increaseGauge gauge, decrease gauge?
    // data: gauge,

    private Level currentLevel;
    private Attempt lastSuccessfulAttempt;

    public GameManager () {
        lastSuccessfulAttempt = new Attempt();
        lastSuccessfulAttempt.setSuccessful(true);
    }

    public void setCurrentLevel (Level screen) {
        currentLevel = screen;
    }

    public void setLastSuccessfulAttempt (Attempt attempt) {
        if (attempt != null) {
            lastSuccessfulAttempt.setDistance(attempt.getDistance());
            lastSuccessfulAttempt.setTime(attempt.getTime());
        }
    }

    public Attempt getLastSuccessfulAttempt () {
        return lastSuccessfulAttempt;
    }

}
