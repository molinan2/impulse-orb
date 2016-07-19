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
import com.jmolina.orb.widgets.BigText;
import com.jmolina.orb.widgets.Heading;
import com.jmolina.orb.widgets.SuccessCover;
import com.jmolina.orb.widgets.LevelTitle;
import com.jmolina.orb.widgets.MainButton;

import java.text.DecimalFormat;

/**
 * TODO
 * Texto de "You have unlocked NEXT LEVEL"
 */
public class LevelSuccess extends BaseScreen {

    private LevelTitle title;
    private SuccessCover cover;
    private Heading timeHeading;
    private Heading distanceHeading;
    private BigText time;
    private BigText distance;
    private MainButton button;

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
        this.distance = new BigText(getAssetManager(), formatDistance(attempt.getDistance()));

        this.button = new MainButton(getAssetManager(), "BACK", MainButton.Type.Default);
        button.addListener(new ClickListener(){
            @Override
            public void clicked (InputEvent event, float x, float y) {
                button.clickEffect();
                getGameManager().play(GameManager.Fx.Back);
                // switchToScreen(getPreviousScreen(), Hierarchy.HIGHER);
                switchToScreen(ScreenManager.Key.LEVEL_SELECT, Hierarchy.HIGHER);
            }
        });

        this.title.setPositionGrid(1, 16.5f);
        this.cover.setPositionGrid(1, 12f);
        this.timeHeading.setPositionGrid(1, 10f);
        this.time.setPositionGrid(1, 8.5f);
        this.distanceHeading.setPositionGrid(1, 7f);
        this.distance.setPositionGrid(1, 5.5f);
        this.button.setPositionGrid(2, 1f);

        addMainActor(this.title);
        addMainActor(this.cover);
        addMainActor(this.timeHeading);
        addMainActor(this.distanceHeading);
        addMainActor(this.time);
        addMainActor(this.distance);
        addMainActor(this.button);
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

    private String formatDistance(float distance) {
        DecimalFormat df = new DecimalFormat("###.##");
        String formatedDistance = "";

        formatedDistance = df.format(distance) + " m";

        return formatedDistance;
    }

    @Override
    public void show() {
        super.show();
        getGameManager().play(GameManager.Track.Success);
    }

    @Override
    public void hide() {
        super.hide();
        getGameManager().play(GameManager.Track.Menu);
    }

}
