package com.jmolina.orb.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.jmolina.orb.OrbGame;
import com.jmolina.orb.var.Util;
import com.jmolina.orb.var.Var;
import com.jmolina.orb.groups.ReturningTitleGroup;

public class MenuScreen extends BaseScreen {

    private ReturningTitleGroup returningTitleGroup;
    private Table table;
    private ScrollPane scrollPane;
    private Texture scrollerTexture;

    public MenuScreen() {
        super();

        returningTitleGroup = new ReturningTitleGroup();
        returningTitleGroup.setGridPosition(1, 3);

        table = new Table();
        table.top();
        table.setPosition(Var.GRID_UNIT, 0f);

        ScrollPane.ScrollPaneStyle scrollPaneStyle = new ScrollPane.ScrollPaneStyle();
        scrollerTexture = new Texture(Gdx.files.internal("scroll_knob.png"));
        scrollPaneStyle.vScrollKnob = new TextureRegionDrawable(new TextureRegion(scrollerTexture));

        scrollPane = new ScrollPane(table);
        scrollPane.setStyle(scrollPaneStyle);
        scrollPane.setWidth(Var.VIEWPORT_WIDTH);
        scrollPane.setHeight(Util.yGrid(4.5f));
        scrollPane.setPosition(0f, 0f);

        addMainActor(returningTitleGroup);
        addMainActor(scrollPane);
    }

    @Override
    public void dispose() {
        returningTitleGroup.dispose();
        scrollerTexture.dispose();
        super.dispose();
    }

    private Table getTable() {
        return table;
    }

    public <T extends Actor> void addRow(T actor) {
        addRow(actor, 0.5f, 10f);
    }

    public <T extends Actor> void addRow(T actor, float bottomPadding) {
        addRow(actor, bottomPadding, 10f);
    }

    public <T extends Actor> void addRow(T actor, float bottomPadding, float width) {
        getTable().row();
        getTable().add(actor).width(width * Var.GRID_UNIT).expandX().padBottom(bottomPadding * Var.GRID_UNIT);
    }

    protected void setReturningScreen(final OrbGame.Name name) {
        returningTitleGroup.clearListeners();
        returningTitleGroup.setListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                switchToScreen(name, Hierarchy.HIGHER);
            }
        });
    }

}
