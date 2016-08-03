package com.jmolina.orb.screens.levels;

import com.jmolina.orb.interfaces.SuperManager;
import com.jmolina.orb.managers.ScreenManager;
import com.jmolina.orb.screens.Level;
import com.jmolina.orb.situations.hero.*;


public class Level4 extends Level {

    public Level4(SuperManager sm, ScreenManager.Key key) {
        super(sm, key);

        setPreviousScreen(ScreenManager.Key.LEVEL_LAUNCH_4);
        setSuccessScreen(ScreenManager.Key.LEVEL_SUCCESS_4);

        addSituation(new Situation400(getAssetManager(), getWorld(), getPixelsPerMeter()));

        addSituation(new Situation401(getAssetManager(), getWorld(), getPixelsPerMeter()));
        addSituation(new Situation401(getAssetManager(), getWorld(), getPixelsPerMeter()));

        addSituation(new Situation409(getAssetManager(), getWorld(), getPixelsPerMeter()));

        addSituation(new Situation402(getAssetManager(), getWorld(), getPixelsPerMeter()));
        addSituation(new Situation402(getAssetManager(), getWorld(), getPixelsPerMeter()));
        addSituation(new Situation402(getAssetManager(), getWorld(), getPixelsPerMeter()));
        addSituation(new Situation402(getAssetManager(), getWorld(), getPixelsPerMeter()));

        addSituation(new Situation409(getAssetManager(), getWorld(), getPixelsPerMeter()));

        addSituation(new Situation403(getAssetManager(), getWorld(), getPixelsPerMeter()));
        addSituation(new Situation403(getAssetManager(), getWorld(), getPixelsPerMeter()));
        addSituation(new Situation403(getAssetManager(), getWorld(), getPixelsPerMeter()));
        addSituation(new Situation403(getAssetManager(), getWorld(), getPixelsPerMeter()));

        addSituation(new Situation409(getAssetManager(), getWorld(), getPixelsPerMeter()));

        addSituation(new Situation404(getAssetManager(), getWorld(), getPixelsPerMeter()));
        addSituation(new Situation404(getAssetManager(), getWorld(), getPixelsPerMeter()));
        addSituation(new Situation404(getAssetManager(), getWorld(), getPixelsPerMeter()));
        addSituation(new Situation404(getAssetManager(), getWorld(), getPixelsPerMeter()));

        addSituation(new Situation405(getAssetManager(), getWorld(), getPixelsPerMeter()));
        addSituation(new Situation405(getAssetManager(), getWorld(), getPixelsPerMeter()));
        addSituation(new Situation405(getAssetManager(), getWorld(), getPixelsPerMeter()));
        addSituation(new Situation405(getAssetManager(), getWorld(), getPixelsPerMeter()));

        addSituation(new Situation409(getAssetManager(), getWorld(), getPixelsPerMeter()));

        addSituation(new Situation406(getAssetManager(), getWorld(), getPixelsPerMeter()));
        addSituation(new Situation406(getAssetManager(), getWorld(), getPixelsPerMeter()));
        addSituation(new Situation406(getAssetManager(), getWorld(), getPixelsPerMeter()));
        addSituation(new Situation406(getAssetManager(), getWorld(), getPixelsPerMeter()));

        addSituation(new Situation407(getAssetManager(), getWorld(), getPixelsPerMeter()));
        addSituation(new Situation407(getAssetManager(), getWorld(), getPixelsPerMeter()));
        addSituation(new Situation407(getAssetManager(), getWorld(), getPixelsPerMeter()));
        addSituation(new Situation407(getAssetManager(), getWorld(), getPixelsPerMeter()));

        addSituation(new Situation409(getAssetManager(), getWorld(), getPixelsPerMeter()));

        addSituation(new Situation408(getAssetManager(), getWorld(), getPixelsPerMeter()));
        addSituation(new Situation408(getAssetManager(), getWorld(), getPixelsPerMeter()));
        addSituation(new Situation408(getAssetManager(), getWorld(), getPixelsPerMeter()));
        addSituation(new Situation408(getAssetManager(), getWorld(), getPixelsPerMeter()));
        addSituation(new Situation408(getAssetManager(), getWorld(), getPixelsPerMeter()));
        addSituation(new Situation408(getAssetManager(), getWorld(), getPixelsPerMeter()));
        addSituation(new Situation408(getAssetManager(), getWorld(), getPixelsPerMeter()));
        addSituation(new Situation408(getAssetManager(), getWorld(), getPixelsPerMeter()));

        addSituation(new Situation499(getAssetManager(), getWorld(), getPixelsPerMeter()));

        setOrbStartPosition(6, 9);
    }

}
