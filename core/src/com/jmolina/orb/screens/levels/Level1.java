package com.jmolina.orb.screens.levels;

import com.jmolina.orb.interfaces.SuperManager;
import com.jmolina.orb.managers.ScreenManager;
import com.jmolina.orb.screens.Level;
import com.jmolina.orb.situations.level1.Situation1Start;
import com.jmolina.orb.situations.level1.Situation101;
import com.jmolina.orb.situations.level1.Situation102;
import com.jmolina.orb.situations.level1.Situation103;
import com.jmolina.orb.situations.level1.Situation104;
import com.jmolina.orb.situations.level1.Situation105;
import com.jmolina.orb.situations.level1.Situation106;
import com.jmolina.orb.situations.level1.Situation107;
import com.jmolina.orb.situations.level1.Situation1End;


public class Level1 extends Level {

    private final float DEBUG_EXIT_OFFSET = 8 * 18;

    public Level1(SuperManager sm, ScreenManager.Key key) {
        super(sm, key);

        setPreviousScreen(ScreenManager.Key.LEVEL_LAUNCH_1);
        setSuccessScreen(ScreenManager.Key.LEVEL_SUCCESS_1);

        addSituation(new Situation1Start(getAssetManager(), getWorld(), getPixelsPerMeter()));
        addSituation(new Situation101(getAssetManager(), getWorld(), getPixelsPerMeter()));
        addSituation(new Situation102(getAssetManager(), getWorld(), getPixelsPerMeter()));
        addSituation(new Situation103(getAssetManager(), getWorld(), getPixelsPerMeter()));
        addSituation(new Situation104(getAssetManager(), getWorld(), getPixelsPerMeter()));
        addSituation(new Situation105(getAssetManager(), getWorld(), getPixelsPerMeter()));
        addSituation(new Situation106(getAssetManager(), getWorld(), getPixelsPerMeter()));
        addSituation(new Situation107(getAssetManager(), getWorld(), getPixelsPerMeter()));
        addSituation(new Situation1End(getAssetManager(), getWorld(), getPixelsPerMeter()));

        setOrbStartPosition(6, 9);
    }

}
