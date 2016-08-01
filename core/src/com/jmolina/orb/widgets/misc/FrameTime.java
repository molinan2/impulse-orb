package com.jmolina.orb.widgets.misc;

import com.jmolina.orb.managers.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.TimeUtils;
import com.jmolina.orb.utils.Utils;
import com.jmolina.orb.var.Asset;
import com.jmolina.orb.widgets.BaseGroup;

import java.util.Locale;

/**
 * Porquería de clase para medir el tiempo de render
 */
public class FrameTime extends BaseGroup {

    private Label label;
    private long first_start = 0, start, end, current, max = 0;
    private boolean enter = true;

    public FrameTime(AssetManager am) {
        super(am);

        Label.LabelStyle style = new Label.LabelStyle();
        style.fontColor = new Color(Color.BLUE);
        style.font = getAsset(Asset.FONT_ROBOTO_REGULAR_30, BitmapFont.class);

        label = new Label("", style);
        label.setTouchable(Touchable.disabled);
        label.setAlignment(Align.right);
        label.setPosition(0f, 0f);
        label.setSize(Utils.cell(4), Utils.cell(1f));

        setHeight(label.getPrefHeight());
        addActor(label);
    }

    public void start () {
        this.start = TimeUtils.nanoTime();
        if (this.first_start == 0) this.first_start = this.start;

        if (this.start - this.first_start > 1000000000 && this.enter) {
            this.max = 0;
            this.enter = false;
        }
    }

    public void end () {
        this.end = TimeUtils.nanoTime();
        this.current = this.end - this.start;

        if (this.current > this.max) this.max = this.current;

        float current = (float) this.current / 1000000f;
        float max = (float) this.max / 1000000f;

        String text = String.format(new Locale(""), "%02.2f\n%02.2f", max, current);
        label.setText(text);
    }

}
