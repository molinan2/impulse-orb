package com.jmolina.orb.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;

/**
 * Test class
 */
public class LadderActor extends Actor {

    // TODO Warning: esta textura no es disposable

    Texture texture;

    public LadderActor() {
        this.texture = new Texture(Gdx.files.internal("ladder.png"));
        this.setPosition(0f, 0f);
        this.setBounds(this.getX(), this.getY(), texture.getWidth(), texture.getHeight());
        this.setVisible(true);
        this.setTouchable(Touchable.enabled);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        // super.draw(batch, parentAlpha);
        batch.draw(this.texture, this.getX(), this.getY());
    }

}
