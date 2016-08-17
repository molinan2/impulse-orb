package com.jmolina.orb.screens.levels;

import com.jmolina.orb.interfaces.SuperManager;
import com.jmolina.orb.managers.ScreenManager;
import com.jmolina.orb.screens.Level;
import com.jmolina.orb.situations.advanced.*;


public class Level2 extends Level {

    public Level2(SuperManager sm) {
        super(sm);

        setPreviousScreen(ScreenManager.Key.LAUNCH_2);
        setSuccessScreen(ScreenManager.Key.SUCCESS_2);

        addSituation(Situation200.class);
        addSituation(Situation201.class);
        addSituation(Situation201.class);
        addSituation(Situation202.class);
        addSituation(Situation202.class);
        addSituation(Situation203.class);
        addSituation(Situation203.class);
        addSituation(Situation203.class);
        addSituation(Situation204.class);
        addSituation(Situation204.class);
        addSituation(Situation201.class);
        addSituation(Situation205.class);
        addSituation(Situation205.class);
        addSituation(Situation205.class);
        addSituation(Situation206.class);
        addSituation(Situation201.class);
        addSituation(Situation207CW.class);
        addSituation(Situation207CCW.class);
        addSituation(Situation207CW.class);
        addSituation(Situation207CCW.class);
        addSituation(Situation208.class);
        addSituation(Situation208.class);
        addSituation(Situation201.class);
        addSituation(Situation209CW.class);
        addSituation(Situation209CCW.class);
        addSituation(Situation209CW.class);
        addSituation(Situation210.class);
        addSituation(Situation210.class);
        addSituation(Situation210.class);
        addSituation(Situation299.class);

        setOrbStartPosition(6, 10);
    }

}
