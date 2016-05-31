package com.jmolina.orb.screens;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.jmolina.orb.groups.Notice;
import com.jmolina.orb.widgets.Button;
import com.jmolina.orb.groups.MainTitleGroup;

import static com.jmolina.orb.Orb.Name.*;

public class Main extends BaseScreen {

    private MainTitleGroup mainTitleGroup;
    private Button playButton;
    private Button optionsButton;
    private Button statsButton;
    private Button creditsButton;
    private Button exitButton;
    private Notice notice;

    public Main() {
        super();

        mainTitleGroup = new MainTitleGroup();
        notice = new Notice();

        mainTitleGroup.setGridPosition(1, 4);
        notice.setGridPosition(1, 18);

        playButton = new Button("PLAY", Button.Type.Play);
        optionsButton = new Button("OPTIONS", Button.Type.Default);
        statsButton = new Button("STATS", Button.Type.Default);
        creditsButton = new Button("CREDITS", Button.Type.Default);
        exitButton = new Button("EXIT", Button.Type.Exit);

        playButton.setGridPosition(2, 7.5f);
        optionsButton.setGridPosition(2, 9.5f);
        statsButton.setGridPosition(2, 11.5f);
        creditsButton.setGridPosition(2, 13.5f);
        exitButton.setGridPosition(2, 15.5f);

        playButton.addListener(new ClickListener(){
            @Override
            public void clicked (InputEvent event, float x, float y) {
                switchToScreen(LEVEL_SELECT, Hierarchy.LOWER);
            }
        });

        optionsButton.addListener(new ClickListener(){
            @Override
            public void clicked (InputEvent event, float x, float y) {
                switchToScreen(OPTIONS, Hierarchy.LOWER);
            }
        });

        statsButton.addListener(new ClickListener(){
            @Override
            public void clicked (InputEvent event, float x, float y) {
                switchToScreen(STATS, Hierarchy.LOWER);
            }
        });

        creditsButton.addListener(new ClickListener(){
            @Override
            public void clicked (InputEvent event, float x, float y) {
                switchToScreen(CREDITS, Hierarchy.LOWER);
            }
        });

        exitButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                exitApplication();
            }
        });

        addMainActor(mainTitleGroup);
        addMainActor(playButton);
        addMainActor(optionsButton);
        addMainActor(statsButton);
        addMainActor(creditsButton);
        addMainActor(exitButton);
        addMainActor(notice);
    }

    @Override
    public void dispose() {
        notice.dispose();
        playButton.dispose();
        optionsButton.dispose();
        statsButton.dispose();
        creditsButton.dispose();
        exitButton.dispose();
        super.dispose();
    }

    @Override
    public void back() {
        exitApplication();
    }
}
