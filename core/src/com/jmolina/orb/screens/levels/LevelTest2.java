package com.jmolina.orb.screens.levels;

import com.jmolina.orb.interfaces.SuperManager;
import com.jmolina.orb.managers.ScreenManager;
import com.jmolina.orb.screens.Level;
import com.jmolina.orb.situations.test.SituationT100;
import com.jmolina.orb.situations.test.SituationT199;

/**
 * Nivel de Test 2
 */
public class LevelTest2 extends Level {

    /**
     * Consntructor
     *
     * @param sm SuperManager
     */
    public LevelTest2(SuperManager sm) {
        super(sm);

        setPreviousScreen(ScreenManager.Key.LAUNCH_T2);
        setSuccessScreen(ScreenManager.Key.SUCCESS_T2);

        addSituation(SituationT100.class);
        addSituation(SituationT199.class);

        setOrbStartPosition(6, 9);
    }

}
