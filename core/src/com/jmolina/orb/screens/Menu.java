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
import com.jmolina.orb.Orb;
import com.jmolina.orb.utils.Grid;
import com.jmolina.orb.var.Var;
import com.jmolina.orb.widgets.Title;

public class Menu extends BaseScreen {

    private Title title;
    private Table table;
    private ScrollPane scrollPane;
    private Texture scrollerTexture;
    private Orb.Name returningScreen;

    public Menu() {
        super();

        title = new Title("");
        title.setGridPosition(1, 3);

        table = new Table();
        table.top();
        table.setPosition(Var.GRID_UNIT, 0f);

        ScrollPane.ScrollPaneStyle scrollPaneStyle = new ScrollPane.ScrollPaneStyle();
        scrollerTexture = new Texture(Gdx.files.internal("scroll_knob.png"));
        scrollPaneStyle.vScrollKnob = new TextureRegionDrawable(new TextureRegion(scrollerTexture));

        scrollPane = new ScrollPane(table);
        scrollPane.setStyle(scrollPaneStyle);
        scrollPane.setWidth(Var.VIEWPORT_WIDTH);
        scrollPane.setHeight(Grid.cellY(4.5f));
        scrollPane.setPosition(0f, 0f);

        addMainActor(title);
        addMainActor(scrollPane);
    }

    @Override
    public void dispose() {
        title.dispose();
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
        register(actor);
    }

    protected void setReturningScreen(Orb.Name name) {
        this.returningScreen = name;
        setReturningScreenListener(name);
    }

    public Orb.Name getReturningScreen() {
        return this.returningScreen;
    }

    private void setReturningScreenListener (final Orb.Name name) {
        title.setListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                switchToScreen(name, Hierarchy.HIGHER);
            }
        });
    }

    public void setTitle (String name) {
        title.setLabel(name);
    }

    @Override
    public void back() {
        switchToScreen(this.returningScreen, Hierarchy.HIGHER);
    }
}
