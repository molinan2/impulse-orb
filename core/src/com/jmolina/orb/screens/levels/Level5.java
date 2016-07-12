package com.jmolina.orb.screens.levels;

import com.jmolina.orb.interfaces.SuperManager;
import com.jmolina.orb.managers.ScreenManager;
import com.jmolina.orb.screens.Level;
import com.jmolina.orb.situations.level1.Situation1End;
import com.jmolina.orb.situations.level1.Situation1Start;


public class Level5 extends Level {

    public Level5(SuperManager sm, ScreenManager.Key key) {
        super(sm, key);

        setPreviousScreen(ScreenManager.Key.LEVEL_LAUNCH_5);
        setSuccessScreen(ScreenManager.Key.LEVEL_SUCCESS_5);

        // Temporal
        addSituation(new Situation1Start(getAssetManager(), getWorld(), getRatioMeterPixel()));
        addSituation(new Situation1End(getAssetManager(), getWorld(), getRatioMeterPixel()));

        setOrbStartPosition(6, 9);
    }

}
