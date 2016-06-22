package com.jmolina.orb.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Box extends BaseActor {

    public Box() {
        super(new Texture(Gdx.files.internal("box.png"), true));
    }

}
