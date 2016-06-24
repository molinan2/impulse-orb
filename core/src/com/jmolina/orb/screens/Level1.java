package com.jmolina.orb.screens;

import com.jmolina.orb.actors.Ball;
import com.jmolina.orb.actors.Box;
import com.jmolina.orb.interfaces.SuperManager;

public class Level1 extends LevelBaseScreen {

    public Level1(SuperManager superManager) {
        super(superManager);

        addElement(new Ball(getWorld(), u(4f), u(10), u(2)));
        addElement(new Ball(getWorld(), u(8), u(4), u(1)));
        addElement(new Box(getWorld(), u(6), u(2), u(3.5f), u(0.5f)));
    }

}
