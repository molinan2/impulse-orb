package com.jmolina.orb.screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.jmolina.orb.var.Asset;
import com.jmolina.orb.data.Attempt;
import com.jmolina.orb.interfaces.SuperManager;
import com.jmolina.orb.managers.GameManager;
import com.jmolina.orb.managers.ScreenManager;
import com.jmolina.orb.var.Utils;
import com.jmolina.orb.var.Var;
import com.jmolina.orb.widgets.ui.BigText;
import com.jmolina.orb.widgets.ui.Heading;
import com.jmolina.orb.widgets.ui.Rating;
import com.jmolina.orb.widgets.ui.Star;
import com.jmolina.orb.widgets.ui.SuccessCover;
import com.jmolina.orb.widgets.ui.SuccessTitle;
import com.jmolina.orb.widgets.ui.MainButton;

public class Success extends BaseScreen {

    private SuccessTitle title;
    private SuccessCover cover;
    private Heading timeHeading, distanceHeading;
    private BigText time, distance;
    private MainButton button;
    private Star podium;
    private Rating rating;
    private int numericRating;

    public Success(SuperManager superManager, ScreenManager.Key thisKey, String label) {
        super(superManager);
        setPreviousScreen(ScreenManager.Key.LEVEL_SELECT);
        setThisKey(thisKey);

        Attempt attempt = getGameManager().getCachedAttempt();
        numericRating = getNumericRating(thisKey, attempt.getTime());

        title = new SuccessTitle(getAssetManager(), label);
        cover = new SuccessCover(getAssetManager(), getCoverTexture(thisKey));
        timeHeading = new Heading(getAssetManager(), "Time", Align.center, Heading.Weight.Regular, Var.COLOR_LILAC_DARK);
        distanceHeading = new Heading(getAssetManager(), "Distance", Align.center, Heading.Weight.Regular, Var.COLOR_LILAC_DARK);
        time = new BigText(getAssetManager(), Utils.formatTime(attempt.getTime()));
        distance = new BigText(getAssetManager(), Utils.formatDistance(attempt.getDistance()));

        button = new MainButton(getAssetManager(), "BACK", MainButton.Type.DEFAULT);
        button.addListener(new ClickListener(){
            @Override
            public void clicked (InputEvent event, float x, float y) {
                button.clickEffect();
                getGameManager().play(GameManager.Fx.Back);
                switchToScreen(ScreenManager.Key.LEVEL_SELECT, Hierarchy.HIGHER);
            }
        });

        podium = new Star(getAssetManager(), getGameManager().getCachedRank());
        rating = new Rating(getAssetManager(), numericRating);

        title.setPositionGrid(1, 16.5f);
        cover.setPositionGrid(1, 12f);
        podium.setPositionGrid(7.65f, 12.25f);
        timeHeading.setPositionGrid(1, 10.5f);
        time.setPositionGrid(1, 9f);
        distanceHeading.setPositionGrid(1, 7.5f);
        distance.setPositionGrid(1, 6f);
        rating.setPositionGrid(1, 3.5f);
        button.setPositionGrid(2, 1f);

        addMainActor(title);
        addMainActor(cover);
        addMainActor(podium);
        addMainActor(timeHeading);
        addMainActor(time);
        addMainActor(distanceHeading);
        addMainActor(distance);
        addMainActor(rating);
        addMainActor(button);
    }

    @Override
    public void show() {
        super.show();
        getGameManager().play(GameManager.Track.Success);

        if (numericRating == GameManager.RATING_DEVELOPER)
            getGameManager().unlockFastFuriousAchievement(getThisKey());
    }

    /**
     * Obtiene el rating numérico
     *
     * @param key
     * @param time
     * @return
     */
    private int getNumericRating(ScreenManager.Key key, float time) {
        switch (key) {
            case SUCCESS_1: return getGameManager().getNumericRating(1, time);
            case SUCCESS_2: return getGameManager().getNumericRating(2, time);
            case SUCCESS_3: return getGameManager().getNumericRating(3, time);
            case SUCCESS_4: return getGameManager().getNumericRating(4, time);
            case SUCCESS_5: return getGameManager().getNumericRating(5, time);
            default: return GameManager.RATING_UNRATED;
        }
    }

    private Texture getCoverTexture(ScreenManager.Key thisKey) {
        switch (thisKey) {
            case SUCCESS_1: return getAsset(Asset.UI_SUCCESS_COVER_1, Texture.class);
            case SUCCESS_2: return getAsset(Asset.UI_SUCCESS_COVER_2, Texture.class);
            case SUCCESS_3: return getAsset(Asset.UI_SUCCESS_COVER_3, Texture.class);
            case SUCCESS_4: return getAsset(Asset.UI_SUCCESS_COVER_4, Texture.class);
            case SUCCESS_5: return getAsset(Asset.UI_SUCCESS_COVER_5, Texture.class);
            default: return getAsset(Asset.UI_SUCCESS_COVER_1, Texture.class);
        }
    }

}
