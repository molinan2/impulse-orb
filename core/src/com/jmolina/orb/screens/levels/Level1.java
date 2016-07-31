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


public class Level1 extends Level {

    private final float DEBUG_EXIT_OFFSET = 8 * 18;

    public Level1(SuperManager sm, ScreenManager.Key key) {
        super(sm, key);

        setPreviousScreen(ScreenManager.Key.LEVEL_LAUNCH_1);
        setSuccessScreen(ScreenManager.Key.LEVEL_SUCCESS_1);

        addSituation(new SituationT100(getAssetManager(), getWorld(), getPixelsPerMeter()));
        addSituation(new SituationT101(getAssetManager(), getWorld(), getPixelsPerMeter()));
        addSituation(new SituationT102(getAssetManager(), getWorld(), getPixelsPerMeter()));
        addSituation(new SituationT103(getAssetManager(), getWorld(), getPixelsPerMeter()));
        addSituation(new SituationT104(getAssetManager(), getWorld(), getPixelsPerMeter()));
        addSituation(new SituationT105(getAssetManager(), getWorld(), getPixelsPerMeter()));
        addSituation(new SituationT106(getAssetManager(), getWorld(), getPixelsPerMeter()));
        addSituation(new SituationT107(getAssetManager(), getWorld(), getPixelsPerMeter()));
        addSituation(new SituationT199(getAssetManager(), getWorld(), getPixelsPerMeter()));

        setOrbStartPosition(6, 9);
    }

}
