package com.jmolina.orb.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class TestBox extends BaseActor {

    public TestBox() {
        super(new Texture(Gdx.files.internal("box.png"), true));
    }

}
