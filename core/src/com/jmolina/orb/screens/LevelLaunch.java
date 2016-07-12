package com.jmolina.orb.screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.jmolina.orb.assets.Asset;
import com.jmolina.orb.interfaces.SuperManager;
import com.jmolina.orb.managers.GameManager;
import com.jmolina.orb.managers.ScreenManager;
import com.jmolina.orb.widgets.Ladder;
import com.jmolina.orb.widgets.LaunchCover;
import com.jmolina.orb.widgets.LevelTitle;
import com.jmolina.orb.widgets.MainButton;

import static com.jmolina.orb.managers.ScreenManager.Key.*;

public class LevelLaunch extends Menu {

    private LevelTitle title;
    private LaunchCover cover;
    private MainButton mainButton;
    private Ladder ladderPersonal;
    private Ladder ladderOnline;

    public LevelLaunch(SuperManager superManager, ScreenManager.Key key, final ScreenManager.Key nextScreen, String title) {
        super(superManager, key);

        setPreviousScreen(LEVEL_SELECT);
        setTitle(nextScreen.toString().replace("_", " "));

        this.title = new LevelTitle(getAssetManager(), title);
        this.cover = new LaunchCover(getAssetManager(), getAsset(Asset.UI_LAUNCH_COVER, Texture.class));
        this.mainButton = new MainButton(getAssetManager(), "GO!", MainButton.Type.Play);
        ladderPersonal = new Ladder(getAssetManager(), getPrefsManager(), nextScreen, "Personal best");
        ladderOnline = new Ladder(getAssetManager(), getPrefsManager(), nextScreen, "Online ladder");

        add(this.title);
        add(this.cover);
        add(this.mainButton, 1f, 8f);
        add(this.ladderPersonal);
        // add(this.ladderOnline);

        mainButton.addListener(new ClickListener(){
            @Override
            public void clicked (InputEvent event, float x, float y) {
                mainButton.clickEffect();
                getGameManager().play(GameManager.Fx.Button);
                switchToScreen(nextScreen, Hierarchy.LOWER);
            }
        });
    }


    @Override
    public void dispose() {
        title.dispose();
        cover.dispose();
        mainButton.dispose();
        ladderOnline.dispose();
        ladderPersonal.dispose();
        super.dispose();
    }

}
