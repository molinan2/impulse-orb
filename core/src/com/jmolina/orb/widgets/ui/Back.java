package com.jmolina.orb.widgets.ui;

import com.jmolina.orb.managers.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.jmolina.orb.var.Asset;
import com.jmolina.orb.widgets.BaseGroup;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.alpha;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

public class Back extends BaseGroup {

    private Image image, frame;

    public Back(AssetManager am) {
        super(am);

        image = new Image(getAsset(Asset.UI_BACK, Texture.class));
        image.setPosition(0, 0);
        frame = new Image(getAsset(Asset.UI_BACK_FRAME, Texture.class));
        frame.setPosition(0, 0);
        frame.addAction(alpha(0));
        frame.act(0);

        setFrame(frame);

        addActor(image);
        addActor(frame);
    }

}
