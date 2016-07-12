package com.jmolina.orb.screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.jmolina.orb.interfaces.SuperManager;
import com.jmolina.orb.managers.AssetManager;
import com.jmolina.orb.managers.GameManager;
import com.jmolina.orb.managers.ScreenManager;
import com.jmolina.orb.utils.Grid;
import com.jmolina.orb.assets.Asset;
import com.jmolina.orb.widgets.ProgressBar;

import static com.jmolina.orb.managers.ScreenManager.Key.*;

public class Load extends BaseScreen {

    private Image splash;
    private ProgressBar bar;
    private boolean switching;
    private boolean loaded;
    private SuperManager superManager;

    public Load(SuperManager sm, ScreenManager.Key key) {
        super(sm, key);

        superManager = sm;

        this.splash = new Image(getAsset(Asset.UI_SPLASH, Texture.class));
        this.splash.setPosition(Grid.unit(2), Grid.unit(7.5f));

        this.bar = new ProgressBar(getAssetManager());
        this.bar.setPosition(Grid.unit(2), Grid.unit(3.5f));

        addMainActor(this.splash);
        addMainActor(this.bar);

        switching = false;
        loaded = false;

        getAssetManager().preloadAll(AssetManager.ASSET_CLASS);
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
            switchToMenu();
        }
    }

    /**
     * Llamada especial a switchToScreen para poder crear el GameManager con los assets cargados
     */
    private void switchToMenu() {
        superManager.createGameManager();
        getGameManager().playMusic(GameManager.Track.Menu);
        switchToScreen(MAIN, Hierarchy.LOWER);
    }

}
