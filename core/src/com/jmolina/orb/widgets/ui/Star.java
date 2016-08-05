package com.jmolina.orb.widgets.ui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.jmolina.orb.managers.AssetManager;
import com.jmolina.orb.utils.Utils;
import com.jmolina.orb.var.Asset;
import com.jmolina.orb.widgets.BaseGroup;

public class Star extends BaseGroup {

    private Image image;

    public Star(AssetManager am, int rank) {
        super(am);

        image = new Image(getTexture(rank));
        image.setPosition(0f, 0f);
        addActor(image);
        setSize(Utils.cell(3.25f), Utils.cell(3));

        if (rank >= 1 && rank <= 3)
            image.setVisible(true);
        else
            image.setVisible(false);
    }

    private Texture getTexture(int rank) {
        switch (rank) {
            case 1: return getAsset(Asset.UI_STAR_1ST, Texture.class);
            case 2: return getAsset(Asset.UI_STAR_2ND, Texture.class);
            case 3: return getAsset(Asset.UI_STAR_3RD, Texture.class);
            default: return getAsset(Asset.UI_STAR_1ST, Texture.class);
        }
    }

}
