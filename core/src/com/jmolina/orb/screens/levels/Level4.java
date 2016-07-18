package com.jmolina.orb.screens.levels;

import com.jmolina.orb.interfaces.SuperManager;
import com.jmolina.orb.managers.ScreenManager;
import com.jmolina.orb.screens.Level;
import com.jmolina.orb.situations.level1.Situation1End;
import com.jmolina.orb.situations.level1.Situation1Start;


public class Level4 extends Level {

    public Level4(SuperManager sm, ScreenManager.Key key) {
        super(sm, key);

        setPreviousScreen(ScreenManager.Key.LEVEL_LAUNCH_4);
        setSuccessScreen(ScreenManager.Key.LEVEL_SUCCESS_4);

        // Temporal
        addSituation(new Situation1Start(getAssetManager(), getWorld(), getPixelsPerMeter()));
        addSituation(new Situation1End(getAssetManager(), getWorld(), getPixelsPerMeter()));

        setOrbStartPosition(6, 9);
    }

}
