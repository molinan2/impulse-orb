package com.jmolina.orb.screens;

import com.badlogic.gdx.graphics.Texture;
import com.jmolina.orb.managers.OrbAssetManager;
import com.jmolina.orb.var.Asset;
import com.jmolina.orb.widgets.Ladder;
import com.jmolina.orb.widgets.LevelCover;
import com.jmolina.orb.widgets.LevelTitle;
import com.jmolina.orb.widgets.Button;

import static com.jmolina.orb.Orb.Name.LEVEL_SELECT;

public class LevelLaunch extends Menu {

    private LevelTitle title;
    private LevelCover cover;
    private Button button;
    private Ladder ladderPersonal;
    private Ladder ladderOnline;

    public LevelLaunch(OrbAssetManager am, String title) {
        super(am);

        setAssetManager(am);

        setReturningScreen(LEVEL_SELECT);
        setTitle("LEVEL");

        this.title = new LevelTitle(getAssetManager(), title);
        this.cover = new LevelCover(getAssetManager(), getAsset(Asset.UI_LAUNCH_COVER, Texture.class));
        this.button = new Button(getAssetManager(), "GO!", Button.Type.Play);
        ladderPersonal = new Ladder(getAssetManager(), "Personal best");
        ladderOnline = new Ladder(getAssetManager(), "Online ladder");

        addRow(this.title);
        addRow(this.cover);
        addRow(this.button, 1f, 8f);
        addRow(this.ladderPersonal);
        addRow(this.ladderOnline);
    }

    @Override
    public void dispose() {
        title.dispose();
        cover.dispose();
        button.dispose();
        ladderOnline.dispose();
        ladderPersonal.dispose();
        super.dispose();
    }

}
