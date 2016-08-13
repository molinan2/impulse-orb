package com.jmolina.orb.screens;

import com.jmolina.orb.interfaces.SuperManager;
import com.jmolina.orb.managers.AssetManager;
import com.jmolina.orb.managers.ScreenManager;
import com.jmolina.orb.widgets.ui.ProgressBar;
import com.jmolina.orb.widgets.ui.Splash;

import static com.jmolina.orb.managers.ScreenManager.Key.*;

public class Load extends BaseScreen {

    private final ScreenManager.Key FIRST_SCREEN = LEVEL_1;

    private Splash splash;
    private ProgressBar bar;
    private boolean switching, loaded;

    public Load(SuperManager superManager) {
        super(superManager);

        splash = new Splash(getAssetManager());
        splash.setPositionGrid(2, 7.5f);

        bar = new ProgressBar(getAssetManager());
        bar.setPositionGrid(2, 3.5f);

        addMainActor(splash);
        addMainActor(bar);

        switching = false;
        loaded = false;

        getAssetManager().preloadAll(AssetManager.ASSET_CLASS);
    }

    @Override
    public void render(float delta) {
        loaded = getAssetManager().update();
        bar.updateProgress(getAssetManager().getProgress());

        super.render(delta);

        if (loaded && !switching) {
            switching = true;
            getSuperManager().createGameManager();
            switchToScreen(FIRST_SCREEN, Hierarchy.LOWER);
        }
    }

}
