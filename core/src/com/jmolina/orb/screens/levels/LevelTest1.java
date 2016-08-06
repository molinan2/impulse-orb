package com.jmolina.orb.screens.levels;

import com.jmolina.orb.interfaces.SuperManager;
import com.jmolina.orb.managers.ScreenManager;
import com.jmolina.orb.screens.Level;
import com.jmolina.orb.situations.test.SituationT100;
import com.jmolina.orb.situations.test.SituationT101;
import com.jmolina.orb.situations.test.SituationT102;
import com.jmolina.orb.situations.test.SituationT103;
import com.jmolina.orb.situations.test.SituationT104;
import com.jmolina.orb.situations.test.SituationT105;
import com.jmolina.orb.situations.test.SituationT106;
import com.jmolina.orb.situations.test.SituationT107;
import com.jmolina.orb.situations.test.SituationT199;


public class LevelTest1 extends Level {

    public LevelTest1(SuperManager sm, ScreenManager.Key key) {
        super(sm, key);

        setPreviousScreen(ScreenManager.Key.LEVEL_LAUNCH_T1);
        setSuccessScreen(ScreenManager.Key.LEVEL_SUCCESS_T1);

        addSituation(SituationT100.class);
        addSituation(SituationT101.class);
        addSituation(SituationT102.class);
        addSituation(SituationT103.class);
        addSituation(SituationT104.class);
        addSituation(SituationT105.class);
        addSituation(SituationT106.class);
        addSituation(SituationT107.class);
        addSituation(SituationT199.class);

        setOrbStartPosition(6, 9);
    }

}
