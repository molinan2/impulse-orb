package com.jmolina.orb.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.jmolina.orb.Orb;
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

        level1.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                switchToScreen(LEVEL_LAUNCH, Hierarchy.LOWER);
            }
        });

        level2.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                switchToScreen(LEVEL_LAUNCH, Hierarchy.LOWER);
            }
        });

        level3.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                switchToScreen(LEVEL_LAUNCH, Hierarchy.LOWER);
            }
        });

        level4.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                switchToScreen(LEVEL_LAUNCH, Hierarchy.LOWER);
            }
        });

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
