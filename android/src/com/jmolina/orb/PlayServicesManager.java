package com.jmolina.orb;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

import com.badlogic.gdx.Gdx;
import com.google.android.gms.games.Games;
import com.google.example.games.basegameutils.GameHelper;
import com.jmolina.orb.interfaces.PlayServices;

public class PlayServicesManager implements PlayServices {

    private GameHelper gameHelper;
    private Activity activity;
    private final static int requestCode = 1;

    public PlayServicesManager(Activity activity, GameHelper gameHelper) {
        this.gameHelper = gameHelper;
        this.activity = activity;
    }

    @Override
    public void signIn() {
        try {
            activity.runOnUiThread(new Runnable()
            {
                @Override
                public void run()
                {
                    gameHelper.beginUserInitiatedSignIn();
                }
            });
        }
        catch (Exception e) {
            Gdx.app.log("MainActivity", "Log in failed: " + e.getMessage() + ".");
        }
    }

    @Override
    public void signOut() {
        try {
            activity.runOnUiThread(new Runnable()
            {
                @Override
                public void run()
                {
                    gameHelper.signOut();
                }
            });
        }
        catch (Exception e) {
            Gdx.app.log("MainActivity", "Log out failed: " + e.getMessage() + ".");
        }
    }

    @Override
    public void rateGame() {
        String str = "Your PlayStore Link";
        activity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(str)));
    }

    @Override
    public void unlockAchievement(Achievement achievement) {
        if (isSignedIn()) {
            switch (achievement) {
                case KnowHow:
                    Games.Achievements.unlock(
                            gameHelper.getApiClient(),
                            activity.getString(R.string.achievement_knowhow)
                    );
                    break;

                case TheRealDeal:
                    Games.Achievements.unlock(
                            gameHelper.getApiClient(),
                            activity.getString(R.string.achievement_the_real_deal)
                    );
                    break;

                case BecomingAnExpert:
                    Games.Achievements.unlock(
                            gameHelper.getApiClient(),
                            activity.getString(R.string.achievement_becoming_an_expert)
                    );
                    break;

                case AHeroWasBorn:
                    Games.Achievements.unlock(
                            gameHelper.getApiClient(),
                            activity.getString(R.string.achievement_a_hero_was_born)
                    );
                    break;

                case OneAboveAll:
                    Games.Achievements.unlock(
                            gameHelper.getApiClient(),
                            activity.getString(R.string.achievement_oneaboveall)
                    );
                    break;

                case FastFurious1:
                    Games.Achievements.unlock(
                            gameHelper.getApiClient(),
                            activity.getString(R.string.achievement_fast__furious_1)
                    );
                    break;

                case FastFurious2:
                    Games.Achievements.unlock(
                            gameHelper.getApiClient(),
                            activity.getString(R.string.achievement_fast__furious_2)
                    );
                    break;

                case FastFurious3:
                    Games.Achievements.unlock(
                            gameHelper.getApiClient(),
                            activity.getString(R.string.achievement_fast__furious_3)
                    );
                    break;

                case FastFurious4:
                    Games.Achievements.unlock(
                            gameHelper.getApiClient(),
                            activity.getString(R.string.achievement_fast__furious_4)
                    );
                    break;

                case FastFurious5:
                    Games.Achievements.unlock(
                            gameHelper.getApiClient(),
                            activity.getString(R.string.achievement_fast__furious_5)
                    );
                    break;

                case Kenny:
                    Games.Achievements.unlock(
                            gameHelper.getApiClient(),
                            activity.getString(R.string.achievement_kenny)
                    );
                    break;

                case Robocop:
                    Games.Achievements.unlock(
                            gameHelper.getApiClient(),
                            activity.getString(R.string.achievement_robocop)
                    );
                    break;

                case Hyperdrive:
                    Games.Achievements.unlock(
                            gameHelper.getApiClient(),
                            activity.getString(R.string.achievement_hyperdrive)
                    );
                    break;

                case ItsOver9000:
                    Games.Achievements.unlock(
                            gameHelper.getApiClient(),
                            activity.getString(R.string.achievement_its_over_9000)
                    );
                    break;

                default:
            }
        }
    }

    @Override
    public void submitScore(Leaderboard leaderboard, long score) {
        if (isSignedIn()) {
            switch (leaderboard) {
                case Level1:
                    Games.Leaderboards.submitScore(
                            gameHelper.getApiClient(),
                            activity.getString(R.string.leaderboard_level_1_basics),
                            score
                    );
                    break;
                case Level2:
                    Games.Leaderboards.submitScore(
                            gameHelper.getApiClient(),
                            activity.getString(R.string.leaderboard_level_2_advance),
                            score
                    );
                    break;
                case Level3:
                    Games.Leaderboards.submitScore(
                            gameHelper.getApiClient(),
                            activity.getString(R.string.leaderboard_level_3_expert),
                            score
                    );
                    break;
                case Level4:
                    Games.Leaderboards.submitScore(
                            gameHelper.getApiClient(),
                            activity.getString(R.string.leaderboard_level_4_hero),
                            score
                    );
                    break;
                case Level5:
                    Games.Leaderboards.submitScore(
                            gameHelper.getApiClient(),
                            activity.getString(R.string.leaderboard_level_5_god),
                            score
                    );
                    break;
                default:
            }
        }
    }

    @Override
    public void showAchievement() {
        if (isSignedIn()) {
            activity.startActivityForResult(
                    Games.Achievements.getAchievementsIntent(gameHelper.getApiClient()),
                    requestCode
            );
        }
        else {
            signIn();
        }
    }

    @Override
    public void showScore(Leaderboard leaderboard) {
        if (isSignedIn()) {
            switch (leaderboard) {
                case Level1:
                    activity.startActivityForResult(
                            Games.Leaderboards.getLeaderboardIntent(gameHelper.getApiClient(),
                                    activity.getString(R.string.leaderboard_level_1_basics)),
                            requestCode
                    );
                    break;

                case Level2:
                    activity.startActivityForResult(
                            Games.Leaderboards.getLeaderboardIntent(gameHelper.getApiClient(),
                                    activity.getString(R.string.leaderboard_level_2_advance)),
                            requestCode
                    );
                    break;

                case Level3:
                    activity.startActivityForResult(
                            Games.Leaderboards.getLeaderboardIntent(gameHelper.getApiClient(),
                                    activity.getString(R.string.leaderboard_level_3_expert)),
                            requestCode
                    );
                    break;

                case Level4:
                    activity.startActivityForResult(
                            Games.Leaderboards.getLeaderboardIntent(gameHelper.getApiClient(),
                                    activity.getString(R.string.leaderboard_level_4_hero)),
                            requestCode
                    );
                    break;

                case Level5:
                    activity.startActivityForResult(
                            Games.Leaderboards.getLeaderboardIntent(gameHelper.getApiClient(),
                                    activity.getString(R.string.leaderboard_level_5_god)),
                            requestCode
                    );
                    break;

                default:
            }


        }
        else {
            signIn();
        }
    }

    @Override
    public boolean isSignedIn() {
        return gameHelper.isSignedIn();
    }

}
