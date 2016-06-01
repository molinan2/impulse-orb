package com.jmolina.orb.screens;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.jmolina.orb.groups.Notice;
import com.jmolina.orb.managers.ReflectionAssetManager;
import com.jmolina.orb.widgets.Button;
import com.jmolina.orb.groups.MainTitle;

import static com.jmolina.orb.Orb.Name.*;

public class Main extends BaseScreen {

    private ReflectionAssetManager assetManager;
    private MainTitle mainTitle;
    private Button play;
    private Button options;
    private Button stats;
    private Button credits;
    private Button exit;
    private Notice notice;

    public Main(ReflectionAssetManager assetManager) {
        super();

        this.assetManager = assetManager;

        mainTitle = new MainTitle();
        notice = new Notice();

        mainTitle.setGridPosition(1, 4);
        notice.setGridPosition(1, 18);

        play = new Button(this.assetManager, "PLAY", Button.Type.Play);
        options = new Button(this.assetManager, "OPTIONS", Button.Type.Default);
        stats = new Button(this.assetManager, "STATS", Button.Type.Default);
        credits = new Button(this.assetManager, "CREDITS", Button.Type.Default);
        exit = new Button(this.assetManager, "EXIT", Button.Type.Exit);



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
