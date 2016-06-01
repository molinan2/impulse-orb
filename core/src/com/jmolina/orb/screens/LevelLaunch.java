package com.jmolina.orb.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.jmolina.orb.widgets.Ladder;
import com.jmolina.orb.widgets.LevelCover;
import com.jmolina.orb.widgets.LevelTitle;
import com.jmolina.orb.widgets.Button;

import static com.jmolina.orb.Orb.Name.LEVEL_SELECT;

public class LevelLaunch extends Menu {

    private AssetManager assetManager;
    private LevelTitle title;
    private LevelCover cover;
    private Button button;
    private Ladder ladderPersonal;
    private Ladder ladderOnline;
    private Texture coverTexture;

    /**
     * TODO
     * title, cover
     *
     * levelID: carga de disco (o hardcoded) los datos del nivel. Un Objeto mejor por ahora
     */
    public LevelLaunch(AssetManager assetManager, String title) {
        super();

        this.assetManager = assetManager;

        setReturningScreen(LEVEL_SELECT);
        setTitle("LEVEL");

        this.coverTexture = new Texture(Gdx.files.internal("launch_cover.png"));

        this.title = new LevelTitle(title);
        this.cover = new LevelCover(coverTexture);
        this.button = new Button(this.assetManager, "GO!", Button.Type.Play);
        ladderPersonal = new Ladder("Personal best");
        ladderOnline = new Ladder("Online ladder");

        addRow(this.title);
        addRow(this.cover);
        addRow(this.button, 1f, 8f);
        addRow(this.ladderPersonal);
        addRow(this.ladderOnline);
    }

    @Override
    public void dispose() {
        super.dispose();
        coverTexture.dispose();
        button.dispose();
    }
}
