package com.jmolina.orb.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class TestBall extends BaseActor {

    public TestBall() {
        super(new Texture(Gdx.files.internal("ball.png"), true));
    }

}
