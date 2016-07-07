package com.jmolina.orb.data;

import java.util.ArrayList;

/**
 * ATTEMPT: Un Attempt se considera COMPLETO cuando el Orb alcanza la salida o es destruido.
 */
public class GameStats {

    public static final float ERROR = -1.0f;

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

    public void setFailed(boolean failed) {
        if (hasAttemps()) {
            getLastAttemp().setFailed(failed);
        }
    }

    public void setSuccessful(boolean successful) {
        if (hasAttemps()) {
            getLastAttemp().setSuccessful(successful);
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

    public float fullDistance() {
        float fullDistance = 0f;

        for (Attempt attempt : attempts) {
            fullDistance += attempt.getDistance();
        }

        return fullDistance;
    }

    public float fullTime() {
        float fullTime = 0f;

        for (Attempt attempt : attempts) {
            fullTime += attempt.getTime();
        }

        return fullTime;
    }

    /**
     * Numero de intentos fallidos (orbe destruido)
     */
    public int fails() {
        int fails = 0;

        for (Attempt attempt : attempts) {
            if (attempt.isFailed())
                fails++;
        }

        return fails;
    }

    /**
     * Numero de intentos exitosos (salida hallada)
     */
    public int successes() {
        int succeeses = 0;

        for (Attempt attempt : attempts) {
            if (attempt.isSuccessful())
                succeeses++;
        }

        return succeeses;
    }

    public boolean isEmpty() {
        return attempts.size() == 0;
    }

    /**
     * Devuelve la lista de intentos completos
     */
    public ArrayList<Attempt> completedAttempts() {
        ArrayList<Attempt> completedAttempts = new ArrayList<Attempt>();

        for (Attempt attempt : attempts) {
            if (attempt.isCompleted())
                completedAttempts.add(attempt);
        }

        return completedAttempts;
    }

    /**
     * Solo contabiliza intentos completos.
     * Devuelve -1 si no hay intentos completos.
     */
    public float minTimeAlive() {
        ArrayList<Attempt> completedAttempts = completedAttempts();

        if (completedAttempts.size() == 0) {
            return ERROR;
        }
        else {
            float minTimeAlive = completedAttempts.get(0).getTime();

            for (Attempt attempt : completedAttempts) {
                if (attempt.getTime() < minTimeAlive)
                    minTimeAlive = attempt.getTime();
            }

            return minTimeAlive;
        }
    }

    /**
     * Solo contabiliza intentos completos.
     * Devuelve -1 si no hay intentos completos.
     */
    public float minDistanceAlive() {
        ArrayList<Attempt> completedAttempts = completedAttempts();

        if (completedAttempts.size() == 0) {
            return ERROR;
        }
        else {
            float minDistanceAlive = completedAttempts.get(0).getDistance();

            for (Attempt attempt : completedAttempts) {
                if (attempt.getDistance() < minDistanceAlive)
                    minDistanceAlive = attempt.getDistance();
            }

            return minDistanceAlive;
        }
    }

    /**
     * Solo contabiliza intentos completos.
     */
    public float maxTimeAlive() {
        ArrayList<Attempt> completedAttempts = completedAttempts();
        float maxTime = 0f;

        for (Attempt attempt : completedAttempts) {
            if (attempt.getTime() > maxTime)
                maxTime = attempt.getTime();
        }

        return maxTime;
    }

    /**
     * Solo contabiliza intentos completos.
     */
    public float maxDistanceAlive() {
        ArrayList<Attempt> completedAttempts = completedAttempts();
        float maxDistance = 0f;

        for (Attempt attempt : completedAttempts) {
            if (attempt.getDistance() > maxDistance)
                maxDistance = attempt.getDistance();
        }

        return maxDistance;
    }

    /**
     * Solo contabiliza intentos completos.
     * Devuelve -1 si no hay intentos completos
     */
    public float averageTimeAlive() {
        ArrayList<Attempt> completedAttempts = completedAttempts();
        int count = completedAttempts.size();

        if (count == 0) {
            return ERROR;
        }
        else {
            float time = 0f;

            for (Attempt attempt : completedAttempts())
                time += attempt.getTime();

            return time / (float) count;
        }
    }

    /**
     * Solo contabiliza intentos completos.
     * Devuelve -1 si no hay intentos completos
     */
    public float averageDistanceAlive() {
        ArrayList<Attempt> completedAttempts = completedAttempts();
        int count = completedAttempts.size();

        if (count == 0) {
            return ERROR;
        }
        else {
            float distance = 0f;

            for (Attempt attempt : completedAttempts())
                distance += attempt.getDistance();

            return distance / (float) count;
        }
    }

}
