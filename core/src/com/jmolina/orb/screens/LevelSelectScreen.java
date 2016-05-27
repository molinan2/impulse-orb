package com.jmolina.orb.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.jmolina.orb.OrbGame;
import com.jmolina.orb.widgets.LevelCardWidget;

import static com.jmolina.orb.OrbGame.Name.LEVEL_LAUNCH;

public class LevelSelectScreen extends MenuScreen {

    private LevelCardWidget level1;
    private LevelCardWidget level2;
    private LevelCardWidget level3;
    private LevelCardWidget level4;
    private Texture level1CoverTexture;
    private Texture level1TitleTexture;
    private Texture level1BestTexture;
    private Texture level1WorldTexture;

    public LevelSelectScreen() {
        super();

        setReturningScreen(OrbGame.Name.MAIN);

        level1CoverTexture = new Texture(Gdx.files.internal("card_cover.png"));
        level1TitleTexture = new Texture(Gdx.files.internal("card_title.png"));
        level1BestTexture = new Texture(Gdx.files.internal("card_best.png"));
        level1WorldTexture = new Texture(Gdx.files.internal("card_world.png"));
        level1 = new LevelCardWidget(level1CoverTexture, level1TitleTexture, level1BestTexture, level1WorldTexture);
        level2 = new LevelCardWidget(level1CoverTexture, level1TitleTexture, level1BestTexture, level1WorldTexture);
        level3 = new LevelCardWidget(level1CoverTexture, level1TitleTexture, level1BestTexture, level1WorldTexture);
        level4 = new LevelCardWidget(level1CoverTexture, level1TitleTexture, level1BestTexture, level1WorldTexture);

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
        level1TitleTexture.dispose();
        level1BestTexture.dispose();
        level1WorldTexture.dispose();
        super.dispose();
    }

}
