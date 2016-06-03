package com.jmolina.orb.screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.jmolina.orb.managers.OrbAssetManager;
import com.jmolina.orb.utils.Grid;
import com.jmolina.orb.var.Asset;
import com.jmolina.orb.widgets.ProgressBar;

import static com.jmolina.orb.Orb.Name.MAIN;

public class Loading extends BaseScreen {

    private Image splash;
    private ProgressBar bar;
    private boolean switching;
    private boolean loaded;

    public Loading(OrbAssetManager am) {
        super(am);

        this.splash = new Image(getAsset(Asset.UI_SPLASH, Texture.class));
        this.splash.setPosition(Grid.unit(2), Grid.unit(7.5f));

        this.bar = new ProgressBar(getAssetManager());
        this.bar.setPosition(Grid.unit(2), Grid.unit(3.5f));

        addMainActor(this.splash);
        addMainActor(this.bar);

        switching = false;
        loaded = false;

        getAssetManager().preloadAllAssets();
    }

    @Override
    public void dispose() {
        bar.dispose();
        super.dispose();
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        loaded = getAssetManager().update();
        this.bar.updateProgress(getAssetManager().getProgress());

        if (loaded && !switching) {
            switching = true;
            getScreenManager().createMenuScreens();
            switchToScreen(MAIN, Hierarchy.LOWER);
        }
    }

    @Override
    public void show() {
    }

}
