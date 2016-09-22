/*
 * IMPULSE ORB
 * Copyright (C) 2016 Juan M. Molina
 *
 * This file is part of the IMPULSE ORB source code.
 *
 * IMPULSE ORB is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * IMPULSE ORB is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

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
import com.jmolina.orb.var.Asset;
import com.jmolina.orb.var.Utils;
import com.jmolina.orb.widgets.ui.Title;

/**
 * Clase base para todas las pantallas de menu que utilizan scroll
 */
public class Menu extends BaseScreen {

    /** Titulo de pantalla */
    private Title title;

    /** Tabla de widgets dentro del scroll */
    private Table table;

    /** Panel de scroll */
    private ScrollPane scrollPane;

    /**
     * Constructor
     *
     * @param superManager SuperManager
     */
    public Menu(SuperManager superManager) {
        super(superManager);

        title = new Title(getAssetManager(), "");
        title.setPosition(Utils.cell(1), Utils.cell(15.5f));

        table = new Table();
        table.top();
        table.setPosition(Utils.cell(1), 0f);

        ScrollPane.ScrollPaneStyle style = new ScrollPane.ScrollPaneStyle();
        style.vScrollKnob = new TextureRegionDrawable(new TextureRegion(getAsset(Asset.UI_SCROLL_KNOB, Texture.class)));

        scrollPane = new ScrollPane(table);
        scrollPane.setStyle(style);
        scrollPane.setWidth(VIEWPORT_WIDTH);
        scrollPane.setHeight(Utils.cell(14));
        scrollPane.setPosition(0f, 0f);

        addMainActor(title);
        addMainActor(scrollPane);
    }

    /**
     * Devuelve la tabla de widgets
     */
    private Table getTable() {
        return table;
    }

    /**
     * Añade un actor a la tabla de widgets
     *
     * @param actor Actor
     */
    public <T extends Actor> void add(T actor) {
        add(actor, 0.5f, 10f);
    }

    /**
     * Añade un actor a la tabla de widgets
     *
     * @param actor Actor
     * @param bottomPadding Padding inferior en unidades del grid
     */
    public <T extends Actor> void add(T actor, float bottomPadding) {
        add(actor, bottomPadding, 10f);
    }

    /**
     * Añade un actor a la tabla de widgets
     *
     * @param actor Actor
     * @param bottomPadding Padding inferior en unidades del grid
     * @param width Ancho del actor
     */
    public <T extends Actor> void add(T actor, float bottomPadding, float width) {
        getTable().row();
        getTable()
                .add(actor)
                .width(width * Utils.cell(1))
                .expandX()
                .padBottom(bottomPadding * Utils.cell(1));
    }

    /**
     * Fija el titulo de la pantalla
     *
     * @param name Titulo
     */
    public void setTitle (String name) {
        title.setLabel(name);
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

    @Override
    public void show() {
        scrollPane.setScrollPercentY(0);
        scrollPane.updateVisualScroll();
        super.show();
        getGameManager().play(GameManager.Track.Menu);
    }

}
