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
import com.jmolina.orb.utils.Utils;
import com.jmolina.orb.var.Var;
import com.jmolina.orb.widgets.ui.BigText;
import com.jmolina.orb.widgets.ui.Heading;
import com.jmolina.orb.widgets.ui.Rating;
import com.jmolina.orb.widgets.ui.Star;
import com.jmolina.orb.widgets.ui.SuccessCover;
import com.jmolina.orb.widgets.ui.LevelTitle;
import com.jmolina.orb.widgets.ui.MainButton;

import java.util.ArrayList;

public class LevelSuccess extends BaseScreen {

    private LevelTitle title;
    private SuccessCover cover;
    private Heading timeHeading;
    private Heading distanceHeading;
    private BigText time;
    private BigText distance;
    private MainButton button;
    private Star star;
    private Rating rating;

    public LevelSuccess(SuperManager superManager, ScreenManager.Key key, ScreenManager.Key previousKey, String title) {
        super(superManager, key);
        setPreviousScreen(previousKey);

        Attempt attempt = getGameManager().getCachedAttempt();

        this.title = new LevelTitle(getAssetManager(), title);
        Texture successCover = getAsset(Asset.UI_SUCCESS_COVER_01, Texture.class);
        this.cover = new SuccessCover(getAssetManager(), successCover);
        this.timeHeading = new Heading(getAssetManager(), "Time", Align.center, Heading.Weight.Regular, Var.COLOR_BLUE);
        this.distanceHeading = new Heading(getAssetManager(), "Distance", Align.center, Heading.Weight.Regular, Var.COLOR_BLUE);

        this.time = new BigText(getAssetManager(), Utils.formatTime(attempt.getTime()));
        this.distance = new BigText(getAssetManager(), Utils.formatDistance(attempt.getDistance()));

        this.button = new MainButton(getAssetManager(), "BACK", MainButton.Type.Default);
        button.addListener(new ClickListener(){
            @Override
            public void clicked (InputEvent event, float x, float y) {
                button.clickEffect();
                getGameManager().play(GameManager.Fx.Back);
                switchToScreen(ScreenManager.Key.LEVEL_SELECT, Hierarchy.HIGHER);
            }
        });

        this.star = new Star(getAssetManager(), getGameManager().getCachedRank());
        this.rating = new Rating(getAssetManager(), numericRating(key, attempt.getTime()));

        this.title.setPositionGrid(1, 16.5f);
        this.cover.setPositionGrid(1, 12f);
        this.timeHeading.setPositionGrid(1, 10.5f);
        this.time.setPositionGrid(1, 9f);
        this.distanceHeading.setPositionGrid(1, 7.5f);
        this.distance.setPositionGrid(1, 6f);
        this.button.setPositionGrid(2, 1f);
        this.star.setPositionGrid(7.65f, 12.25f);
        this.rating.setPositionGrid(1, 3.5f);

        addMainActor(this.title);
        addMainActor(this.cover);
        addMainActor(this.timeHeading);
        addMainActor(this.distanceHeading);
        addMainActor(this.time);
        addMainActor(this.distance);
        addMainActor(this.button);
        addMainActor(this.star);
        addMainActor(this.rating);
    }

    @Override
    public void dispose() {
        title.dispose();
        cover.dispose();
        timeHeading.dispose();
        time.dispose();
        distanceHeading.dispose();
        distance.dispose();
        button.dispose();
        super.dispose();
    }

    @Override
    public void show() {
        super.show();
        getGameManager().play(GameManager.Track.Success);
    }

    /**
     * Obtiene el rating numérico
     *
     * @param key
     * @param time
     * @return
     */
    private int numericRating(ScreenManager.Key key, float time) {
        switch (key) {
            case LEVEL_SUCCESS_1: return numericRatingByLevel(1, time);
            case LEVEL_SUCCESS_2: return numericRatingByLevel(2, time);
            case LEVEL_SUCCESS_3: return numericRatingByLevel(3, time);
            case LEVEL_SUCCESS_4: return numericRatingByLevel(4, time);
            case LEVEL_SUCCESS_5: return numericRatingByLevel(5, time);
            default: return 0;
        }
    }

    /**
     * Obtiene el rating numérico
     *
     * @param levelIndex
     * @param time
     * @return
     */
    private int numericRatingByLevel(int levelIndex, float time) {
        ArrayList<Float> levelTimes = getGameManager().getTimes(levelIndex);

        if (time < levelTimes.get(0)) return 4;
        else if (time < levelTimes.get(1)) return 3;
        else if (time < levelTimes.get(2)) return 2;
        else if (time < levelTimes.get(3)) return 1;
        else return 0;
    }

}
