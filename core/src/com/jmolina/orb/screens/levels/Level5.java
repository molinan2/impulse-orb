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

        addSituation(new Situation500(getAssetManager(), getWorld(), getPixelsPerMeter()));

        addSituation(new Situation501(getAssetManager(), getWorld(), getPixelsPerMeter()));
        addSituation(new Situation501(getAssetManager(), getWorld(), getPixelsPerMeter()));
        addSituation(new Situation501(getAssetManager(), getWorld(), getPixelsPerMeter()));
        addSituation(new Situation501(getAssetManager(), getWorld(), getPixelsPerMeter()));

        addSituation(new Situation502(getAssetManager(), getWorld(), getPixelsPerMeter()));
        addSituation(new Situation502(getAssetManager(), getWorld(), getPixelsPerMeter()));
        addSituation(new Situation502(getAssetManager(), getWorld(), getPixelsPerMeter()));
        addSituation(new Situation502(getAssetManager(), getWorld(), getPixelsPerMeter()));

        addSituation(new Situation503(getAssetManager(), getWorld(), getPixelsPerMeter()));
        addSituation(new Situation503(getAssetManager(), getWorld(), getPixelsPerMeter()));
        addSituation(new Situation503(getAssetManager(), getWorld(), getPixelsPerMeter()));
        addSituation(new Situation503(getAssetManager(), getWorld(), getPixelsPerMeter()));

        addSituation(new Situation507(getAssetManager(), getWorld(), getPixelsPerMeter()));

        addSituation(new Situation504(getAssetManager(), getWorld(), getPixelsPerMeter()));
        addSituation(new Situation504(getAssetManager(), getWorld(), getPixelsPerMeter()));
        addSituation(new Situation504(getAssetManager(), getWorld(), getPixelsPerMeter()));
        addSituation(new Situation504(getAssetManager(), getWorld(), getPixelsPerMeter()));

        addSituation(new Situation505(getAssetManager(), getWorld(), getPixelsPerMeter()));
        addSituation(new Situation505(getAssetManager(), getWorld(), getPixelsPerMeter()));
        addSituation(new Situation505(getAssetManager(), getWorld(), getPixelsPerMeter()));
        addSituation(new Situation505(getAssetManager(), getWorld(), getPixelsPerMeter()));

        addSituation(new Situation506(getAssetManager(), getWorld(), getPixelsPerMeter()));
        addSituation(new Situation506(getAssetManager(), getWorld(), getPixelsPerMeter()));
        addSituation(new Situation506(getAssetManager(), getWorld(), getPixelsPerMeter()));
        addSituation(new Situation506(getAssetManager(), getWorld(), getPixelsPerMeter()));

        addSituation(new Situation507(getAssetManager(), getWorld(), getPixelsPerMeter()));

        addSituation(new Situation508(getAssetManager(), getWorld(), getPixelsPerMeter()));
        addSituation(new Situation508(getAssetManager(), getWorld(), getPixelsPerMeter()));
        addSituation(new Situation508(getAssetManager(), getWorld(), getPixelsPerMeter()));
        addSituation(new Situation508(getAssetManager(), getWorld(), getPixelsPerMeter()));

        addSituation(new Situation507(getAssetManager(), getWorld(), getPixelsPerMeter()));

        addSituation(new Situation509(getAssetManager(), getWorld(), getPixelsPerMeter()));
        addSituation(new Situation509(getAssetManager(), getWorld(), getPixelsPerMeter()));
        addSituation(new Situation509(getAssetManager(), getWorld(), getPixelsPerMeter()));
        addSituation(new Situation509(getAssetManager(), getWorld(), getPixelsPerMeter()));

        addSituation(new Situation507(getAssetManager(), getWorld(), getPixelsPerMeter()));

        addSituation(new Situation510(getAssetManager(), getWorld(), getPixelsPerMeter()));
        addSituation(new Situation510(getAssetManager(), getWorld(), getPixelsPerMeter()));
        addSituation(new Situation510(getAssetManager(), getWorld(), getPixelsPerMeter()));
        addSituation(new Situation510(getAssetManager(), getWorld(), getPixelsPerMeter()));

        addSituation(new Situation507(getAssetManager(), getWorld(), getPixelsPerMeter()));

        addSituation(new Situation599(getAssetManager(), getWorld(), getPixelsPerMeter()));

        setOrbStartPosition(6, 9);
    }

}
