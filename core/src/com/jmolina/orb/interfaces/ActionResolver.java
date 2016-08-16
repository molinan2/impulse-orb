package com.jmolina.orb.interfaces;

/**
 *
 */
public interface ActionResolver {

    public boolean getSignedIn();

    public void login();

    public void submitScore(int score);

    public void unlockAchievement(String achievementId);

    public void getLeaderboard();

    public void getAchievements();

}