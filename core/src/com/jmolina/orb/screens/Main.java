package com.jmolina.orb.screens;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.jmolina.orb.interfaces.SuperManager;
import com.jmolina.orb.managers.GameManager;
import com.jmolina.orb.managers.ScreenManager;
import com.jmolina.orb.utils.Utils;
import com.jmolina.orb.widgets.ui.Notice;
import com.jmolina.orb.widgets.ui.MainButton;
import com.jmolina.orb.widgets.ui.GameTitle;

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

        gameTitle.setPosition(Utils.cell(1), Utils.cell(14.5f));
        notice.setPosition(Utils.cell(1), Utils.cell(0.5f));

        play = new MainButton(getAssetManager(), "PLAY", MainButton.Type.Play);
        options = new MainButton(getAssetManager(), "OPTIONS", MainButton.Type.Default);
        stats = new MainButton(getAssetManager(), "STATS", MainButton.Type.Default);
        credits = new MainButton(getAssetManager(), "CREDITS", MainButton.Type.Default);
        exit = new MainButton(getAssetManager(), "EXIT", MainButton.Type.Exit);

        play.setPosition(Utils.cell(2), Utils.cell(11));
        options.setPosition(Utils.cell(2), Utils.cell(9));
        stats.setPosition(Utils.cell(2), Utils.cell(7));
        credits.setPosition(Utils.cell(2), Utils.cell(5));
        exit.setPosition(Utils.cell(2), Utils.cell(3));

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

    @Override
    public void show() {
        super.show();
        getGameManager().play(GameManager.Track.Menu);
    }
}
