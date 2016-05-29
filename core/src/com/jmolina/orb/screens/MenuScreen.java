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
import com.jmolina.orb.widgets.ScreenHeader;

public class MenuScreen extends BaseScreen {

    private ScreenHeader screenHeader;
    private Table table;
    private ScrollPane scrollPane;
    private Texture scrollerTexture;

    public MenuScreen() {
        super();

        screenHeader = new ScreenHeader("");
        screenHeader.setGridPosition(1, 3);

        table = new Table();
        table.top();
        table.setPosition(Var.GRID_UNIT, 0f);

        ScrollPane.ScrollPaneStyle scrollPaneStyle = new ScrollPane.ScrollPaneStyle();
        scrollerTexture = new Texture(Gdx.files.internal("scroll_knob.png"));
        scrollPaneStyle.vScrollKnob = new TextureRegionDrawable(new TextureRegion(scrollerTexture));

        scrollPane = new ScrollPane(table);
        scrollPane.setStyle(scrollPaneStyle);
        scrollPane.setWidth(Var.VIEWPORT_WIDTH);
        scrollPane.setHeight(Grid.y(4.5f));
        scrollPane.setPosition(0f, 0f);

        addMainActor(screenHeader);
        addMainActor(scrollPane);
    }

    @Override
    public void dispose() {
        screenHeader.dispose();
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

    protected void setReturningScreen(final Orb.Name name) {
        screenHeader.clearListeners();
        screenHeader.setListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                switchToScreen(name, Hierarchy.HIGHER);
            }
        });
    }

    public void setTitle (String name) {
        screenHeader.setLabel(name);
    }

}
