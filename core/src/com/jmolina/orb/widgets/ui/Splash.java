package com.jmolina.orb.widgets.ui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.jmolina.orb.managers.AssetManager;
import com.jmolina.orb.utils.Utils;
import com.jmolina.orb.var.Asset;
import com.jmolina.orb.widgets.BaseGroup;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

public class Splash extends BaseGroup {

    private Image splashBody, splashReflections;

    public Splash(AssetManager am) {
        super(am);

        splashBody = new Image(getAsset(Asset.UI_SPLASH_BODY, Texture.class));
        splashBody.setSize(Utils.cell(8), Utils.cell(8));
        splashBody.setOrigin(Utils.cell(4), Utils.cell(4));
        splashBody.setPosition(Utils.cell(0), Utils.cell(0));
        splashBody.addAction(forever(sequence(
                Actions.delay(0.75f),
                Actions.rotateBy(2 * 360, 2, Interpolation.pow3)
        )));

        splashReflections = new Image(getAsset(Asset.UI_SPLASH_REFLECTIONS, Texture.class));
        splashReflections.setPosition(Utils.cell(0), Utils.cell(0));

        setSize(Utils.cell(8), Utils.cell(8));

        addActor(splashBody);
        addActor(splashReflections);
    }

}
