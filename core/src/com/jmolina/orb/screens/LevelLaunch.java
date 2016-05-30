package com.jmolina.orb.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.jmolina.orb.widgets.LadderWidget;
import com.jmolina.orb.widgets.LevelCoverWidget;
import com.jmolina.orb.widgets.LevelTitleWidget;
import com.jmolina.orb.widgets.Button;

import static com.jmolina.orb.Orb.Name.LEVEL_SELECT;

public class LevelLaunch extends Menu {

    private LevelTitleWidget title;
    private LevelCoverWidget cover;
    private Button goButton;
    private LadderWidget ladderPersonal;
    private LadderWidget ladderOnline;

    private Texture titleTexture;
    private Texture coverTexture;
    private Texture ladderPersonalTexture;
    private Texture ladderOnlineTexture;

    public LevelLaunch() {
        super();

        setReturningScreen(LEVEL_SELECT);
        setTitle("LEVEL");

        titleTexture = new Texture(Gdx.files.internal("launch_title.png"));
        coverTexture = new Texture(Gdx.files.internal("launch_cover.png"));
        ladderPersonalTexture = new Texture(Gdx.files.internal("launch_personal.png"));
        ladderOnlineTexture = new Texture(Gdx.files.internal("launch_online.png"));

        title = new LevelTitleWidget(titleTexture);
        cover = new LevelCoverWidget(coverTexture);
        goButton = new Button("GO!");
        ladderPersonal = new LadderWidget(ladderPersonalTexture);
        ladderOnline = new LadderWidget(ladderOnlineTexture);

        addRow(title);
        addRow(cover);
        addRow(goButton, 1f, 8f);
        addRow(ladderPersonal);
        addRow(ladderOnline);
    }

    @Override
    public void dispose() {
        super.dispose();
        titleTexture.dispose();
        coverTexture.dispose();
        ladderPersonalTexture.dispose();
        ladderOnlineTexture.dispose();
        goButton.dispose();
    }
}
