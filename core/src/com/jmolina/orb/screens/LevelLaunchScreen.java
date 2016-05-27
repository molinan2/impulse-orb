package com.jmolina.orb.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.jmolina.orb.widgets.LadderWidget;
import com.jmolina.orb.widgets.LevelCoverWidget;
import com.jmolina.orb.widgets.LevelTitleWidget;
import com.jmolina.orb.widgets.MainButtonWidget;

import static com.jmolina.orb.OrbGame.Name.LEVEL_SELECT;

public class LevelLaunchScreen extends MenuScreen {

    private LevelTitleWidget title;
    private LevelCoverWidget cover;
    private MainButtonWidget button;
    private LadderWidget ladderPersonal;
    private LadderWidget ladderOnline;

    private Texture titleTexture;
    private Texture coverTexture;
    private Texture buttonTexture;
    private Texture ladderPersonalTexture;
    private Texture ladderOnlineTexture;

    public LevelLaunchScreen() {
        super();

        setReturningScreen(LEVEL_SELECT);

        titleTexture = new Texture(Gdx.files.internal("launch_title.png"));
        coverTexture = new Texture(Gdx.files.internal("launch_cover.png"));
        buttonTexture = new Texture(Gdx.files.internal("launch_button.png"));
        ladderPersonalTexture = new Texture(Gdx.files.internal("launch_personal.png"));
        ladderOnlineTexture = new Texture(Gdx.files.internal("launch_online.png"));

        title = new LevelTitleWidget(titleTexture);
        cover = new LevelCoverWidget(coverTexture);
        button = new MainButtonWidget(buttonTexture);
        ladderPersonal = new LadderWidget(ladderPersonalTexture);
        ladderOnline = new LadderWidget(ladderOnlineTexture);

        addRow(title);
        addRow(cover);
        addRow(button, 1f, 8f);
        addRow(ladderPersonal);
        addRow(ladderOnline);
    }

    @Override
    public void dispose() {
        super.dispose();

        titleTexture.dispose();
        coverTexture.dispose();
        buttonTexture.dispose();
        ladderPersonalTexture.dispose();
        ladderOnlineTexture.dispose();
    }
}
