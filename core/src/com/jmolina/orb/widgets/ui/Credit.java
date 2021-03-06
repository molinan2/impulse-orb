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

package com.jmolina.orb.widgets.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.jmolina.orb.managers.AssetManager;
import com.jmolina.orb.var.Atlas;
import com.jmolina.orb.var.Font;
import com.jmolina.orb.var.Utils;
import com.jmolina.orb.var.Var;
import com.jmolina.orb.widgets.BaseGroup;

import java.util.ArrayList;

/**
 * Credito, incluyendo encabezado, texto descriptivo e hiperenlaces
 */
public class Credit extends BaseGroup {

    /** Encabezado, cuerpo descriptivo */
    private Label header, body;

    /** Lista de hiperenlaces */
    private ArrayList<Label> links;

    /**
     * Constructor
     *
     * @param am AssetManager
     * @param header Encabezado
     * @param body Texto descriptivo
     */
    public Credit(AssetManager am, String header, String body) {
        super(am);

        links = new ArrayList<Label>();

        Label.LabelStyle bodyStyle = new Label.LabelStyle();
        bodyStyle.fontColor = new Color(Var.COLOR_LILAC_DARK);
        bodyStyle.font = new BitmapFont(
                Gdx.files.internal(Font.FONT_ROBOTO_REGULAR_30),
                findRegion(Atlas.FONT_ROBOTO_REGULAR_30)
        );

        Label.LabelStyle headerStyle = new Label.LabelStyle();
        headerStyle.fontColor = new Color(Var.COLOR_GREEN_DARK);
        headerStyle.font = new BitmapFont(
                Gdx.files.internal(Font.FONT_ROBOTO_MEDIUM_45),
                findRegion(Atlas.FONT_ROBOTO_MEDIUM_45)
        );

        this.body = new Label(body, bodyStyle);
        this.body.setTouchable(Touchable.disabled);
        this.body.setPosition(0f, 0f);
        this.body.setWidth(Utils.cell(10));
        this.body.setWrap(true);
        this.body.setAlignment(Align.topLeft);
        this.body.setHeight(this.body.getPrefHeight());

        this.header = new Label(header, headerStyle);
        this.header.setTouchable(Touchable.disabled);
        this.header.setPosition(0f, this.body.getPrefHeight());
        this.header.setSize(Utils.cell(10), Utils.cell(1));
        this.header.setAlignment(Align.topLeft);

        addActor(this.header);
        addActor(this.body);

        setHeight(this.header.getPrefHeight() + this.body.getPrefHeight());
    }

    /**
     * Añade un hiperenlace
     *
     * @param text Texto visible
     * @param uri URI del enlace
     */
    public void addLink(String text, final String uri) {
        Label.LabelStyle style = new Label.LabelStyle();
        style.fontColor = new Color(Var.COLOR_LILAC_MEDIUM);
        style.font = new BitmapFont(
                Gdx.files.internal(Font.FONT_ROBOTO_REGULAR_30),
                findRegion(Atlas.FONT_ROBOTO_REGULAR_30)
        );

        int linkIndex = links.size() + 1;
        Label link = new Label("[" + linkIndex + "]: " + text, style);
        link.setTouchable(Touchable.enabled);
        link.setPosition(0f, 0f);
        link.setAlignment(Align.left);
        link.setHeight(link.getPrefHeight());
        link.setWidth(link.getPrefWidth());
        link.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.net.openURI(uri);
            }
        });

        links.add(link);
        addActor(link);

        header.setY(body.getPrefHeight() + getLinksPrefHeight() + Utils.cell(1));
        body.setY(getLinksPrefHeight() + Utils.cell(1));

        for (Label previouslink : links) {
            previouslink.setY(previouslink.getY() + link.getPrefHeight());
        }

        setHeight(header.getPrefHeight() + body.getPrefHeight() + getLinksPrefHeight() + Utils.cell(1));
    }

    /**
     * Devuelve la altura combinada de todos los hiperenlaces, para calcular la altura del widget.
     */
    private float getLinksPrefHeight() {
        float height = 0f;

        for (Label link : links) {
            height += link.getPrefHeight();
        }

        return height;
    }

}
