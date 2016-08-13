package com.jmolina.orb.widgets.misc;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.jmolina.orb.managers.AssetManager;
import com.jmolina.orb.utils.Utils;
import com.jmolina.orb.var.Asset;
import com.jmolina.orb.var.Atlas;
import com.jmolina.orb.var.Font;
import com.jmolina.orb.var.Var;


/**
 * Class for metering and displaying render time.
 */
public class DebugTime {

    private long nanotimeStart = 0;
    private long nanotimeEnd = 0;
    private long nanotime = 0;
    private long nanotimeAcc = 0;
    private float nanotimeAvg = 0;
    private int frames = 0;
    private SpriteBatch spriteBatch;
    private Label frametime;

    public DebugTime(AssetManager am) {
        spriteBatch = new SpriteBatch();

        Label.LabelStyle style = new Label.LabelStyle();
        style.fontColor = new Color(Var.COLOR_RED);
        style.font = new BitmapFont(
                Gdx.files.internal(Font.FONT_ROBOTO_BOLD_45),
                am.getGameAtlas().findRegion(Atlas.FONT_ROBOTO_BOLD_45)
        );

        frametime = new Label("", style);
        frametime.setPosition(Utils.cell(1), Utils.cell(1));
    }

    /**
     * Comienza a medir el tiempo.
     */
    public void start() {
        nanotimeStart = System.nanoTime();
    }

    /**
     * Termina de medir el tiempo y lo muestra en pantalla.
     */
    public void end() {
        nanotimeEnd = System.nanoTime();
        nanotime = nanotimeEnd - nanotimeStart;
        nanotimeAcc += nanotime;
        frames++;
        nanotimeAvg = (float) (nanotimeAcc) / (float) frames;
        frametime.setText(String.format("%.2f", nanotimeAvg / 1000000f));

        draw();
    }

    /**
     * Dibuja el tiempo en pantalla.
     */
    private void draw() {
        spriteBatch.begin();
        frametime.draw(spriteBatch, 1);
        spriteBatch.end();
    }

}
