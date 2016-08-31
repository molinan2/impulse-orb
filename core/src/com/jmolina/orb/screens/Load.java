package com.jmolina.orb.screens;

import com.jmolina.orb.interfaces.SuperManager;
import com.jmolina.orb.managers.AssetManager;
import com.jmolina.orb.managers.ScreenManager;
import com.jmolina.orb.var.Var;
import com.jmolina.orb.widgets.ui.ProgressBar;
import com.jmolina.orb.widgets.ui.Splash;

import static com.jmolina.orb.managers.ScreenManager.Key.*;

/**
 * Pantalla de carga
 */
public class Load extends BaseScreen {

    /** Primera pantalla después de la pantalla de carga */
    private final ScreenManager.Key FIRST_SCREEN = Var.FIRST_SCREEN;

    /** Splash */
    private Splash splash;

    /** Barra de progreso */
    private ProgressBar bar;

    /** Indicadores de cambio de pantalla en proceso, y de assets cargados */
    private boolean switching, loaded;

    /**
     * Constructor
     *
     * @param superManager SuperManager
     */
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

    /**
     * El ciclo de render de la pantalla de carga realiza la carga asincrona de assets (que fueron
     * previamente precargados) y actualiza la barra de progreso segun avanza la carga. Una vez esta
     * completa, crea el GameManager y señaliza el cambio de pantalla.
     *
     * @param delta Delta time
     */
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
