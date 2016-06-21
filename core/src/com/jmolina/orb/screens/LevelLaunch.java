package com.jmolina.orb.screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.jmolina.orb.assets.Asset;
import com.jmolina.orb.interfaces.SuperManager;
import com.jmolina.orb.widgets.Ladder;
import com.jmolina.orb.widgets.LevelCover;
import com.jmolina.orb.widgets.LevelTitle;
import com.jmolina.orb.widgets.Button;

import static com.jmolina.orb.managers.ScreenManager.Key.*;

public class LevelLaunch extends Menu {

    private LevelTitle title;
    private LevelCover cover;
    private Button button;
    private Ladder ladderPersonal;
    private Ladder ladderOnline;

    public LevelLaunch(SuperManager superManager, String title) {
        super(superManager);

        setReturningScreen(LEVEL_SELECT);
        setTitle("LEVEL");

        this.title = new LevelTitle(getAssetManager(), title);
        this.cover = new LevelCover(getAssetManager(), getAsset(Asset.UI_LAUNCH_COVER, Texture.class));
        this.button = new Button(getAssetManager(), "GO!", Button.Type.Play);
        ladderPersonal = new Ladder(getAssetManager(), "Personal best");
        ladderOnline = new Ladder(getAssetManager(), "Online ladder");

        add(this.title);
        add(this.cover);
        add(this.button, 1f, 8f);
        add(this.ladderPersonal);
        add(this.ladderOnline);

        // TODO Hay que parametrizar el nivel que se carga

        button.addListener(new ClickListener(){
            @Override
            public void clicked (InputEvent event, float x, float y) {
                button.clickEffect();
                switchToScreen(LEVEL, Hierarchy.LOWER);
            }
        });
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
