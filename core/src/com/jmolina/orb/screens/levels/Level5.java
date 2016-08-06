package com.jmolina.orb.screens.levels;

import com.jmolina.orb.interfaces.SuperManager;
import com.jmolina.orb.managers.ScreenManager;
import com.jmolina.orb.screens.Level;
import com.jmolina.orb.situations.god.*;


public class Level5 extends Level {

    public Level5(SuperManager sm, ScreenManager.Key key) {
        super(sm, key);

        setPreviousScreen(ScreenManager.Key.LEVEL_LAUNCH_5);
        setSuccessScreen(ScreenManager.Key.LEVEL_SUCCESS_5);

        addSituation(Situation500.class);

        addSituation(Situation501.class);
        addSituation(Situation501.class);
        addSituation(Situation501.class);
        addSituation(Situation501.class);

        addSituation(Situation502.class);
        addSituation(Situation502.class);
        addSituation(Situation502.class);
        addSituation(Situation502.class);

        addSituation(Situation503.class);
        addSituation(Situation503.class);
        addSituation(Situation503.class);
        addSituation(Situation503.class);

        addSituation(Situation507.class);

        addSituation(Situation504.class);
        addSituation(Situation504.class);
        addSituation(Situation504.class);
        addSituation(Situation504.class);

        addSituation(Situation505.class);
        addSituation(Situation505.class);
        addSituation(Situation505.class);
        addSituation(Situation505.class);

        addSituation(Situation506.class);
        addSituation(Situation506.class);
        addSituation(Situation506.class);
        addSituation(Situation506.class);

        addSituation(Situation507.class);

        addSituation(Situation508.class);
        addSituation(Situation508.class);
        addSituation(Situation508.class);
        addSituation(Situation508.class);

        addSituation(Situation507.class);

        addSituation(Situation509.class);
        addSituation(Situation509.class);
        addSituation(Situation509.class);
        addSituation(Situation509.class);

        addSituation(Situation507.class);

        addSituation(Situation510.class);
        addSituation(Situation510.class);
        addSituation(Situation510.class);
        addSituation(Situation510.class);

        addSituation(Situation507.class);

        addSituation(Situation599.class);

        setOrbStartPosition(6, 9);
    }

}
