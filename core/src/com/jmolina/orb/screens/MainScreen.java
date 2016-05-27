package com.jmolina.orb.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.jmolina.orb.groups.NoticeGroup;
import com.jmolina.orb.widgets.MainButtonWidget;
import com.jmolina.orb.groups.MainTitleGroup;

import static com.jmolina.orb.OrbGame.Name.*;

public class MainScreen extends BaseScreen {

    private MainTitleGroup mainTitleGroup;
    private MainButtonWidget playWidget;
    private MainButtonWidget optionsWidget;
    private MainButtonWidget statsWidget;
    private MainButtonWidget creditsWidget;
    private MainButtonWidget exitWidget;
    private Texture playTexture;
    private Texture optionsTexture;
    private Texture statsTexture;
    private Texture creditsTexture;
    private Texture exitTexture;
    private NoticeGroup noticeGroup;

    public MainScreen() {
        super();

        mainTitleGroup = new MainTitleGroup();
        noticeGroup = new NoticeGroup();

        mainTitleGroup.setGridPosition(1, 4);
        noticeGroup.setGridPosition(1, 18);

        playTexture = new Texture(Gdx.files.internal("button_play.png"));
        optionsTexture = new Texture(Gdx.files.internal("button_options.png"));
        statsTexture = new Texture(Gdx.files.internal("button_stats.png"));
        creditsTexture = new Texture(Gdx.files.internal("button_credits.png"));
        exitTexture = new Texture(Gdx.files.internal("button_exit.png"));

        playWidget = new MainButtonWidget(playTexture);
        optionsWidget = new MainButtonWidget(optionsTexture);
        statsWidget = new MainButtonWidget(statsTexture);
        creditsWidget = new MainButtonWidget(creditsTexture);
        exitWidget = new MainButtonWidget(exitTexture);

        playWidget.setGridPosition(2, 7.5f);
        optionsWidget.setGridPosition(2, 9.5f);
        statsWidget.setGridPosition(2, 11.5f);
        creditsWidget.setGridPosition(2, 13.5f);
        exitWidget.setGridPosition(2, 15.5f);

        playWidget.addListener(new ClickListener(){
            @Override
            public void clicked (InputEvent event, float x, float y) {
                switchToScreen(LEVEL_SELECT, Hierarchy.LOWER);
            }
        });

        optionsWidget.addListener(new ClickListener(){
            @Override
            public void clicked (InputEvent event, float x, float y) {
                switchToScreen(OPTIONS, Hierarchy.LOWER);
            }
        });

        statsWidget.addListener(new ClickListener(){
            @Override
            public void clicked (InputEvent event, float x, float y) {
                switchToScreen(STATS, Hierarchy.LOWER);
            }
        });

        creditsWidget.addListener(new ClickListener(){
            @Override
            public void clicked (InputEvent event, float x, float y) {
                switchToScreen(CREDITS, Hierarchy.LOWER);
            }
        });

        exitWidget.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                exitApplication();
            }
        });

        addMainActor(mainTitleGroup);
        addMainActor(playWidget);
        addMainActor(optionsWidget);
        addMainActor(statsWidget);
        addMainActor(creditsWidget);
        addMainActor(exitWidget);
        addMainActor(noticeGroup);
    }

    @Override
    public void dispose() {
        playTexture.dispose();
        optionsTexture.dispose();
        statsTexture.dispose();
        creditsTexture.dispose();
        exitTexture.dispose();
        noticeGroup.dispose();
        super.dispose();
    }

}
