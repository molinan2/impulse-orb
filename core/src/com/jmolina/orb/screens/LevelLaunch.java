package com.jmolina.orb.screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.jmolina.orb.var.Asset;
import com.jmolina.orb.interfaces.SuperManager;
import com.jmolina.orb.managers.GameManager;
import com.jmolina.orb.managers.ScreenManager;
import com.jmolina.orb.widgets.ui.Ladder;
import com.jmolina.orb.widgets.ui.LaunchCover;
import com.jmolina.orb.widgets.ui.LevelTitle;
import com.jmolina.orb.widgets.ui.MainButton;

import static com.jmolina.orb.managers.ScreenManager.Key.*;

public class LevelLaunch extends Menu {

    private LevelTitle title;
    private LaunchCover cover;
    private MainButton mainButton;
    private Ladder ladderPersonal;

    /**
     * Constructor
     *
     * @param superManager
     * @param levelLaunched
     * @param title
     */
    public LevelLaunch(SuperManager superManager, final ScreenManager.Key levelLaunched, String title) {
        super(superManager);

        setPreviousScreen(LEVEL_SELECT);
        setTitle(title);

        this.title = new LevelTitle(getAssetManager(), title);
        this.cover = new LaunchCover(getAssetManager(), getAsset(Asset.UI_LAUNCH_COVER, Texture.class));
        this.mainButton = new MainButton(getAssetManager(), "GO!", MainButton.Type.Play);
        ladderPersonal = new Ladder(getAssetManager(), getPrefsManager(), levelLaunched, "Your best times");

        add(this.title);
        add(this.cover);
        add(this.mainButton, 1f, 8f);
        add(this.ladderPersonal);

        mainButton.addListener(new ClickListener(){
            @Override
            public void clicked (InputEvent event, float x, float y) {
                mainButton.clickEffect();
                getGameManager().play(GameManager.Fx.Button);
                switchToScreen(levelLaunched, Hierarchy.LOWER);
            }
        });
    }


    @Override
    public void dispose() {
        title.dispose();
        cover.dispose();
        mainButton.dispose();
        ladderPersonal.dispose();
        super.dispose();
    }

}
