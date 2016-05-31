package com.jmolina.orb.widgets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Disposable;
import com.jmolina.orb.groups.BaseGroup;
import com.jmolina.orb.utils.Grid;
import com.jmolina.orb.var.Var;

import java.util.ArrayList;

/**
 * TODO
 * Idealmente, los tiempos se cargan desde el servidor.
 * Este widget deberia conectar con el recurso pasado como parametro.
 * Mientras los resultados llegan y se procesan, renderizar un "loading".
 */

/**
 * Ranking Ladder. Por ahora, exactamente con 3 posiciones
 */
public class Ladder extends BaseGroup implements Disposable {

    private Label title;
    private BitmapFont font;
    private Image bg;
    private Texture bgTexture;
    private ArrayList<LadderRow> rows;

    public Ladder(String title) {
        this.rows = new ArrayList<LadderRow>();

        this.bgTexture = new Texture(Gdx.files.internal("ladder_background.png"));
        this.bg = new Image(bgTexture);
        this.bg.setPosition(0f, 0f);

        this.font = new BitmapFont(Gdx.files.internal("font/roboto_bold_30.fnt"));

        Label.LabelStyle style = new Label.LabelStyle();
        style.fontColor = new Color(Var.COLOR_BLUE);
        style.font = font;

        this.title = new Label(title.toUpperCase(), style);
        this.title.setPosition(Grid.unit(0.5f), Grid.unit(3.125f));
        this.title.setSize(Grid.unit(2), Grid.unit(0.5f));
        this.title.setAlignment(Align.left);

        this.rows.add(new LadderRow("1", "1:34.72", "MyUserName", "(13)"));
        this.rows.add(new LadderRow("2", "1:35.18", "MyUserName", "(21)"));
        this.rows.add(new LadderRow("3", "1:41.94", "MyUserName", "(18)"));

        addActor(this.bg);
        addActor(this.title);
        addLadderRows(rows);
        setHeight(Grid.unit(4));
    }

    @Override
    public void dispose() {
        font.dispose();
        bgTexture.dispose();
    }

    private void addLadderRows(ArrayList<LadderRow> rows) {
        for (int i=0; i<rows.size(); i++) {
            rows.get(i).setPosition(Grid.unit(0.5f), Grid.unit(2f - i * 0.75f));
            addActor(rows.get(i));
        }
    }
}
