package com.jmolina.orb.screens;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.jmolina.orb.elements.Ball;
import com.jmolina.orb.elements.Element;
import com.jmolina.orb.interfaces.SuperManager;
import com.jmolina.orb.managers.ScreenManager;
import com.jmolina.orb.situations.Situation100;
import com.jmolina.orb.situations.Situation101;
import com.jmolina.orb.situations.Situation102;
import com.jmolina.orb.situations.Situation103;
import com.jmolina.orb.situations.Situation104;


public class Level1 extends LevelBaseScreen {

    public Level1(SuperManager superManager) {
        super(superManager);

        setReturningScreen(ScreenManager.Key.LEVEL_LAUNCH_1);

        addSituation(new Situation100(getAssetManager(), getWorld(), WORLD_GRID_UNIT));
        addSituation(new Situation101(getAssetManager(), getWorld(), WORLD_GRID_UNIT));
        addSituation(new Situation102(getAssetManager(), getWorld(), WORLD_GRID_UNIT));
        addSituation(new Situation103(getAssetManager(), getWorld(), WORLD_GRID_UNIT));
        addSituation(new Situation104(getAssetManager(), getWorld(), WORLD_GRID_UNIT));

        // Bola que cae
        Ball.BallConfig ballConfig = new Ball.BallConfig(getAssetManager(), getWorld(), WORLD_GRID_UNIT);
        ballConfig.setPosition(6, 9);
        ballConfig.setDiameter(1);
        ballConfig.type = Element.Type.DUMMY;
        setOrb(new Ball(ballConfig, BodyDef.BodyType.DynamicBody));
        addElement(getOrb());
    }

}
