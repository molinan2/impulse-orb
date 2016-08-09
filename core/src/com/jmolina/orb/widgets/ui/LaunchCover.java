package com.jmolina.orb.widgets.ui;

import com.jmolina.orb.managers.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.jmolina.orb.utils.Utils;
import com.jmolina.orb.var.Asset;
import com.jmolina.orb.widgets.BaseGroup;

public class LaunchCover extends BaseGroup {

    private Image cover, frame;

    public LaunchCover(AssetManager am, Texture texture) {
        super(am);

        frame = new Image(getAsset(Asset.UI_LAUNCH_FRAME, Texture.class));
        cover = new Image(texture);

        addActor(cover);
        addActor(frame);

        setSize(Utils.cell(10), Utils.cell(6.5f));
    }

}
