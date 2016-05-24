package com.jmolina.orb.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.jmolina.orb.var.Utils;
import com.jmolina.orb.var.Vars;
import com.jmolina.orb.groups.SectionTitleGroup;
import com.sun.javafx.property.adapter.PropertyDescriptor;

import static com.jmolina.orb.var.Vars.ScreenNames.*;

/**
 * TODO
 * Cuando se pierde el focus, hay que cancelar de alguna manera el movimientoo de scroll
 * Pues disposeando la pantalla, por ejemplo. A no ser que quiera dejarlas todas en memoria
 */
public class MenuScreen extends BaseScreen {

    private SectionTitleGroup sectionTitleGroup;
    private Table table;
    private ScrollPane scrollPane;
    private Texture scrollerTexture;

    public MenuScreen() {
        super();

        sectionTitleGroup = new SectionTitleGroup();
        sectionTitleGroup.setGridPosition(1, 3);

        // Default listener
        // TODO: 24/05/2016 Esto es confuso porque no todas las pantallas que heredan de MenuScreen vuelven a MainScreen.

        sectionTitleGroup.setBackListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                screenManager.setScreenByKey(SCREEN_MAIN);
            }
        });

        table = new Table();
        table.debug();
        table.top();
        table.setPosition(Vars.GRID_UNIT, 0f);
        // table.setWidth(Vars.VIEWPORT_WIDTH - 2 * Vars.GRID_UNIT);
        // table.setFillParent(true);
        // table.pack();

        ScrollPane.ScrollPaneStyle scrollPaneStyle = new ScrollPane.ScrollPaneStyle();
        scrollerTexture = new Texture(Gdx.files.internal("scroll_knob.png"));
        scrollPaneStyle.vScrollKnob = new TextureRegionDrawable(new TextureRegion(scrollerTexture));

        scrollPane = new ScrollPane(table);
        scrollPane.setStyle(scrollPaneStyle);
        scrollPane.setWidth(Vars.VIEWPORT_WIDTH);
        scrollPane.setHeight(Utils.yGrid(4.5f));
        scrollPane.setPosition(0f, 0f);

        getStage().addActor(sectionTitleGroup);
        getStage().addActor(scrollPane);
    }

    @Override
    public void dispose() {
        sectionTitleGroup.dispose();
        scrollerTexture.dispose();
        super.dispose();
    }

    /**
     * TODO Cuando estén claras las funcionalidades que se usan de Table, crear una API simple
     * @return Table
     */
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
        getTable().add(actor).width(width * Vars.GRID_UNIT).expandX().padBottom(bottomPadding * Vars.GRID_UNIT);
    }

    protected void setBackListener (EventListener listener) {
        sectionTitleGroup.clearListeners();
        sectionTitleGroup.addListener(listener);
    }

}
