package com.jmolina.orb.interfaces;

/**
 * Define la interfaz para gestionar los servicios de Google Play Games
 */
public interface PlayServices {

    /** Lista de logros */
    public enum Achievement {
        KnowHow, TheRealDeal, BecomingAnExpert, AHeroWasBorn, OneAboveAll,
        FastFurious1, FastFurious2, FastFurious3, FastFurious4, FastFurious5,
        Kenny, Robocop, Hyperdrive, ItsOver9000, EasterHunter
    }

    /** Lista de clasificaciones */
    public enum Leaderboard {
        Leaderboard1, Leaderboard2, Leaderboard3, Leaderboard4, Leaderboard5
    }

    /**
     * Realiza un intento de conexión a Play Games.
     */
    public void signIn();

    /**
     * Realiza un intento de desconexión a Play Games. Sin estar conectado, no se podrán ejecutar
     * ninguna de las llamadas a Play Games.
     */
    public void signOut();

    /**
     * Soporte preliminar para puntuar el juego.
     */
    public void rateGame();

    /**
     * Desbloquea un logro. Si se pide desbloquear un logro ya desbloqueado, no ocurre nada.
     *
     * @param achievement Logro
     */
    public void unlockAchievement(Achievement achievement);

    /**
     * Sube la puntuación (el tiempo) a Play Games.
     *
     * @param leaderboard Tabla de clasificación correspondiente a un nivel
     * @param score Puntuación (el tiempo conseguido en un nivel)
     */
    public void submitScore(Leaderboard leaderboard, long score);

    /**
     * Muestra un logro en un overlay de Play Games.
     */
    public void showAchievement();

    /**
     * Muestra la tabla de puntuaciones de una clasificación.
     *
     * @param leaderboard Tabla de clasificación correspondiente a un nivel
     */
    public void showScore(Leaderboard leaderboard);

    /**
     * Comprueba si el usuario está logueado en Play Games
     */
    public boolean isSignedIn();

}