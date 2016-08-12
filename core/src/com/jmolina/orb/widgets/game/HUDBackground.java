package com.jmolina.orb.widgets.game;

import com.jmolina.orb.managers.AssetManager;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.jmolina.orb.utils.Utils;
import com.jmolina.orb.var.Atlas;
import com.jmolina.orb.widgets.BaseGroup;

public class HUDBackground extends BaseGroup {

    private Image border, overlay;

    public HUDBackground(AssetManager am) {
        super(am);

        overlay = new Image(findRegion(Atlas.HUD_BACKGROUND_OVERLAY));
        overlay.setPosition(0, 0);

        border = new Image(findRegion(Atlas.HUD_BACKGROUND_BORDER));
        border.setPosition(0, 0);

        addActor(overlay);
        addActor(border);

        overlay.setScale(24, 28);
        border.setScaleX(24);
        // setSize(Utils.cell(24), Utils.cell(2.5f));
        setOrigin(Utils.cell(12), Utils.cell(14));
    }

}
