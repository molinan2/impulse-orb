package com.jmolina.orb.widgets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.jmolina.orb.assets.Asset;
import com.jmolina.orb.utils.Grid;

import java.util.Locale;

public class Timer extends BaseGroup {

    private final String DEFAULT_TEXT = "00:00.00";

    private Label label;
    private boolean paused = false;
    private float time = 0f;
    private String timeFormated = DEFAULT_TEXT;

    public Timer(AssetManager am) {
        super(am);


        Label.LabelStyle style = new Label.LabelStyle();
        style.fontColor = new Color(BaseGroup.COLOR_WHITE);
        style.font = getAsset(Asset.FONT_ROBOTO_BOLD_90, BitmapFont.class);

        label = new Label(timeFormated, style);
        label.setTouchable(Touchable.disabled);
        label.setPosition(0, 0);
        label.setHeight(Grid.unit(1.5f));
        label.setWidth(Grid.unit(6));
        label.setAlignment(Align.center);

        addActor(label);

        setTransform(false);
        setHeight(Grid.unit(1.5f));
    }

    public void update() {
        if (!paused) {
            time += Gdx.graphics.getRawDeltaTime();
            updateTimeFormated(time);
            label.setText(timeFormated);
        }
    }

    public void pause() {
        paused = true;
    }

    public void resume() {
        paused = false;
    }

    public float getTime () {
        return time;
    }

    public void reset() {
        time = 0f;
        timeFormated = DEFAULT_TEXT;
    }

    public String getTimeFormated () {
        return timeFormated;
    }

    private void updateTimeFormated (float time) {
        int minutes = (int) (time / 60f);
        int seconds = (int) (time - minutes * 60f);
        int decimals = (int) ((time - minutes * 60f - (float) seconds) * 100f);

        String minutesString = String.format(new Locale(""), "%02d", minutes);
        String secondsString = String.format(new Locale(""), "%02d", seconds);
        String decimalsString = String.format(new Locale(""), "%02d", decimals);

        timeFormated = minutesString + ":" + secondsString + "." + decimalsString;
    }

}
