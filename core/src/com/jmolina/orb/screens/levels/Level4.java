package com.jmolina.orb.screens.levels;

import com.jmolina.orb.interfaces.SuperManager;
import com.jmolina.orb.managers.ScreenManager;
import com.jmolina.orb.screens.Level;
import com.jmolina.orb.situations.hero.*;


public class Level4 extends Level {

    public Level4(SuperManager sm) {
        super(sm);

        setPreviousScreen(ScreenManager.Key.LAUNCH_4);
        setSuccessScreen(ScreenManager.Key.SUCCESS_4);

        addSituation(Situation400.class);

        addSituation(Situation401.class);
        addSituation(Situation401.class);

        addSituation(Situation409.class);

        addSituation(Situation402.class);
        addSituation(Situation402.class);
        addSituation(Situation402.class);
        addSituation(Situation402.class);

        addSituation(Situation409.class);

        addSituation(Situation403.class);
        addSituation(Situation403.class);
        addSituation(Situation403.class);
        addSituation(Situation403.class);

        addSituation(Situation409.class);

        addSituation(Situation404.class);
        addSituation(Situation404.class);
        addSituation(Situation404.class);
        addSituation(Situation404.class);

        addSituation(Situation405.class);
        addSituation(Situation405.class);
        addSituation(Situation405.class);
        addSituation(Situation405.class);

        addSituation(Situation409.class);

        addSituation(Situation406.class);
        addSituation(Situation406.class);
        addSituation(Situation406.class);
        addSituation(Situation406.class);

        addSituation(Situation407.class);
        addSituation(Situation407.class);
        addSituation(Situation407.class);
        addSituation(Situation407.class);

        addSituation(Situation409.class);

        addSituation(Situation408.class);
        addSituation(Situation408.class);
        addSituation(Situation408.class);
        addSituation(Situation408.class);
        addSituation(Situation408.class);
        addSituation(Situation408.class);
        addSituation(Situation408.class);
        addSituation(Situation408.class);

        addSituation(Situation499.class);

        setOrbStartPosition(6, 9);
    }

}
