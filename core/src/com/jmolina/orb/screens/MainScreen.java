package com.jmolina.orb.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.jmolina.orb.groups.NoticeGroup;
import com.jmolina.orb.widgets.MainButtonWidget;
import com.jmolina.orb.groups.MainTitleGroup;

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
        mainTitleGroup.setGridPosition(1, 4);

        noticeGroup = new NoticeGroup();
        noticeGroup.setGridPosition(1, 18);

        playTexture = new Texture(Gdx.files.internal("button_play.png"));
        optionsTexture = new Texture(Gdx.files.internal("button_options.png"));
        statsTexture = new Texture(Gdx.files.internal("button_stats.png"));
        creditsTexture = new Texture(Gdx.files.internal("button_credits.png"));
        exitTexture = new Texture(Gdx.files.internal("button_exit.png"));

        playWidget = new MainButtonWidget(playTexture);
        playWidget.setGridPosition(2, 7.5f);
        optionsWidget = new MainButtonWidget(optionsTexture);
        optionsWidget.setGridPosition(2, 9.5f);
        statsWidget = new MainButtonWidget(statsTexture);
        statsWidget.setGridPosition(2, 11.5f);
        creditsWidget = new MainButtonWidget(creditsTexture);
        creditsWidget.setGridPosition(2, 13.5f);
        exitWidget = new MainButtonWidget(exitTexture);
        exitWidget.setGridPosition(2, 15.5f);

        getStage().addActor(mainTitleGroup);
        getStage().addActor(playWidget);
        getStage().addActor(optionsWidget);
        getStage().addActor(statsWidget);
        getStage().addActor(creditsWidget);
        getStage().addActor(exitWidget);
        getStage().addActor(noticeGroup);
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
