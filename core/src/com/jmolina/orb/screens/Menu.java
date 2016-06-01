package com.jmolina.orb.screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.jmolina.orb.Orb;
import com.jmolina.orb.managers.OrbAssetManager;
import com.jmolina.orb.utils.Grid;
import com.jmolina.orb.var.Asset;
import com.jmolina.orb.var.Var;
import com.jmolina.orb.widgets.Title;

public class Menu extends BaseScreen {

    private Title title;
    private Table table;
    private ScrollPane scrollPane;
    private Orb.Name returningScreen;

    public Menu(OrbAssetManager am) {
        super(am);

        title = new Title(getAssetManager(), "");
        title.setGridPosition(1, 3);

        table = new Table();
        table.top();
        table.setPosition(Var.GRID_UNIT, 0f);

        ScrollPane.ScrollPaneStyle style = new ScrollPane.ScrollPaneStyle();
        style.vScrollKnob = new TextureRegionDrawable(new TextureRegion(
                getAsset(Asset.UI_SCROLL_KNOB, Texture.class)));

        scrollPane = new ScrollPane(table);
        scrollPane.setStyle(style);
        scrollPane.setWidth(Var.VIEWPORT_WIDTH);
        scrollPane.setHeight(Grid.cellY(4.5f));
        scrollPane.setPosition(0f, 0f);

        addMainActor(title);
        addMainActor(scrollPane);
    }

    @Override
    public void dispose() {
        title.dispose();
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
