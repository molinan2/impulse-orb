package com.jmolina.orb.screens;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.jmolina.orb.interfaces.SuperManager;
import com.jmolina.orb.managers.GameManager;
import com.jmolina.orb.managers.ScreenManager;
import com.jmolina.orb.utils.Grid;
import com.jmolina.orb.widgets.Notice;
import com.jmolina.orb.widgets.MainButton;
import com.jmolina.orb.widgets.GameTitle;

import static com.jmolina.orb.managers.ScreenManager.Key.*;

public class Main extends BaseScreen {

    private GameTitle gameTitle;
    private MainButton play;
    private MainButton options;
    private MainButton stats;
    private MainButton credits;
    private MainButton exit;
    private Notice notice;

    public Main(SuperManager superManager, ScreenManager.Key key) {
        super(superManager, key);

        gameTitle = new GameTitle(getAssetManager());
        notice = new Notice(getAssetManager());

        gameTitle.setPosition(Grid.unit(1), Grid.unit(14.5f));
        notice.setPosition(Grid.unit(1), Grid.unit(0.5f));

        play = new MainButton(getAssetManager(), "PLAY", MainButton.Type.Play);
        options = new MainButton(getAssetManager(), "OPTIONS", MainButton.Type.Default);
        stats = new MainButton(getAssetManager(), "STATS", MainButton.Type.Default);
        credits = new MainButton(getAssetManager(), "CREDITS", MainButton.Type.Default);
        exit = new MainButton(getAssetManager(), "EXIT", MainButton.Type.Exit);

        play.setPosition(Grid.unit(2), Grid.unit(11));
        options.setPosition(Grid.unit(2), Grid.unit(9));
        stats.setPosition(Grid.unit(2), Grid.unit(7));
        credits.setPosition(Grid.unit(2), Grid.unit(5));
        exit.setPosition(Grid.unit(2), Grid.unit(3));

        play.addListener(new ClickListener(){
            @Override
            public void clicked (InputEvent event, float x, float y) {
                play.clickEffect();
                getGameManager().play(GameManager.Fx.Button);
                switchToScreen(LEVEL_SELECT, Hierarchy.LOWER);
            }
        });

        options.addListener(new ClickListener(){
            @Override
            public void clicked (InputEvent event, float x, float y) {
                options.clickEffect();
                getGameManager().play(GameManager.Fx.Button);
                switchToScreen(OPTIONS, Hierarchy.LOWER);
            }
        });

        stats.addListener(new ClickListener(){
            @Override
            public void clicked (InputEvent event, float x, float y) {
                stats.clickEffect();
                getGameManager().play(GameManager.Fx.Button);
                switchToScreen(STATS, Hierarchy.LOWER);
            }
        });

        credits.addListener(new ClickListener(){
            @Override
            public void clicked (InputEvent event, float x, float y) {
                credits.clickEffect();
                getGameManager().play(GameManager.Fx.Button);
                switchToScreen(CREDITS, Hierarchy.LOWER);
            }
        });

        exit.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                exit.clickEffect();
                getGameManager().play(GameManager.Fx.Back);
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
