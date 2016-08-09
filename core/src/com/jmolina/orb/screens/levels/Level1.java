package com.jmolina.orb.screens.levels;

import com.jmolina.orb.interfaces.SuperManager;
import com.jmolina.orb.managers.ScreenManager;
import com.jmolina.orb.screens.Level;
import com.jmolina.orb.situations.basic.*;


public class Level1 extends Level {

    public Level1(SuperManager sm) {
        super(sm);
        setPreviousScreen(ScreenManager.Key.LAUNCH_1);
        setSuccessScreen(ScreenManager.Key.SUCCESS_1);

        addSituation(Situation100.class);

//        addSituation(Situation107.class);
//        addSituation(Situation107.class);
//
//        addSituation(Situation102.class);
//        addSituation(Situation102.class);
//
//        addSituation(Situation103.class);
//        addSituation(Situation103.class);
//        addSituation(Situation103.class);
//        addSituation(Situation103.class);
//
//        addSituation(Situation104.class);
//        addSituation(Situation104.class);
//        addSituation(Situation104.class);
//        addSituation(Situation104.class);
//
//        addSituation(Situation102.class);
//
//        addSituation(Situation101.class);
//
//        addSituation(Situation105.class);
//        addSituation(Situation105.class);
//        addSituation(Situation105.class);
//        addSituation(Situation105.class);
//
//        addSituation(Situation106.class);
//        addSituation(Situation106.class);
//        addSituation(Situation106.class);
//        addSituation(Situation106.class);
//
//        addSituation(Situation101.class);
//        addSituation(Situation101.class);
//
//        addSituation(Situation102.class);
//
//        addSituation(Situation108.class);
//        addSituation(Situation108.class);
//        addSituation(Situation108.class);
//        addSituation(Situation108.class);
//
//        addSituation(Situation109.class);
//        addSituation(Situation109.class);
//        addSituation(Situation109.class);
//        addSituation(Situation109.class);

        addSituation(Situation199.class);

        setOrbStartPosition(6, 9);
    }

}
