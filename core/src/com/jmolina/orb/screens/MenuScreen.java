package com.jmolina.orb.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.jmolina.orb.var.Vars;

public class MenuScreen extends BaseScreen {

    private Texture backTitleTexture;
    private Image backTitle;
    private ScrollPane scrollPane;
    private Table table;

    // private BackTitle backTitle;

    public MenuScreen() {
        super();
        backTitleTexture = new Texture(Gdx.files.internal("backtitle.png"));
        backTitle = new Image(new TextureRegionDrawable(new TextureRegion(backTitleTexture)));
        setPositionRelative(0.5f, 0.95f, backTitle);
        getStage().addActor(backTitle);

        table = new Table();
        table.debug();
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
        backTitleTexture.dispose();
        super.dispose();
    }

    public Table getTable() {
        return table;
    }
}
