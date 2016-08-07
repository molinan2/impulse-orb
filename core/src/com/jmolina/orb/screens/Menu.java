package com.jmolina.orb.screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.jmolina.orb.interfaces.SuperManager;
import com.jmolina.orb.managers.GameManager;
import com.jmolina.orb.managers.ScreenManager;
import com.jmolina.orb.utils.Utils;
import com.jmolina.orb.var.Asset;
import com.jmolina.orb.widgets.ui.Title;

public class Menu extends BaseScreen {

    private Title title;
    private Table table;
    private ScrollPane scrollPane;

    public Menu(SuperManager superManager) {
        super(superManager);

        title = new Title(getAssetManager(), "");
        title.setPosition(Utils.cell(1), Utils.cell(15.5f));

        table = new Table();
        table.top();
        table.setPosition(Utils.cell(1), 0f);

        ScrollPane.ScrollPaneStyle style = new ScrollPane.ScrollPaneStyle();
        style.vScrollKnob = new TextureRegionDrawable(new TextureRegion(
                getAsset(Asset.UI_SCROLL_KNOB, Texture.class)));

        scrollPane = new ScrollPane(table);
        scrollPane.setStyle(style);
        scrollPane.setWidth(VIEWPORT_WIDTH);
        scrollPane.setHeight(Utils.cell(14));
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

    public <T extends Actor> void add(T actor) {
        add(actor, 0.5f, 10f);
    }

    public <T extends Actor> void add(T actor, float bottomPadding) {
        add(actor, bottomPadding, 10f);
    }

    public <T extends Actor> void add(T actor, float bottomPadding, float width) {
        getTable().row();
        getTable()
                .add(actor)
                .width(width * Utils.cell(1))
                .expandX()
                .padBottom(bottomPadding * Utils.cell(1));

        registerActor(actor);
    }

    @Override
    protected void setPreviousScreen(final ScreenManager.Key key) {
        super.setPreviousScreen(key);

        title.setListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                title.clickEffect();
                getGameManager().play(GameManager.Fx.Back);
                switchToScreen(key, Hierarchy.HIGHER);
            }
        });
    }

    public void setTitle (String name) {
        title.setLabel(name);
    }

    @Override
    public void show() {
        scrollPane.setScrollPercentY(0);
        scrollPane.updateVisualScroll();
        super.show();
        getGameManager().play(GameManager.Track.Menu);
    }

}
