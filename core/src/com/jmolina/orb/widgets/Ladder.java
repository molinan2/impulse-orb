package com.jmolina.orb.widgets;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.jmolina.orb.utils.Grid;
import com.jmolina.orb.var.Asset;

import java.util.ArrayList;

/**
 * Ranking Ladder. Por ahora, exactamente con 3 posiciones
 */
public class Ladder extends Base {

    private Label title;
    private Image bg;
    private ArrayList<LadderRow> rows;

    public Ladder(AssetManager am, String title) {
        super(am);

        this.rows = new ArrayList<LadderRow>();

        this.bg = new Image(getAssetManager().get(Asset.UI_LADDER_BACKGROUND, Texture.class));
        this.bg.setPosition(0f, 0f);

        Label.LabelStyle style = new Label.LabelStyle();
        style.fontColor = new Color(Base.COLOR_BLUE);
        style.font = getAssetManager().get(Asset.FONT_ROBOTO_BOLD_30, BitmapFont.class);

        this.title = new Label(title.toUpperCase(), style);
        this.title.setPosition(Grid.unit(0.5f), Grid.unit(3.125f));
        this.title.setSize(Grid.unit(2), Grid.unit(0.5f));
        this.title.setAlignment(Align.left);

        this.rows.add(new LadderRow(getAssetManager(), "1", "1:34.72", "MyUserName", "(13)"));
        this.rows.add(new LadderRow(getAssetManager(), "2", "1:35.18", "MyUserName", "(21)"));
        this.rows.add(new LadderRow(getAssetManager(), "3", "1:41.94", "MyUserName", "(18)"));

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
}
