package com.jmolina.orb.screens.levels;

import com.jmolina.orb.interfaces.SuperManager;
import com.jmolina.orb.managers.ScreenManager;
import com.jmolina.orb.screens.Level;
import com.jmolina.orb.situations.test.SituationT100;
import com.jmolina.orb.situations.test.SituationT199;


public class LevelTest2 extends Level {

    public LevelTest2(SuperManager sm, ScreenManager.Key key) {
        super(sm, key);

        setPreviousScreen(ScreenManager.Key.LEVEL_LAUNCH_T2);
        setSuccessScreen(ScreenManager.Key.LEVEL_SUCCESS_T2);

        // Temporal
        addSituation(new SituationT100(getAssetManager(), getWorld(), getPixelsPerMeter()));
        addSituation(new SituationT199(getAssetManager(), getWorld(), getPixelsPerMeter()));

        setOrbStartPosition(6, 9);
    }

}
