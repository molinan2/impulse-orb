package com.jmolina.orb.widgets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.jmolina.orb.assets.Asset;
import com.jmolina.orb.utils.Grid;

import java.util.Locale;

public class Timer extends BaseGroup {

    private final String DEFAULT_TEXT = "00:00.00";

    private Label label;
    private boolean paused;
    private float time;

    public Timer(AssetManager am) {
        super(am);

        paused = false;
        time = 0f;

        Label.LabelStyle style = new Label.LabelStyle();
        style.fontColor = new Color(BaseGroup.COLOR_WHITE);
        style.font = getAsset(Asset.FONT_ROBOTO_BOLD_90, BitmapFont.class);

        label = new Label(DEFAULT_TEXT, style);
        label.setTouchable(Touchable.disabled);
        label.setPosition(0, 0);
        label.setHeight(Grid.unit(1.5f));
        label.setWidth(Grid.unit(6));
        label.setAlignment(Align.center);

        addActor(label);

        setHeight(Grid.unit(1.5f));
    }

    public void updateTime () {
        if (!paused) {
            time += Gdx.graphics.getRawDeltaTime();

            int minutes = (int) (time / 60f);
            int seconds = (int) (time - minutes * 60f);
            int decimals = (int) ((time - minutes * 60f - (float) seconds) * 100f);

            String minutesString = String.format(new Locale(""), "%02d", minutes);
            String secondsString = String.format(new Locale(""), "%02d", seconds);
            String decimalsString = String.format(new Locale(""), "%02d", decimals);

            label.setText(minutesString + ":" + secondsString + "." + decimalsString);
        }
    }

    public void pause() {
        paused = true;
    }

    public void resume() {
        paused = false;
    }

    public void reset() {
        updateTime();
    }

    public void toggle () {
        if (paused) resume();
        else pause();
    }

}
