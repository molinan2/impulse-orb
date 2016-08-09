package com.jmolina.orb.widgets.ui;

import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.jmolina.orb.managers.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.jmolina.orb.utils.Utils;
import com.jmolina.orb.var.Asset;
import com.jmolina.orb.widgets.BaseGroup;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.rotateBy;

public class GameTitle extends BaseGroup {

    private Image text, orb;

    public GameTitle(AssetManager am) {
        super(am);

        text = new Image(getAsset(Asset.UI_MAIN_TITLE_TEXT, Texture.class));
        text.setPosition(0, 0);

        orb = new Image(getAsset(Asset.UI_MAIN_TITLE_ORB, Texture.class));
        orb.setPosition(0, 0);
        orb.setSize(Utils.cell(3), Utils.cell(3));
        orb.setOrigin(Utils.cell(1.5f), Utils.cell(1.5f));
        orb.addAction(Actions.rotateBy(90 * (float) Math.random()));
        orb.act(0);
        orb.addAction(forever(sequence(
                Actions.rotateBy(360, 24)
        )));

        addActor(orb);
        addActor(text);

        setSize(Utils.cell(10), Utils.cell(3));
    }

}
