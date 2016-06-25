package com.jmolina.orb.screens;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.jmolina.orb.elements.Ball;
import com.jmolina.orb.elements.Box;
import com.jmolina.orb.elements.Element;
import com.jmolina.orb.interfaces.SuperManager;


public class Level1 extends LevelBaseScreen {

    public Level1(SuperManager superManager) {
        super(superManager);

        Ball.BallConfig ballConfig = new Ball.BallConfig(getAssetManager(), getWorld(), SCENE_GRID_UNIT);
        Box.BoxConfig boxConfig = new Box.BoxConfig(getAssetManager(), getWorld(), SCENE_GRID_UNIT);

        // Esquinas con rojo
        boxConfig.setPosition(0, 7);
        boxConfig.setSize(7, 7);
        boxConfig.rotation = 45;
        boxConfig.type = Element.Type.GREY;
        addElement(new Box(boxConfig));

        boxConfig.setPosition(2.5f, 9.5f);
        boxConfig.setSize(7, 0.5f);
        boxConfig.rotation = -45;
        boxConfig.type = Element.Type.RED;
        addElement(new Box(boxConfig));

        boxConfig.setPosition(12, 12);
        boxConfig.setSize(7, 7);
        boxConfig.rotation = -45;
        boxConfig.type = Element.Type.GREY;
        addElement(new Box(boxConfig));

        boxConfig.setPosition(9.5f, 14.5f);
        boxConfig.setSize(7, 0.5f);
        boxConfig.rotation = 45;
        boxConfig.type = Element.Type.RED;
        addElement(new Box(boxConfig));

        // Bola obstaculo
        ballConfig.setPosition(6, 14);
        ballConfig.setDiameter(4);
        ballConfig.type = Element.Type.GREY;
        addElement(new Ball(ballConfig));

        // Bordes
        boxConfig.setPosition(6, 0.5f);
        boxConfig.setSize(12, 1);
        boxConfig.rotation = 0;
        boxConfig.type = Element.Type.BLACK;
        addElement(new Box(boxConfig));

        boxConfig.setPosition(0.25f, 9);
        boxConfig.setSize(0.5f, 18);
        boxConfig.rotation = 0;
        boxConfig.type = Element.Type.BLACK;
        addElement(new Box(boxConfig));

        boxConfig.setPosition(11.75f, 9);
        boxConfig.setSize(0.5f, 18);
        boxConfig.rotation = 0;
        boxConfig.type = Element.Type.BLACK;
        addElement(new Box(boxConfig));

        // Rojos laterales
        boxConfig.setPosition(0.75f, 10);
        boxConfig.setSize(0.5f, 4);
        boxConfig.rotation = 0;
        boxConfig.type = Element.Type.RED;
        addElement(new Box(boxConfig));

        boxConfig.setPosition(11.25f, 10);
        boxConfig.setSize(0.5f, 4);
        boxConfig.rotation = 0;
        boxConfig.type = Element.Type.RED;
        addElement(new Box(boxConfig));

        // Bola que cae
        ballConfig.setPosition(5, 18);
        ballConfig.setDiameter(1);
        ballConfig.type = Element.Type.DUMMY;
        addElement(new Ball(ballConfig, BodyDef.BodyType.DynamicBody));
    }

}
