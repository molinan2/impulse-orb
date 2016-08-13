package com.jmolina.orb.widgets.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.jmolina.orb.managers.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.jmolina.orb.managers.GameManager;
import com.jmolina.orb.managers.PrefsManager;
import com.jmolina.orb.managers.ScreenManager;
import com.jmolina.orb.var.Asset;
import com.jmolina.orb.utils.Utils;
import com.jmolina.orb.var.Atlas;
import com.jmolina.orb.var.Font;
import com.jmolina.orb.var.Var;
import com.jmolina.orb.widgets.BaseGroup;

import java.util.ArrayList;

import static com.jmolina.orb.managers.PrefsManager.*;


/**
 * Ranking Ladder. Por ahora, exactamente con 3 posiciones
 */
public class Ladder extends BaseGroup {

    private String LADDER_1;
    private String LADDER_2;
    private String LADDER_3;

    private Label titleLabel;
    private Image background;
    private ArrayList<LadderRow> rows;
    private ArrayList<Float> times;
    private ArrayList<Integer> ratings;
    private Preferences prefs;
    private GameManager gameManager;

    public Ladder(AssetManager am, PrefsManager pm, GameManager gm, ScreenManager.Key level, String title) {
        super(am);

        gameManager = gm;
        prefs = pm.getPrefs();
        times = getLevelTimes(level);
        ratings = getRatings(level);
        rows = new ArrayList<LadderRow>();

        background = new Image(getAsset(Asset.UI_LADDER_BACKGROUND, Texture.class));
        background.setPosition(0f, 0f);

        Label.LabelStyle style = new Label.LabelStyle();
        style.fontColor = new Color(Var.COLOR_GREEN_DARK);
        style.font = new BitmapFont(
                Gdx.files.internal(Font.FONT_ROBOTO_BOLD_30),
                findRegion(Atlas.FONT_ROBOTO_BOLD_30)
        );

        titleLabel = new Label(title.toUpperCase(), style);
        titleLabel.setPosition(Utils.cell(0.5f), Utils.cell(3.125f));
        titleLabel.setSize(Utils.cell(2), Utils.cell(0.5f));
        titleLabel.setAlignment(Align.left);

        rows.add(new LadderRow(getAssetManager(), 1, times.get(0), ratings.get(0)));
        rows.add(new LadderRow(getAssetManager(), 2, times.get(1), ratings.get(1)));
        rows.add(new LadderRow(getAssetManager(), 3, times.get(2), ratings.get(2)));

        addActor(background);
        addActor(titleLabel);
        addLadderRows(rows);
        setHeight(Utils.cell(4));
    }

    private void addLadderRows(ArrayList<LadderRow> rows) {
        for (int i=0; i<rows.size(); i++) {
            rows.get(i).setPosition(Utils.cell(0.5f), Utils.cell(2f - i * 0.75f));
            addActor(rows.get(i));
        }
    }

    private ArrayList<Float> getLevelTimes(ScreenManager.Key key) {
        detectLevel(key);
        ArrayList<Float> times = new ArrayList<Float>();

        times.add(prefs.getFloat(LADDER_1));
        times.add(prefs.getFloat(LADDER_2));
        times.add(prefs.getFloat(LADDER_3));

        return times;
    }

    private ArrayList<Integer> getRatings(ScreenManager.Key key) {
        ArrayList<Integer> ratings = new ArrayList<Integer>();

        ratings.add(numericRating(key, times.get(0)));
        ratings.add(numericRating(key, times.get(1)));
        ratings.add(numericRating(key, times.get(2)));

        return ratings;
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
            case LEVEL_T1:
                LADDER_1 = LADDER_T1_1;
                LADDER_2 = LADDER_TEST1_2;
                LADDER_3 = LADDER_TEST1_3;
                break;
            case LEVEL_T2:
                LADDER_1 = LADDER_T2_1;
                LADDER_2 = LADDER_TEST2_2;
                LADDER_3 = LADDER_TEST2_3;
                break;
            default:
        }
    }

    private int numericRating(ScreenManager.Key level, float time) {
        switch (level) {
            case LEVEL_1: return gameManager.getNumericRating(1, time);
            case LEVEL_2: return gameManager.getNumericRating(2, time);
            case LEVEL_3: return gameManager.getNumericRating(3, time);
            case LEVEL_4: return gameManager.getNumericRating(4, time);
            case LEVEL_5: return gameManager.getNumericRating(5, time);
            default: return 0;
        }
    }

}
