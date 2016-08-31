package com.jmolina.orb.screens.levels;

import com.jmolina.orb.interfaces.SuperManager;
import com.jmolina.orb.managers.ScreenManager;
import com.jmolina.orb.screens.Level;
import com.jmolina.orb.situations.god.*;

/**
 * Nivel 5
 */
public class Level5 extends Level {

    /**
     * Consntructor
     *
     * @param sm SuperManager
     */
    public Level5(SuperManager sm) {
        super(sm);

        setPreviousScreen(ScreenManager.Key.LAUNCH_5);
        setSuccessScreen(ScreenManager.Key.SUCCESS_5);

        addSituation(Situation500.class);
        addSituation(Situation501.class);
        addSituation(Situation501.class);
        addSituation(Situation501.class);
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
        addSituation(Situation507.class);
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
        addSituation(Situation511.class);
        addSituation(Situation511.class);
        addSituation(Situation511.class);
        addSituation(Situation511.class);
        addSituation(Situation507.class);
        addSituation(Situation599B.class);

        setOrbStartPosition(6, 9);
    }

}
