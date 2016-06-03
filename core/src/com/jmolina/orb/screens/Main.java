package com.jmolina.orb.screens;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.jmolina.orb.managers.OrbAssetManager;
import com.jmolina.orb.utils.Grid;
import com.jmolina.orb.widgets.Notice;
import com.jmolina.orb.widgets.Button;
import com.jmolina.orb.widgets.GameTitle;

import static com.jmolina.orb.Orb.Name.*;

public class Main extends BaseScreen {

    private GameTitle gameTitle;
    private Button play;
    private Button options;
    private Button stats;
    private Button credits;
    private Button exit;
    private Notice notice;

    public Main(OrbAssetManager am) {
        super(am);

        gameTitle = new GameTitle(getAssetManager());
        notice = new Notice(getAssetManager());

        gameTitle.setPosition(Grid.unit(1), Grid.unit(14.5f));
        notice.setPosition(Grid.unit(1), Grid.unit(0.5f));

        play = new Button(getAssetManager(), "PLAY", Button.Type.Play);
        options = new Button(getAssetManager(), "OPTIONS", Button.Type.Default);
        stats = new Button(getAssetManager(), "STATS", Button.Type.Default);
        credits = new Button(getAssetManager(), "CREDITS", Button.Type.Default);
        exit = new Button(getAssetManager(), "EXIT", Button.Type.Exit);

        play.setPosition(Grid.unit(2), Grid.unit(11));
        options.setPosition(Grid.unit(2), Grid.unit(9));
        stats.setPosition(Grid.unit(2), Grid.unit(7));
        credits.setPosition(Grid.unit(2), Grid.unit(5));
        exit.setPosition(Grid.unit(2), Grid.unit(3));

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

        addMainActor(gameTitle);
        addMainActor(play);
        addMainActor(options);
        addMainActor(stats);
        addMainActor(credits);
        addMainActor(exit);
        addMainActor(notice);
    }

    @Override
    public void dispose() {
        gameTitle.dispose();
        play.dispose();
        options.dispose();
        stats.dispose();
        credits.dispose();
        exit.dispose();
        notice.dispose();
        super.dispose();
    }

    @Override
    public void back() {
        exitApplication();
    }
}