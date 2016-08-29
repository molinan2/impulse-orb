package com.jmolina.orb.interfaces;

/**
 *
 */
public interface PlayServices {

    public enum Achievement {
        KnowHow, TheRealDeal, BecomingAnExpert, AHeroWasBorn, OneAboveAll,
        FastFurious1, FastFurious2, FastFurious3, FastFurious4, FastFurious5,
        Kenny, Robocop, Hyperdrive, ItsOver9000, EasterHunter
    }

    public enum Leaderboard {
        Level1, Level2, Level3, Level4, Level5
    }

    public void signIn();

    public void signOut();

    public void rateGame();

    /**
     * Desbloquea un logro. Si se pide desbloquear un logro ya desbloqueado, no ocurre nada.
     *
     * @param achievement Logro
     */
    public void unlockAchievement(Achievement achievement);

    public void submitScore(Leaderboard leaderboard, long score);

    public void showAchievement();

    public void showScore(Leaderboard leaderboard);

    public boolean isSignedIn();

}