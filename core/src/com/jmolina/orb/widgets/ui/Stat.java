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
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.jmolina.orb.managers.AssetManager;
import com.jmolina.orb.var.Atlas;
import com.jmolina.orb.var.Font;
import com.jmolina.orb.var.Utils;
import com.jmolina.orb.var.Var;
import com.jmolina.orb.widgets.BaseGroup;

import java.text.DecimalFormat;

// No se encuentra en GWT (HTML), por lo que no se puede compilar en GWT (HTML)

/**
 * Estadistica generica
 */
public class Stat extends BaseGroup {

    /** Nombre de la estadistica, dato estadistico */
    private Label name, data;
    private Label.LabelStyle nameStyle, dataStyle;

    /**
     * Constructor
     *
     * @param am AssetManager
     * @param name Nombre
     */
    public Stat (AssetManager am, String name) {
        this(am, name, 0);
    }

    /**
     * Constructor
     *
     * @param am AssetManager
     * @param name Nombre
     * @param value Valor del dato
     */
    public Stat (AssetManager am, String name, float value) {
        this(am, name, value, "");
    }

    /**
     * Constructor
     *
     * @param am AssetManager
     * @param name Nombre
     * @param value Valor del dato
     * @param unit Unidad del dato
     */
    public Stat(AssetManager am, String name, float value, String unit) {
        super(am);

        nameStyle = new Label.LabelStyle();
        dataStyle = new Label.LabelStyle();

        nameStyle.fontColor = new Color(Var.COLOR_LILAC_DARK);
        dataStyle.fontColor = new Color(Var.COLOR_BLACK);
        nameStyle.font = new BitmapFont(
                Gdx.files.internal(Font.FONT_ROBOTO_REGULAR_45),
                findRegion(Atlas.FONT_ROBOTO_REGULAR_45)
        );
        dataStyle.font = new BitmapFont(
                Gdx.files.internal(Font.FONT_ROBOTO_REGULAR_45),
                findRegion(Atlas.FONT_ROBOTO_REGULAR_45)
        );

        this.name = new Label(name, nameStyle);
        this.name.setTouchable(Touchable.disabled);
        this.name.setPosition(0f, 0f);
        this.name.setHeight(Utils.cell(1));
        this.name.setWidth(Utils.cell(6));
        this.name.setAlignment(Align.left);

        this.data = new Label(formatStat(value, unit), dataStyle);
        this.data.setTouchable(Touchable.disabled);
        this.data.setPosition(Utils.cell(6), 0f);
        this.data.setHeight(Utils.cell(1));
        this.data.setWidth(Utils.cell(4));
        this.data.setAlignment(Align.right);

        addActor(this.name);
        addActor(this.data);
        setTransform(false);
        setHeight(Utils.cell(1));
    }

    /**
     * Devuelve el valor del dato formateado
     *
     * @param value Valor del dato
     * @param unit Unidad
     */
    private String formatStat(float value, String unit) {
        return formatStat(value, unit, true);
    }

    /**
     * Devuelve el valor del dato formateado
     *
     * @param value Valor del dato
     * @param unit Unidad
     * @param withDecimals Si contempla decimales o no
     */
    private String formatStat(float value, String unit, boolean withDecimals) {
        DecimalFormat df;

        if (withDecimals)
            df = new DecimalFormat("###.##");
        else
            df = new DecimalFormat("###");

        String stat;

        if (unit.length() > 0)
            stat = df.format(value) + " " + unit;
        else
            stat = df.format(value);

        return stat;
    }

    /**
     * Fija el valor del dato estadistico
     *
     * @param value Valor
     */
    public void setValue(float value) {
        setValue(value, "");
    }

    /**
     * Fija el valor del dato estadistico y su unidad
     *
     * @param value Valor del dato
     * @param unit Unidad
     */
    public void setValue(float value, String unit) {
        setValue(value, unit, true);
    }

    /**
     * Fija el valor del dato estadistico, su unidad y si presenta decimales
     *
     * @param value Valor del dato
     * @param unit Unidad
     * @param decimals Si contempla decimales o no
     */
    public void setValue(float value, String unit, boolean decimals) {
        this.data.setText(formatStat(value, unit, decimals));
    }

    /**
     * Fija un valor nulo para el dato estadistico
     *
     * @param unit Unidad
     */
    public void setNullValue(String unit) {
        this.data.setText("-- " + unit);
    }

    /**
     * Fija el nombre de la estadistica
     *
     * @param name Nombre
     */
    public void setName(String name) {
        this.name.setText(name);
    }

    /**
     * Devuelve el nombre de la estadistica
     */
    public String getName() {
        return this.name.getText().toString();
    }

    /**
     * Modifica el color del texto
     *
     * @param color Color
     */
    public void setLabelColor (int color) {
        nameStyle.fontColor = new Color(color);
        dataStyle.fontColor = new Color(color);
        name.setStyle(nameStyle);
        data.setStyle(dataStyle);
    }

}