package com.jmolina.orb.widgets.ui;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.jmolina.orb.managers.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.jmolina.orb.utils.Utils;
import com.jmolina.orb.var.Asset;
import com.jmolina.orb.widgets.BaseGroup;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

public class GameTitle extends BaseGroup {

    private Image text, orb;
    private int clicks;

    public GameTitle(AssetManager am) {
        super(am);

        clicks = 0;
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
        addListener(getEasterListener());
    }

    /**
     * Small easter egg xD
     *
     * @return A listener performing an animation over {@link #orb}
     */
    private ClickListener getEasterListener() {
        return new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                clicks++;

                orb.addAction(Actions.rotateBy(
                        360 * (float) Math.random(),
                        1,
                        Interpolation.sineOut
                ));

                if (clicks > 7) {
                    clicks = 0;
                    orb.addAction(sequence(
                            Actions.moveBy(Utils.cell(-4), 0, 0.75f, Interpolation.sineIn),
                            Actions.moveTo(Utils.cell(12), orb.getY(), 0),
                            parallel(
                                    Actions.moveTo(0, 0, 2, Interpolation.sineOut),
                                    Actions.rotateBy(720, 2, Interpolation.sineOut)
                            )
                    ));
                }
            }
        };
    }

}
