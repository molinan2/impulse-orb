package com.jmolina.orb.screens.levels;

import com.jmolina.orb.interfaces.SuperManager;
import com.jmolina.orb.managers.ScreenManager;
import com.jmolina.orb.screens.Level;
import com.jmolina.orb.situations.level1.Situation199;
import com.jmolina.orb.situations.level1.Situation100;


public class Level5 extends Level {

    public Level5(SuperManager sm, ScreenManager.Key key) {
        super(sm, key);

        setPreviousScreen(ScreenManager.Key.LEVEL_LAUNCH_5);
        setSuccessScreen(ScreenManager.Key.LEVEL_SUCCESS_5);

        // Temporal
        addSituation(new Situation100(getAssetManager(), getWorld(), getPixelsPerMeter()));
        addSituation(new Situation199(getAssetManager(), getWorld(), getPixelsPerMeter()));

        setOrbStartPosition(6, 9);
    }

}
