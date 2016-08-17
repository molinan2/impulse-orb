package com.jmolina.orb.widgets.game;

import com.jmolina.orb.managers.AssetManager;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.jmolina.orb.var.Utils;
import com.jmolina.orb.var.Atlas;
import com.jmolina.orb.widgets.BaseGroup;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.alpha;

public class PauseButton extends BaseGroup {

    private Image image, frame;
    private TextureRegionDrawable pauseDrawable;
    private TextureRegionDrawable resumeDrawable;

    public PauseButton(AssetManager am) {
        super(am);

        pauseDrawable = new TextureRegionDrawable(findRegion(Atlas.HUD_BUTTON_PAUSE));
        resumeDrawable = new TextureRegionDrawable(findRegion(Atlas.HUD_BUTTON_RESUME));
        image = new Image(pauseDrawable);
        image.setPosition(0f, 0f);

        frame = new Image(findRegion(Atlas.HUD_BUTTON_FRAME));
        frame.setPosition(0, 0);
        frame.addAction(alpha(0));
        frame.act(0);

        addActor(image);
        addActor(frame);

        setFrame(frame);

        setTransform(false);
        setHeight(Utils.cell(1.5f));
        setOrigin(image.getWidth() * 0.5f, image.getHeight() * 0.5f);
    }

    public void pause () {
        image.setDrawable(resumeDrawable);
    }

    public void resume () {
        image.setDrawable(pauseDrawable);
    }

}
