package com.jmolina.orb.screens;

import com.jmolina.orb.interfaces.SuperManager;
import com.jmolina.orb.managers.ScreenManager;
import com.jmolina.orb.situations.Situation100;
import com.jmolina.orb.situations.Situation101;
import com.jmolina.orb.situations.Situation102;
import com.jmolina.orb.situations.Situation103;
import com.jmolina.orb.situations.Situation104;
import com.jmolina.orb.situations.Situation105;
import com.jmolina.orb.situations.Situation106;
import com.jmolina.orb.situations.Situation107;
import com.jmolina.orb.situations.Situation199;


public class Level1 extends LevelScreen {

    public Level1(SuperManager superManager) {
        super(superManager);

        setReturningScreen(ScreenManager.Key.LEVEL_LAUNCH_1);

        addSituation(new Situation100(getAssetManager(), getWorld())); // TODO Revisar PPM
        addSituation(new Situation101(getAssetManager(), getWorld()));
        addSituation(new Situation102(getAssetManager(), getWorld()));
        addSituation(new Situation103(getAssetManager(), getWorld()));
        addSituation(new Situation104(getAssetManager(), getWorld()));
        addSituation(new Situation105(getAssetManager(), getWorld()));
        addSituation(new Situation106(getAssetManager(), getWorld()));
        addSituation(new Situation107(getAssetManager(), getWorld()));
        addSituation(new Situation199(getAssetManager(), getWorld()));

        getOrb().setPosition(6, 9);
    }

}
