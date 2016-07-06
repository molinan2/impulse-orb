package com.jmolina.orb.data;

import com.badlogic.gdx.utils.GdxRuntimeException;
import com.jmolina.orb.var.Var;

import java.util.ArrayList;

/**
 * TODO
 *
 * Error: solo se estan considerando los tiempos de intentos FALLIDOS. Hay que considerar tanto los
 * fallidos como los exitoso (EXIT encontrada). Los que hay que dejar fuera son los reiniciados. Esto
 * es valido para el computo de los min, max y average (porque son "estando vivo").
 */
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

    public int fails() {
        int failsTotal = 0;

        for (Attempt attempt : attempts) {
            if (attempt.isFailed())
                failsTotal++;
        }

        return failsTotal;
    }

    public boolean isEmpty() {
        return attempts.size() == 0;
    }

    /**
     * Devuelve la lista de intentos que acabado en destruccion
     */
    private ArrayList<Attempt> findCompletedAttempts() {
        ArrayList<Attempt> completedAttempts = new ArrayList<Attempt>();

        for (Attempt attempt : attempts) {
            if (attempt.isFailed() || attempt.isSuccessful())
                completedAttempts.add(attempt);
        }

        return completedAttempts;
    }

    public int countCompletedAttempts() {
        return findCompletedAttempts().size();
    }

    /**
     * Sólo cuentan los tiempos mínimos si el orbe ha sido destruido (i.e. se descartan los tiempos
     * de intentos que se hayan reiniciado manualmente).
     *
     * Devuelve -1 si no encuentra un minTimeAlive (i.e. no hay intentos que hayan terminado a
     * causa de la destrucción del orbe).
     */
    public float minTimeAlive() throws GdxRuntimeException {
        if (isEmpty()) {
            throw new GdxRuntimeException(Var.EMPTY_LIST_ACCESS_EXCEPTION);
        }
        else {
            ArrayList<Attempt> completedAttempts = findCompletedAttempts();

            if (completedAttempts.size() == 0) {
                return -1f; // Cutre error code
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
    }

    public float maxTimeAlive() {
        float maxTime = 0f;

        for (Attempt attempt : attempts) {
            if (attempt.getTime() > maxTime)
                maxTime = attempt.getTime();
        }

        return maxTime;
    }

    /**
     * Sólo cuentan las distancias mínimas si el orbe ha sido destruido (i.e. se descartan las distancias
     * de intentos que se hayan reiniciado manualmente).
     *
     * Devuelve -1 si no encuentra una minDistanceAlive (i.e. no hay intentos que hayan terminado a
     * causa de la destrucción del orbe).
     */
    public float minDistanceAlive() throws GdxRuntimeException {
        if (isEmpty()) {
            throw new GdxRuntimeException(Var.EMPTY_LIST_ACCESS_EXCEPTION);
        }
        else {
            ArrayList<Attempt> completedAttempts = findCompletedAttempts();

            if (completedAttempts.size() == 0) {
                return -1f; // Cutre error code
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
    }

    public float maxDistanceAlive() {
        float maxDistance = 0f;

        for (Attempt attempt : attempts) {
            if (attempt.getDistance() > maxDistance)
                maxDistance = attempt.getTime();
        }

        return maxDistance;
    }

    /**
     * Devuelve -1 si no hay intentos fallidos
     */
    public float averageDistanceAlive() {
        if (isEmpty()) {
            throw new GdxRuntimeException(Var.EMPTY_LIST_ACCESS_EXCEPTION);
        }
        else {
            ArrayList<Attempt> completedAttempts = findCompletedAttempts();
            int count = completedAttempts.size();

            if (count == 0) {
                return -1f; // Cutre error code
            }
            else {
                float distance = 0f;

                for (Attempt attempt : findCompletedAttempts()) {
                    distance += attempt.getDistance();
                }

                return distance / (float) count;
            }
        }
    }

    /**
     * Devuelve -1 si no hay intentos fallidos
     */
    public float averageTimeAlive() {
        if (isEmpty()) {
            throw new GdxRuntimeException(Var.EMPTY_LIST_ACCESS_EXCEPTION);
        }
        else {
            ArrayList<Attempt> completedAttempts = findCompletedAttempts();
            int count = completedAttempts.size();

            if (count == 0) {
                return -1f; // Cutre error code
            }
            else {
                float time = 0f;

                for (Attempt attempt : findCompletedAttempts()) {
                    time += attempt.getTime();
                }

                return time / (float) count;
            }
        }
    }


}
