package com.jmolina.orb.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.jmolina.orb.Orb;
import com.jmolina.orb.interfaces.Visitor;
import com.jmolina.orb.widgets.Card;

import static com.jmolina.orb.Orb.Name.LEVEL_LAUNCH;

public class LevelSelect extends Menu {

    private Card level1;
    private Card level2;
    private Card level3;
    private Card level4;

    private Texture level1CoverTexture;

    public LevelSelect() {
        super();

        setReturningScreen(Orb.Name.MAIN);
        setTitle("SELECT");

        level1CoverTexture = new Texture(Gdx.files.internal("card_cover.png"));

        level1 = new Card("BASICS", "1:34.72", "57.41", level1CoverTexture);
        level2 = new Card("ADVANCED", "1:34.72", "57.41", level1CoverTexture);
        level3 = new Card("EXPERT", "--", "57.41", level1CoverTexture, true);
        level4 = new Card("HERO", "--", "57.41", level1CoverTexture, true);

        Visitor screenSwitcher = new Visitor() {
            @Override
            public void run() {
                switchToScreen(LEVEL_LAUNCH, Hierarchy.LOWER);
            }
        };

        level1.setOnClickOperation(screenSwitcher);
        level2.setOnClickOperation(screenSwitcher);
        level3.setOnClickOperation(screenSwitcher);
        level4.setOnClickOperation(screenSwitcher);

        addRow(level1);
        addRow(level2);
        addRow(level3);
        addRow(level4);
    }

    @Override
    public void dispose() {
        level1CoverTexture.dispose();
        level1.dispose();
        level2.dispose();
        level3.dispose();
        level4.dispose();
        super.dispose();
    }

}
