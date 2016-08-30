package com.jmolina.orb.data;

import java.util.ArrayList;

/**
 * Estadisticas de una partida. Comprende todos los intentos realizados en la partida. Una partida
 * empieza cuando se inicia un nivel y termina cuando se sale del nivel.
 */
public class GameStats {

    public static final float ERROR = -1.0f;

    /** Intentos realizados en la partida */
    private ArrayList<Attempt> attempts;

    /**
     * Constructor
     */
    public GameStats() {
        attempts = new ArrayList<Attempt>();
    }

    /**
     * Indica si la partida tiene algun intento
     */
    private boolean hasAttemps () {
        return attempts.size() != 0;
    }

    /**
     * Indica si no hay intentos creados
     */
    public boolean isEmpty() {
        return !hasAttemps();
    }

    /**
     * Inicia un nuevo intento
     */
    public void newTry() {
        Attempt attempt = new Attempt();
        attempts.add(attempt);
    }

    /**
     * Añadie al intento actual un tiempo transcurrido
     *
     * @param time Incremento de tiempo
     */
    public void addTime(float time) {
        if (hasAttemps()) {
            getCurrentAttempt().addTime(time);
        }
    }

    /**
     * Añade al intento actual una distancia recorrida
     *
     * @param distance Incremento de distancia
     */
    public void addDistance(float distance) {
        if (hasAttemps()) {
            getCurrentAttempt().addDistance(distance);
        }
    }

    /**
     * Fija el intento actual como fallido (o no)
     *
     * @param failed Si es fallido
     */
    public void setFailed(boolean failed) {
        if (hasAttemps()) {
            getCurrentAttempt().setFailed(failed);
        }
    }

    /**
     * Fija el intento actual como exitoso (o no)
     *
     * @param successful Si es exitoso
     */
    public void setSuccessfull(boolean successful) {
        if (hasAttemps()) {
            getCurrentAttempt().setSuccessful(successful);
        }
    }

    /**
     * Devuelve la distancia recorrida en el intento actual
     */
    public float getCurrentDistance() {
        if (hasAttemps())
            return getCurrentAttempt().getDistance();
        else
            return 0f;
    }

    /**
     * Devuelve el tiempo invertido en el intento actual
     */
    public float getCurrentTime() {
        if (hasAttemps())
            return getCurrentAttempt().getTime();
        else
            return 0f;
    }

    /**
     * Devuelve la distancia total recorrida entre todos los intentos
     */
    public float fullDistance() {
        float fullDistance = 0f;

        for (Attempt attempt : attempts) {
            fullDistance += attempt.getDistance();
        }

        return fullDistance;
    }

    /**
     * Devuelve el tiempo total invertido en todos los intentos
     */
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
     * Numero de intentos exitosos (salida alcanzada)
     */
    public int successes() {
        int succeeses = 0;

        for (Attempt attempt : attempts) {
            if (attempt.isSuccessful())
                succeeses++;
        }

        return succeeses;
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

    /**
     * Devuelve el intento actual
     */
    public Attempt getCurrentAttempt() {
        if (!isEmpty())
            return attempts.get(attempts.size()-1);
        else
            return null;
    }

}
