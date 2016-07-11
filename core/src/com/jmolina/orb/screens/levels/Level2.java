package com.jmolina.orb.screens.levels;

import com.jmolina.orb.interfaces.SuperManager;
import com.jmolina.orb.managers.ScreenManager;
import com.jmolina.orb.screens.Level;
import com.jmolina.orb.situations.Situation101;
import com.jmolina.orb.situations.Situation102;
import com.jmolina.orb.situations.Situation103;
import com.jmolina.orb.situations.Situation104;
import com.jmolina.orb.situations.Situation105;
import com.jmolina.orb.situations.Situation106;
import com.jmolina.orb.situations.Situation107;
import com.jmolina.orb.situations.Situation1End;
import com.jmolina.orb.situations.Situation1Start;


public class Level2 extends Level {

    public Level2(SuperManager sm, ScreenManager.Key key) {
        super(sm, key);

        setPreviousScreen(ScreenManager.Key.LEVEL_LAUNCH_2);
        setSuccessScreen(ScreenManager.Key.LEVEL_SUCCESS_2);

        // Temporal
        addSituation(new Situation1Start(getAssetManager(), getWorld()));
        addSituation(new Situation1End(getAssetManager(), getWorld()));

        setOrbStartPosition(6, 9);
    }

}
