package com.jmolina.orb.widgets;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Disposable;
import com.jmolina.orb.groups.BaseGroup;
import com.jmolina.orb.var.Var;

/**
 * TODO
 * Idealmente, los tiempos se cargan desde el servidor
 */
public class Ladder extends BaseGroup implements Disposable {

    private class LadderRow extends BaseGroup {

        private Label rank;
        private Label time;
        private Label user;
        private Label globalRank;

        public LadderRow(String rank, String time, String user, String globalRank) {

        }

    }

    private Image title;
    // Array<LadderRowWidget> rows;
    // private Image background;
    // private Texture backgroundTexture;

    public Ladder(Texture titleTexture) {
        title = new Image(titleTexture);
        addActor(title);
        setHeight(3.75f * Var.GRID_UNIT);
    }

    @Override
    public void dispose() {
    }
}
