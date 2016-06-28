package com.jmolina.orb.screens;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.jmolina.orb.elements.Ball;
import com.jmolina.orb.elements.Element;
import com.jmolina.orb.elements.Orb;
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


public class Level1 extends LevelBaseScreen {

    public Level1(SuperManager superManager) {
        super(superManager);

        setReturningScreen(ScreenManager.Key.LEVEL_LAUNCH_1);

        addSituation(new Situation100(getAssetManager(), getWorld(), WORLD_GRID_UNIT)); // TODO PPM
        addSituation(new Situation101(getAssetManager(), getWorld(), WORLD_GRID_UNIT));
        addSituation(new Situation102(getAssetManager(), getWorld(), WORLD_GRID_UNIT));
        addSituation(new Situation103(getAssetManager(), getWorld(), WORLD_GRID_UNIT));
        addSituation(new Situation104(getAssetManager(), getWorld(), WORLD_GRID_UNIT));
        addSituation(new Situation105(getAssetManager(), getWorld(), WORLD_GRID_UNIT));
        addSituation(new Situation106(getAssetManager(), getWorld(), WORLD_GRID_UNIT));
        addSituation(new Situation107(getAssetManager(), getWorld(), WORLD_GRID_UNIT));
        addSituation(new Situation199(getAssetManager(), getWorld(), WORLD_GRID_UNIT));

        getOrb().setPosition(6, 9);
    }

}
