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

        addSituation(Situation200.class);

        addSituation(Situation201.class);
        addSituation(Situation201.class);
        addSituation(Situation201.class);
        addSituation(Situation201.class);

        addSituation(Situation202.class);
        addSituation(Situation202.class);
        addSituation(Situation202.class);
        addSituation(Situation202.class);

        addSituation(Situation201.class);

        addSituation(Situation203.class);
        addSituation(Situation203.class);
        addSituation(Situation203.class);
        addSituation(Situation203.class);

        addSituation(Situation204.class);
        addSituation(Situation204.class);
        addSituation(Situation204.class);
        addSituation(Situation204.class);

        addSituation(Situation201.class);

        addSituation(Situation205.class);
        addSituation(Situation205.class);
        addSituation(Situation205.class);
        addSituation(Situation205.class);

        addSituation(Situation206.class);
        addSituation(Situation206.class);
        addSituation(Situation206.class);
        addSituation(Situation206.class);

        addSituation(Situation201.class);

        addSituation(Situation207.class);
        addSituation(Situation207.class);
        addSituation(Situation207.class);
        addSituation(Situation207.class);

        addSituation(Situation208.class);
        addSituation(Situation208.class);

        addSituation(Situation201.class);

        addSituation(Situation209.class);
        addSituation(Situation209.class);
        addSituation(Situation209.class);
        addSituation(Situation209.class);

        addSituation(Situation210.class);
        addSituation(Situation210.class);
        addSituation(Situation210.class);
        addSituation(Situation210.class);

        addSituation(Situation299.class);

        setOrbStartPosition(6, 10);
    }

}
