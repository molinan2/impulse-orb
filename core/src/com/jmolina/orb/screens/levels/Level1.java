package com.jmolina.orb.screens.levels;

import com.jmolina.orb.interfaces.SuperManager;
import com.jmolina.orb.managers.ScreenManager;
import com.jmolina.orb.screens.Level;
import com.jmolina.orb.situations.basic.*;

/**
 * Nivel 1
 */
public class Level1 extends Level {

    /**
     * Consntructor
     *
     * @param sm SuperManager
     */
    public Level1(SuperManager sm) {
        super(sm);
        setPreviousScreen(ScreenManager.Key.LAUNCH_1);
        setSuccessScreen(ScreenManager.Key.SUCCESS_1);

        addSituation(Situation100.class);
        addSituation(Situation106B.class);
        addSituation(Situation106B.class);
        addSituation(Situation101.class);
        addSituation(Situation107R.class);
        addSituation(Situation102R.class);
        addSituation(Situation107L.class);
        addSituation(Situation102L.class);
        addSituation(Situation103.class);
        addSituation(Situation103.class);
        addSituation(Situation104.class);
        addSituation(Situation104.class);
        addSituation(Situation107R.class);
        addSituation(Situation102R.class);
        addSituation(Situation105.class);
        addSituation(Situation105.class);
        addSituation(Situation107L.class);
        addSituation(Situation106B.class);
        addSituation(Situation106B.class);
        addSituation(Situation107R.class);
        addSituation(Situation102R.class);
        addSituation(Situation108.class);
        addSituation(Situation108.class);
        addSituation(Situation108.class);
        addSituation(Situation109.class);
        addSituation(Situation109.class);
        addSituation(Situation109.class);
        addSituation(Situation199.class);

        setOrbStartPosition(6, 9);
    }

}
