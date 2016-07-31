package com.jmolina.orb.screens.levels;

import com.jmolina.orb.interfaces.SuperManager;
import com.jmolina.orb.managers.ScreenManager;
import com.jmolina.orb.screens.Level;
import com.jmolina.orb.situations.test.SituationT199;
import com.jmolina.orb.situations.test.SituationT100;


public class Level2 extends Level {

    public Level2(SuperManager sm, ScreenManager.Key key) {
        super(sm, key);

        setPreviousScreen(ScreenManager.Key.LEVEL_LAUNCH_2);
        setSuccessScreen(ScreenManager.Key.LEVEL_SUCCESS_2);

        // Temporal
        addSituation(new SituationT100(getAssetManager(), getWorld(), getPixelsPerMeter()));
        addSituation(new SituationT199(getAssetManager(), getWorld(), getPixelsPerMeter()));

        setOrbStartPosition(6, 9);
    }

}
