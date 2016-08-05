package com.jmolina.orb.screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.jmolina.orb.interfaces.SuperManager;
import com.jmolina.orb.managers.AssetManager;
import com.jmolina.orb.managers.ScreenManager;
import com.jmolina.orb.utils.Utils;
import com.jmolina.orb.var.Asset;
import com.jmolina.orb.widgets.ui.ProgressBar;

import static com.jmolina.orb.managers.ScreenManager.Key.*;

public class Load extends BaseScreen {

    private final ScreenManager.Key FIRST_SCREEN = LEVEL_SUCCESS_1;

    private Image splash;
    private ProgressBar bar;
    private boolean switching;
    private boolean loaded;
    private SuperManager superManager;

    public Load(SuperManager sm, ScreenManager.Key key) {
        super(sm, key);

        superManager = sm;

        this.splash = new Image(getAsset(Asset.UI_SPLASH, Texture.class));
        this.splash.setPosition(Utils.cell(2), Utils.cell(7.5f));

        this.bar = new ProgressBar(getAssetManager());
        this.bar.setPosition(Utils.cell(2), Utils.cell(3.5f));

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
            superManager.createGameManager();
            switchToScreen(MAIN, Hierarchy.LOWER);
        }
    }

}
