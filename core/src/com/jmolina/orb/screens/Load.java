package com.jmolina.orb.screens;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.jmolina.orb.utils.Grid;
import com.jmolina.orb.widgets.ProgressBar;

import static com.jmolina.orb.Orb.Name.MAIN;

public class Load extends BaseScreen {

    private AssetManager assetManager;
    private Image splash;
    private ProgressBar bar;
    private boolean switching;
    private boolean loaded;

    public Load(AssetManager assetManager, Texture splash) {
        this.assetManager = assetManager;

        this.splash = new Image(splash);
        this.splash.setPosition(Grid.unit(2), Grid.unit(7.5f));

        this.bar = new ProgressBar();
        this.bar.setPosition(Grid.unit(2), Grid.unit(3.5f));

        addMainActor(this.splash);
        addMainActor(this.bar);

        switching = false;
        loaded = false;
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        loaded = assetManager.update();
        this.bar.updateProgress(assetManager.getProgress());

        if (loaded && !switching) {
            switching = true;
            switchToScreen(MAIN, Hierarchy.LOWER);
        }
    }

    @Override
    public void show() {
    }

}
