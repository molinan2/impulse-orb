package com.jmolina.orb.screens.levels;

import com.jmolina.orb.interfaces.SuperManager;
import com.jmolina.orb.managers.ScreenManager;
import com.jmolina.orb.screens.Level;
import com.jmolina.orb.situations.Situation1End;
import com.jmolina.orb.situations.Situation1Start;


public class Level3 extends Level {

    public Level3(SuperManager sm, ScreenManager.Key key) {
        super(sm, key);

        setPreviousScreen(ScreenManager.Key.LEVEL_LAUNCH_3);
        setSuccessScreen(ScreenManager.Key.LEVEL_SUCCESS_3);

        // Temporal
        addSituation(new Situation1Start(getAssetManager(), getWorld()));
        addSituation(new Situation1End(getAssetManager(), getWorld()));

        setOrbStartPosition(6, 9);
    }

}
