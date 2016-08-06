package com.jmolina.orb.screens.levels;

import com.jmolina.orb.interfaces.SuperManager;
import com.jmolina.orb.managers.ScreenManager;
import com.jmolina.orb.screens.Level;
import com.jmolina.orb.situations.Situation;
import com.jmolina.orb.situations.basic.*;


public class Level1 extends Level {

    public Level1(SuperManager sm, ScreenManager.Key key) {
        super(sm, key);

        setPreviousScreen(ScreenManager.Key.LEVEL_LAUNCH_1);
        setSuccessScreen(ScreenManager.Key.LEVEL_SUCCESS_1);

        addSituation(new Situation100(getAssetManager(), getWorld(), getPixelsPerMeter()));

//        addSituation(new Situation107(getAssetManager(), getWorld(), getPixelsPerMeter()));
//        addSituation(new Situation107(getAssetManager(), getWorld(), getPixelsPerMeter()));
//
//        addSituation(new Situation102(getAssetManager(), getWorld(), getPixelsPerMeter()));
//        addSituation(new Situation102(getAssetManager(), getWorld(), getPixelsPerMeter()));
//
//        addSituation(new Situation103(getAssetManager(), getWorld(), getPixelsPerMeter()));
//        addSituation(new Situation103(getAssetManager(), getWorld(), getPixelsPerMeter()));
//        addSituation(new Situation103(getAssetManager(), getWorld(), getPixelsPerMeter()));
//        addSituation(new Situation103(getAssetManager(), getWorld(), getPixelsPerMeter()));
//
//        addSituation(new Situation104(getAssetManager(), getWorld(), getPixelsPerMeter()));
//        addSituation(new Situation104(getAssetManager(), getWorld(), getPixelsPerMeter()));
//        addSituation(new Situation104(getAssetManager(), getWorld(), getPixelsPerMeter()));
//        addSituation(new Situation104(getAssetManager(), getWorld(), getPixelsPerMeter()));
//
//        addSituation(new Situation102(getAssetManager(), getWorld(), getPixelsPerMeter()));
//
//        addSituation(new Situation101(getAssetManager(), getWorld(), getPixelsPerMeter()));
//
//        addSituation(new Situation105(getAssetManager(), getWorld(), getPixelsPerMeter()));
//        addSituation(new Situation105(getAssetManager(), getWorld(), getPixelsPerMeter()));
//        addSituation(new Situation105(getAssetManager(), getWorld(), getPixelsPerMeter()));
//        addSituation(new Situation105(getAssetManager(), getWorld(), getPixelsPerMeter()));
//
//        addSituation(new Situation106(getAssetManager(), getWorld(), getPixelsPerMeter()));
//        addSituation(new Situation106(getAssetManager(), getWorld(), getPixelsPerMeter()));
//        addSituation(new Situation106(getAssetManager(), getWorld(), getPixelsPerMeter()));
//        addSituation(new Situation106(getAssetManager(), getWorld(), getPixelsPerMeter()));
//
//        addSituation(new Situation101(getAssetManager(), getWorld(), getPixelsPerMeter()));
//        addSituation(new Situation101(getAssetManager(), getWorld(), getPixelsPerMeter()));
//
//        addSituation(new Situation102(getAssetManager(), getWorld(), getPixelsPerMeter()));
//
//        addSituation(new Situation108(getAssetManager(), getWorld(), getPixelsPerMeter()));
//        addSituation(new Situation108(getAssetManager(), getWorld(), getPixelsPerMeter()));
//        addSituation(new Situation108(getAssetManager(), getWorld(), getPixelsPerMeter()));
//        addSituation(new Situation108(getAssetManager(), getWorld(), getPixelsPerMeter()));
//
//        addSituation(new Situation109(getAssetManager(), getWorld(), getPixelsPerMeter()));
//        addSituation(new Situation109(getAssetManager(), getWorld(), getPixelsPerMeter()));
//        addSituation(new Situation109(getAssetManager(), getWorld(), getPixelsPerMeter()));
//        addSituation(new Situation109(getAssetManager(), getWorld(), getPixelsPerMeter()));

        addSituation(new Situation199(getAssetManager(), getWorld(), getPixelsPerMeter()));

        setOrbStartPosition(6, 9);
    }

}
