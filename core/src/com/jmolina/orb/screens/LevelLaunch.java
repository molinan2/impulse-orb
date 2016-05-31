package com.jmolina.orb.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.jmolina.orb.widgets.Ladder;
import com.jmolina.orb.widgets.LevelCover;
import com.jmolina.orb.widgets.LevelTitle;
import com.jmolina.orb.widgets.Button;

import static com.jmolina.orb.Orb.Name.LEVEL_SELECT;

public class LevelLaunch extends Menu {

    private LevelTitle title;
    private LevelCover cover;
    private Button goButton;
    private Ladder ladderPersonal;
    private Ladder ladderOnline;

    private Texture titleTexture;
    private Texture coverTexture;
    private Texture ladderPersonalTexture;
    private Texture ladderOnlineTexture;

    /**
     * TODO
     * title, cover, ladderPersonal, ladderOnline
     *
     * levelID: carga de disco (o hardcoded) los datos del nivel. Un Objeto mejor por ahora
     */
    public LevelLaunch() {
        super();

        setReturningScreen(LEVEL_SELECT);
        setTitle("LEVEL");

        titleTexture = new Texture(Gdx.files.internal("launch_title.png"));
        coverTexture = new Texture(Gdx.files.internal("launch_cover.png"));
        ladderPersonalTexture = new Texture(Gdx.files.internal("launch_personal.png"));
        ladderOnlineTexture = new Texture(Gdx.files.internal("launch_online.png"));

        title = new LevelTitle(titleTexture);
        cover = new LevelCover(coverTexture);
        goButton = new Button("GO!", Button.Type.Play);
        ladderPersonal = new Ladder("Personal best");
        ladderOnline = new Ladder("Online ladder");

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
