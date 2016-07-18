package com.jmolina.orb.widgets;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.jmolina.orb.assets.Asset;
import com.jmolina.orb.utils.Grid;

public class PauseButton extends BaseGroup {

    private Image image;
    private TextureRegionDrawable pauseDrawable;
    private TextureRegionDrawable resumeDrawable;
    private boolean paused;

    public PauseButton(AssetManager am) {
        super(am);


        paused = false;
        pauseDrawable = new TextureRegionDrawable(new TextureRegion(getAsset(Asset.HUD_PAUSE, Texture.class)));
        resumeDrawable = new TextureRegionDrawable(new TextureRegion(getAsset(Asset.HUD_PAUSE_RESUME, Texture.class)));
        image = new Image(pauseDrawable);
        image.setPosition(0f, 0f);

        addActor(image);

        setTransform(false);
        setHeight(Grid.unit(1.5f));
        setOrigin(image.getWidth() * 0.5f, image.getHeight() * 0.5f);
    }

    public void pause () {
        paused = true;
        image.setDrawable(resumeDrawable);
    }

    public void resume () {
        paused = false;
        image.setDrawable(pauseDrawable);
    }

    public void toggle () {
        if (paused)
            resume();
        else
            pause();
    }

}
