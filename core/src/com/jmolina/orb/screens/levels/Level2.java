package com.jmolina.orb.screens.levels;

import com.jmolina.orb.interfaces.SuperManager;
import com.jmolina.orb.managers.ScreenManager;
import com.jmolina.orb.screens.Level;
import com.jmolina.orb.situations.advanced.*;


public class Level2 extends Level {

    public Level2(SuperManager sm, ScreenManager.Key key) {
        super(sm, key);

        setPreviousScreen(ScreenManager.Key.LEVEL_LAUNCH_2);
        setSuccessScreen(ScreenManager.Key.LEVEL_SUCCESS_2);

        addSituation(new Situation200(getAssetManager(), getWorld(), getPixelsPerMeter()));

        addSituation(new Situation201(getAssetManager(), getWorld(), getPixelsPerMeter()));
        addSituation(new Situation201(getAssetManager(), getWorld(), getPixelsPerMeter()));
        addSituation(new Situation201(getAssetManager(), getWorld(), getPixelsPerMeter()));
        addSituation(new Situation201(getAssetManager(), getWorld(), getPixelsPerMeter()));

        addSituation(new Situation202(getAssetManager(), getWorld(), getPixelsPerMeter()));
        addSituation(new Situation202(getAssetManager(), getWorld(), getPixelsPerMeter()));
        addSituation(new Situation202(getAssetManager(), getWorld(), getPixelsPerMeter()));
        addSituation(new Situation202(getAssetManager(), getWorld(), getPixelsPerMeter()));

        addSituation(new Situation201(getAssetManager(), getWorld(), getPixelsPerMeter()));

        addSituation(new Situation203(getAssetManager(), getWorld(), getPixelsPerMeter()));
        addSituation(new Situation203(getAssetManager(), getWorld(), getPixelsPerMeter()));
        addSituation(new Situation203(getAssetManager(), getWorld(), getPixelsPerMeter()));
        addSituation(new Situation203(getAssetManager(), getWorld(), getPixelsPerMeter()));

        addSituation(new Situation204(getAssetManager(), getWorld(), getPixelsPerMeter()));
        addSituation(new Situation204(getAssetManager(), getWorld(), getPixelsPerMeter()));
        addSituation(new Situation204(getAssetManager(), getWorld(), getPixelsPerMeter()));
        addSituation(new Situation204(getAssetManager(), getWorld(), getPixelsPerMeter()));

        addSituation(new Situation201(getAssetManager(), getWorld(), getPixelsPerMeter()));

        addSituation(new Situation205(getAssetManager(), getWorld(), getPixelsPerMeter()));
        addSituation(new Situation205(getAssetManager(), getWorld(), getPixelsPerMeter()));
        addSituation(new Situation205(getAssetManager(), getWorld(), getPixelsPerMeter()));
        addSituation(new Situation205(getAssetManager(), getWorld(), getPixelsPerMeter()));

        addSituation(new Situation206(getAssetManager(), getWorld(), getPixelsPerMeter()));
        addSituation(new Situation206(getAssetManager(), getWorld(), getPixelsPerMeter()));
        addSituation(new Situation206(getAssetManager(), getWorld(), getPixelsPerMeter()));
        addSituation(new Situation206(getAssetManager(), getWorld(), getPixelsPerMeter()));

        addSituation(new Situation201(getAssetManager(), getWorld(), getPixelsPerMeter()));

        addSituation(new Situation207(getAssetManager(), getWorld(), getPixelsPerMeter()));
        addSituation(new Situation207(getAssetManager(), getWorld(), getPixelsPerMeter()));
        addSituation(new Situation207(getAssetManager(), getWorld(), getPixelsPerMeter()));
        addSituation(new Situation207(getAssetManager(), getWorld(), getPixelsPerMeter()));

        addSituation(new Situation208(getAssetManager(), getWorld(), getPixelsPerMeter()));
        addSituation(new Situation208(getAssetManager(), getWorld(), getPixelsPerMeter()));

        addSituation(new Situation201(getAssetManager(), getWorld(), getPixelsPerMeter()));

        addSituation(new Situation209(getAssetManager(), getWorld(), getPixelsPerMeter()));
        addSituation(new Situation209(getAssetManager(), getWorld(), getPixelsPerMeter()));
        addSituation(new Situation209(getAssetManager(), getWorld(), getPixelsPerMeter()));
        addSituation(new Situation209(getAssetManager(), getWorld(), getPixelsPerMeter()));

        addSituation(new Situation210(getAssetManager(), getWorld(), getPixelsPerMeter()));
        addSituation(new Situation210(getAssetManager(), getWorld(), getPixelsPerMeter()));
        addSituation(new Situation210(getAssetManager(), getWorld(), getPixelsPerMeter()));
        addSituation(new Situation210(getAssetManager(), getWorld(), getPixelsPerMeter()));

        addSituation(new Situation299(getAssetManager(), getWorld(), getPixelsPerMeter()));

        setOrbStartPosition(6, 10);
    }

}
