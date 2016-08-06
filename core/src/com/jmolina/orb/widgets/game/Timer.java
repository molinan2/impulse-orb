package com.jmolina.orb.widgets.game;

import com.badlogic.gdx.Gdx;
import com.jmolina.orb.managers.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.jmolina.orb.utils.Utils;
import com.jmolina.orb.var.Asset;
import com.jmolina.orb.var.Var;
import com.jmolina.orb.widgets.BaseGroup;

public class Timer extends BaseGroup {

    private Label label;
    private float time;

    public Timer(AssetManager am) {
        super(am);

        Label.LabelStyle style = new Label.LabelStyle();
        style.fontColor = new Color(Var.COLOR_WHITE);
        style.font = getAsset(Asset.FONT_ROBOTO_BOLD_90, BitmapFont.class);

        label = new Label("", style);
        label.setTouchable(Touchable.disabled);
        label.setPosition(0, 0);
        label.setHeight(Utils.cell(1.5f));
        label.setWidth(Utils.cell(6));
        label.setAlignment(Align.center);

        addActor(label);

        setTransform(false);
        setHeight(Utils.cell(1.5f));
        reset();
    }

    public void update() {
        time += Gdx.graphics.getRawDeltaTime();
        updateLabel();
    }

    public float getTime () {
        return time;
    }

    public void reset() {
        time = 0f;
        updateLabel();
    }

    private void updateLabel() {
        label.setText(Utils.formatTime(time));
    }

}
