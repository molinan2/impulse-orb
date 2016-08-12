package com.jmolina.orb.widgets.ui;

import com.jmolina.orb.managers.AssetManager;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.jmolina.orb.utils.Utils;
import com.jmolina.orb.var.Atlas;
import com.jmolina.orb.widgets.BaseGroup;

public class PauseButton extends BaseGroup {

    private Image image;
    private TextureRegionDrawable pauseDrawable;
    private TextureRegionDrawable resumeDrawable;
    private boolean paused;

    public PauseButton(AssetManager am) {
        super(am);

        paused = false;
        pauseDrawable = new TextureRegionDrawable(findRegion(Atlas.HUD_BUTTON_PAUSE));
        resumeDrawable = new TextureRegionDrawable(findRegion(Atlas.HUD_BUTTON_RESUME));
        image = new Image(pauseDrawable);
        image.setPosition(0f, 0f);

        addActor(image);

        setTransform(false);
        setHeight(Utils.cell(1.5f));
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

}
