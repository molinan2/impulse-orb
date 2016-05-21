package com.jmolina.orb.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.jmolina.orb.var.Vars;
import com.jmolina.orb.widgets.BackTitleGroup;

public class MenuScreen extends BaseScreen {

    private BackTitleGroup backTitleGroup;
    private Table table;
    private ScrollPane scrollPane;

    public MenuScreen() {
        super();

        backTitleGroup = new BackTitleGroup();
        backTitleGroup.setGridPosition(1, 3);
        getStage().addActor(backTitleGroup);

        table = new Table();
        table.setPosition(0f, 0f);
        table.setWidth(Vars.VIEWPORT_WIDTH);

        scrollPane = new ScrollPane(table);
        scrollPane.setWidth(Vars.VIEWPORT_WIDTH);
        scrollPane.setHeight(0.7f * Vars.VIEWPORT_HEIGHT);
        scrollPane.setPosition(0f, 0f);

        getStage().addActor(scrollPane);
    }

    @Override
    public void dispose() {
        backTitleGroup.dispose();
        super.dispose();
    }

    /**
     * TODO Cuando estén claras las funcionalidades que se usan de Table, crear una API simple
     * @return Table
     */
    public Table getTable() {
        return table;
    }
}
