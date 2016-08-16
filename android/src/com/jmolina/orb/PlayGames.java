package com.jmolina.orb;


import com.jmolina.orb.interfaces.ActionResolver;

public class PlayGames implements ActionResolver {

    public PlayGames() {
        // GoogleApiClient.Builder builder = null;
    }

    @Override
    public boolean getSignedIn() {
        return false;
    }

    @Override
    public void login() {

    }

    @Override
    public void submitScore(int score) {

    }

    @Override
    public void unlockAchievement(String achievementId) {
        // Games.Achievements.unlock(getApiClient(), achievementId);
    }

    @Override
    public void getLeaderboard() {

    }

    @Override
    public void getAchievements() {

    }

}
