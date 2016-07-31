package com.jmolina.orb.screens;

import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Texture;
import com.jmolina.orb.interfaces.SuperManager;
import com.jmolina.orb.interfaces.Visitor;
import com.jmolina.orb.managers.GameManager;
import com.jmolina.orb.managers.PrefsManager;
import com.jmolina.orb.managers.ScreenManager;
import com.jmolina.orb.var.Asset;
import com.jmolina.orb.utils.Utils;
import com.jmolina.orb.widgets.ui.Card;

import static com.jmolina.orb.managers.ScreenManager.Key.*;
import static com.jmolina.orb.screens.BaseScreen.Hierarchy.*;

public class LevelSelect extends Menu {

    private Card level1, level2, level3, level4, level5, levelTest1, levelTest2;

    public LevelSelect(SuperManager superManager, ScreenManager.Key key) {
        super(superManager, key);

        setPreviousScreen(ScreenManager.Key.MAIN);
        setTitle("SELECT");

        Texture cover = getAsset(Asset.UI_CARD_COVER, Texture.class);

        level1 = new Card(getAssetManager(), "BASIC", getFormattedBestTime(LEVEL_1), "--", cover, true);
        level2 = new Card(getAssetManager(), "ADVANCED", getFormattedBestTime(LEVEL_2), "--", cover, true);
        level3 = new Card(getAssetManager(), "EXPERT", getFormattedBestTime(LEVEL_3), "--", cover, true);
        level4 = new Card(getAssetManager(), "HERO", getFormattedBestTime(LEVEL_4), "--", cover, true);
        level5 = new Card(getAssetManager(), "GOD", getFormattedBestTime(LEVEL_5), "--", cover, true);
        levelTest1 = new Card(getAssetManager(), "TEST1", getFormattedBestTime(LEVEL_T1), "--", cover, true);
        levelTest2 = new Card(getAssetManager(), "TEST2", getFormattedBestTime(LEVEL_T2), "--", cover, true);

        level1.setOnClickOperation(visitor(LEVEL_LAUNCH_1));
        level2.setOnClickOperation(visitor(LEVEL_LAUNCH_2));
        level3.setOnClickOperation(visitor(LEVEL_LAUNCH_3));
        level4.setOnClickOperation(visitor(LEVEL_LAUNCH_4));
        level5.setOnClickOperation(visitor(LEVEL_LAUNCH_5));
        levelTest1.setOnClickOperation(visitor(LEVEL_LAUNCH_T1));
        levelTest2.setOnClickOperation(visitor(LEVEL_LAUNCH_T2));

        add(level1);
        add(level2);
        add(level3);
        add(level4);
        add(level5);
        add(levelTest1);
        add(levelTest2);
    }

    private Visitor visitor(final ScreenManager.Key screen) {
        return new Visitor() {
            @Override
            public void run() {
                getGameManager().play(GameManager.Fx.Button);
                switchToScreen(screen, LOWER);
            }
        };
    }

    @Override
    public void dispose() {
        level1.dispose();
        level2.dispose();
        level3.dispose();
        level4.dispose();
        level5.dispose();
        levelTest1.dispose();
        levelTest2.dispose();
        super.dispose();
    }

    private String getFormattedBestTime(ScreenManager.Key key) {
        float time = getBestTime(key);

        if (time > 0)
            return Utils.formatTime(getBestTime(key));
        else
            return "--";
    }

    private Float getBestTime(ScreenManager.Key key) {
        Preferences prefs = getPrefsManager().getPrefs();

        switch (key) {
            case LEVEL_1: return prefs.getFloat(PrefsManager.LADDER_1_1);
            case LEVEL_2: return prefs.getFloat(PrefsManager.LADDER_2_1);
            case LEVEL_3: return prefs.getFloat(PrefsManager.LADDER_3_1);
            case LEVEL_4: return prefs.getFloat(PrefsManager.LADDER_4_1);
            case LEVEL_5: return prefs.getFloat(PrefsManager.LADDER_5_1);
            case LEVEL_T1: return prefs.getFloat(PrefsManager.LADDER_T1_1);
            case LEVEL_T2: return prefs.getFloat(PrefsManager.LADDER_T2_1);
            default: return 0f;
        }
    }

}
