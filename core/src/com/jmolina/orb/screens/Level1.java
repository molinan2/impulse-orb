package com.jmolina.orb.screens;

import com.jmolina.orb.actors.Ball;
import com.jmolina.orb.actors.Element;
import com.jmolina.orb.interfaces.SuperManager;

public class Level1 extends LevelBaseScreen {

    public Level1(SuperManager superManager) {
        super(superManager);

        addElement(new Ball(getWorld(), 3, 10, 0.25f));
    }

}
