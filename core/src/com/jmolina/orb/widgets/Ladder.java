package com.jmolina.orb.widgets;

import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.jmolina.orb.managers.PrefsManager;
import com.jmolina.orb.managers.ScreenManager;
import com.jmolina.orb.utils.Grid;
import com.jmolina.orb.assets.Asset;
import com.jmolina.orb.utils.Time;
import com.jmolina.orb.var.Var;

import java.util.ArrayList;

import static com.jmolina.orb.managers.PrefsManager.*;


/**
 * Ranking Ladder. Por ahora, exactamente con 3 posiciones
 */
public class Ladder extends BaseGroup {

    private String LADDER_1;
    private String LADDER_2;
    private String LADDER_3;

    private Label title;
    private Image bg;
    private ArrayList<LadderRow> rows;
    private Preferences prefs;
    private ArrayList<Float> times;

    public Ladder(AssetManager am, PrefsManager pm, ScreenManager.Key levelKey, String title) {
        super(am);

        this.prefs = pm.getPrefs();
        this.times = getLevelTimes(levelKey);

        this.rows = new ArrayList<LadderRow>();

        this.bg = new Image(getAsset(Asset.UI_LADDER_BACKGROUND, Texture.class));
        this.bg.setPosition(0f, 0f);

        Label.LabelStyle style = new Label.LabelStyle();
        style.fontColor = new Color(Var.COLOR_BLUE);
        style.font = getAsset(Asset.FONT_ROBOTO_BOLD_30, BitmapFont.class);

        this.title = new Label(title.toUpperCase(), style);
        this.title.setPosition(Grid.unit(0.5f), Grid.unit(3.125f));
        this.title.setSize(Grid.unit(2), Grid.unit(0.5f));
        this.title.setAlignment(Align.left);

        this.rows.add(new LadderRow(getAssetManager(), "1", getLevelTime(0), "MyUserName", "(13)"));
        this.rows.add(new LadderRow(getAssetManager(), "2", getLevelTime(1), "MyUserName", "(21)"));
        this.rows.add(new LadderRow(getAssetManager(), "3", getLevelTime(2), "MyUserName", "(18)"));

        addActor(this.bg);
        addActor(this.title);
        addLadderRows(rows);
        setHeight(Grid.unit(4));
    }

    @Override
    public void dispose() {
        for(LadderRow row : rows)
            row.dispose();

        super.dispose();
    }

    private void addLadderRows(ArrayList<LadderRow> rows) {
        for (int i=0; i<rows.size(); i++) {
            rows.get(i).setPosition(Grid.unit(0.5f), Grid.unit(2f - i * 0.75f));
            addActor(rows.get(i));
        }
    }

    private String getLevelTime(int index) {
        float time = times.get(index);

        if (time > 0)
            return Time.formatTime(time);
        else
            return "--";
    }

    private ArrayList<Float> getLevelTimes(ScreenManager.Key key) {
        detectLevel(key);
        ArrayList<Float> times = new ArrayList<Float>();

        times.add(prefs.getFloat(LADDER_1));
        times.add(prefs.getFloat(LADDER_2));
        times.add(prefs.getFloat(LADDER_3));

        return times;
    }

    private void detectLevel(ScreenManager.Key key) {
        switch (key) {
            case LEVEL_1:
                LADDER_1 = LADDER_1_1;
                LADDER_2 = LADDER_1_2;
                LADDER_3 = LADDER_1_3;
                break;
            case LEVEL_2:
                LADDER_1 = LADDER_2_1;
                LADDER_2 = LADDER_2_2;
                LADDER_3 = LADDER_2_3;
                break;
            case LEVEL_3:
                LADDER_1 = LADDER_3_1;
                LADDER_2 = LADDER_3_2;
                LADDER_3 = LADDER_3_3;
                break;
            case LEVEL_4:
                LADDER_1 = LADDER_4_1;
                LADDER_2 = LADDER_4_2;
                LADDER_3 = LADDER_4_3;
                break;
            case LEVEL_5:
                LADDER_1 = LADDER_5_1;
                LADDER_2 = LADDER_5_2;
                LADDER_3 = LADDER_5_3;
                break;
            default:
        }
    }

}
