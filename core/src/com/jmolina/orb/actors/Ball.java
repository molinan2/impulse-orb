package com.jmolina.orb.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Ball extends BaseActor {

    public Ball() {
        super(new Texture(Gdx.files.internal("ball.png"), true));
    }

}
