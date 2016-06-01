package com.jmolina.orb.screens;

import com.badlogic.gdx.graphics.Texture;
import com.jmolina.orb.Orb;
import com.jmolina.orb.interfaces.Visitor;
import com.jmolina.orb.managers.OrbAssetManager;
import com.jmolina.orb.var.Asset;
import com.jmolina.orb.widgets.Card;

import static com.jmolina.orb.Orb.Name.*;
import static com.jmolina.orb.screens.BaseScreen.Hierarchy.*;

public class LevelSelect extends Menu {

    private Card level1;
    private Card level2;
    private Card level3;
    private Card level4;

    public LevelSelect(OrbAssetManager am) {
        super(am);

        setReturningScreen(Orb.Name.MAIN);
        setTitle("SELECT");

        Texture cover = getAsset(Asset.UI_CARD_COVER, Texture.class);

        level1 = new Card(getAssetManager(), "BASICS", "1:34.72", "57.41", cover);
        level2 = new Card(getAssetManager(), "ADVANCED", "1:34.72", "57.41", cover);
        level3 = new Card(getAssetManager(), "EXPERT", "--", "57.41", cover, true);
        level4 = new Card(getAssetManager(), "HERO", "--", "57.41", cover, true);

        Visitor screenSwitcher1 = new Visitor() {
            @Override
            public void run() {
                switchToScreen(LEVEL_LAUNCH_1, LOWER);
            }
        };

        Visitor screenSwitcher2 = new Visitor() {
            @Override
            public void run() {
                switchToScreen(LEVEL_LAUNCH_2, LOWER);
            }
        };

        Visitor screenSwitcher3 = new Visitor() {
            @Override
            public void run() {
                switchToScreen(LEVEL_LAUNCH_3, LOWER);
            }
        };

        Visitor screenSwitcher4 = new Visitor() {
            @Override
            public void run() {
                switchToScreen(LEVEL_LAUNCH_4, LOWER);
            }
        };

        level1.setOnClickOperation(screenSwitcher1);
        level2.setOnClickOperation(screenSwitcher2);
        level3.setOnClickOperation(screenSwitcher3);
        level4.setOnClickOperation(screenSwitcher4);

        addRow(level1);
        addRow(level2);
        addRow(level3);
        addRow(level4);
    }

    @Override
    public void dispose() {
        level1.dispose();
        level2.dispose();
        level3.dispose();
        level4.dispose();
        super.dispose();
    }

}
