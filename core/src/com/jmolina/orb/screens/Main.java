package com.jmolina.orb.screens;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.jmolina.orb.managers.OrbAssetManager;
import com.jmolina.orb.widgets.Notice;
import com.jmolina.orb.widgets.Button;
import com.jmolina.orb.widgets.MainTitle;

import static com.jmolina.orb.Orb.Name.*;

public class Main extends BaseScreen {

    private MainTitle mainTitle;
    private Button play;
    private Button options;
    private Button stats;
    private Button credits;
    private Button exit;
    private Notice notice;

    public Main(OrbAssetManager am) {
        super(am);

        setAssetManager(am);

        mainTitle = new MainTitle(getAssetManager());
        notice = new Notice(getAssetManager());

        mainTitle.setGridPosition(1, 4);
        notice.setGridPosition(1, 18);

        play = new Button(getAssetManager(), "PLAY", Button.Type.Play);
        options = new Button(getAssetManager(), "OPTIONS", Button.Type.Default);
        stats = new Button(getAssetManager(), "STATS", Button.Type.Default);
        credits = new Button(getAssetManager(), "CREDITS", Button.Type.Default);
        exit = new Button(getAssetManager(), "EXIT", Button.Type.Exit);

        play.setGridPosition(2, 7.5f);
        options.setGridPosition(2, 9.5f);
        stats.setGridPosition(2, 11.5f);
        credits.setGridPosition(2, 13.5f);
        exit.setGridPosition(2, 15.5f);

        play.addListener(new ClickListener(){
            @Override
            public void clicked (InputEvent event, float x, float y) {
                switchToScreen(LEVEL_SELECT, Hierarchy.LOWER);
            }
        });

        options.addListener(new ClickListener(){
            @Override
            public void clicked (InputEvent event, float x, float y) {
                switchToScreen(OPTIONS, Hierarchy.LOWER);
            }
        });

        stats.addListener(new ClickListener(){
            @Override
            public void clicked (InputEvent event, float x, float y) {
                switchToScreen(STATS, Hierarchy.LOWER);
            }
        });

        credits.addListener(new ClickListener(){
            @Override
            public void clicked (InputEvent event, float x, float y) {
                switchToScreen(CREDITS, Hierarchy.LOWER);
            }
        });

        exit.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                exitApplication();
            }
        });

        addMainActor(mainTitle);
        addMainActor(play);
        addMainActor(options);
        addMainActor(stats);
        addMainActor(credits);
        addMainActor(exit);
        addMainActor(notice);
    }

    @Override
    public void dispose() {
        notice.dispose();
        play.dispose();
        options.dispose();
        stats.dispose();
        credits.dispose();
        exit.dispose();
        super.dispose();
    }

    @Override
    public void back() {
        exitApplication();
    }
}
