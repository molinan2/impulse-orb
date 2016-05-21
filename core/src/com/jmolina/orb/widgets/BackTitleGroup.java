package com.jmolina.orb.widgets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Disposable;
import com.jmolina.orb.var.Utils;

public class BackTitleGroup extends Group implements Disposable {

    private Image button;
    private Texture buttonTexture;
    private Image title;
    private Texture titleTexture;

    public BackTitleGroup() {
        buttonTexture = new Texture(Gdx.files.internal("back.png"));
        titleTexture = new Texture(Gdx.files.internal("title.png"));
        button = new Image(buttonTexture);
        title = new Image(titleTexture);
        button.setPosition(0f, 0f);
        title.setPosition(Utils.xGrid(3), 0f);
        addActor(button);
        addActor(title);
    }

    @Override
    public void dispose() {
        buttonTexture.dispose();
        titleTexture.dispose();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        // batch.draw(this.texture, this.getX(), this.getY());

        // button.draw(batch, parentAlpha);
        // title.draw(batch, parentAlpha);

        super.draw(batch, parentAlpha);
    }
}
