package com.jmolina.orb.screens.levels;

import com.jmolina.orb.interfaces.SuperManager;
import com.jmolina.orb.managers.ScreenManager;
import com.jmolina.orb.screens.Level;
import com.jmolina.orb.situations.expert.*;

/**
 * Nivel 3
 */
public class Level3 extends Level {

    /**
     * Consntructor
     *
     * @param sm SuperManager
     */
    public Level3(SuperManager sm) {
        super(sm);

        setPreviousScreen(ScreenManager.Key.LAUNCH_3);
        setSuccessScreen(ScreenManager.Key.SUCCESS_3);

        addSituation(Situation300.class);
        addSituation(Situation302.class);
        addSituation(Situation302.class);
        addSituation(Situation302.class);
        addSituation(Situation301.class);
        addSituation(Situation301.class);
        addSituation(Situation301.class);
        addSituation(Situation303.class);
        addSituation(Situation303.class);
        addSituation(Situation303.class);
        addSituation(Situation304.class);
        addSituation(Situation304.class);
        addSituation(Situation304.class);
        addSituation(Situation304.class);
        addSituation(Situation304.class);
        addSituation(Situation304.class);
        addSituation(Situation304.class);
        addSituation(Situation304.class);
        addSituation(Situation305.class);
        addSituation(Situation306.class);
        addSituation(Situation306.class);
        addSituation(Situation306.class);
        addSituation(Situation306.class);
        addSituation(Situation306.class);
        addSituation(Situation306.class);
        addSituation(Situation306.class);
        addSituation(Situation306.class);
        addSituation(Situation305.class);
        addSituation(Situation307.class);
        addSituation(Situation307.class);
        addSituation(Situation307.class);
        addSituation(Situation307.class);
        addSituation(Situation305.class);
        addSituation(Situation308R.class);
        addSituation(Situation308R.class);
        addSituation(Situation308R.class);
        addSituation(Situation308R.class);
        addSituation(Situation308L.class);
        addSituation(Situation308L.class);
        addSituation(Situation308L.class);
        addSituation(Situation308L.class);
        addSituation(Situation301.class);
        addSituation(Situation309.class);
        addSituation(Situation309.class);
        addSituation(Situation309.class);
        addSituation(Situation309.class);
        addSituation(Situation305.class);
        addSituation(Situation310.class);
        addSituation(Situation310.class);
        addSituation(Situation310.class);
        addSituation(Situation310.class);
        addSituation(Situation301.class);
        addSituation(Situation311.class);
        addSituation(Situation311.class);
        addSituation(Situation311.class);
        addSituation(Situation301.class);
        addSituation(Situation303.class);
        addSituation(Situation303.class);
        addSituation(Situation399.class);

        setOrbStartPosition(6, 9);
    }

}
