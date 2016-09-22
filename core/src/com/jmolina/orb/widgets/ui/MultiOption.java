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
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.jmolina.orb.managers.AssetManager;
import com.jmolina.orb.var.Asset;
import com.jmolina.orb.var.Atlas;
import com.jmolina.orb.var.Font;
import com.jmolina.orb.var.Utils;
import com.jmolina.orb.var.Var;
import com.jmolina.orb.widgets.BaseGroup;

import java.util.ArrayList;

/**
 * Multicheckbox compuesta de 3 opciones (checkboxes)
 */
public class MultiOption extends BaseGroup {

    private final int DEFAULT_VALUE = 1;
    private final int OPTIONS = 3;

    /** Texto de la opcion */
    private Label label;

    /** Valor actual */
    private int value;

    /** Lista de checkboxes */
    private ArrayList<Checkbox> checkboxes;

    /** Imagen de un fader superpuesta a las checkboxes */
    private Image lever;

    /**
     * Constructor
     *
     * @param am AssetManager
     * @param name Nombre de la opcion
     */
    public MultiOption(AssetManager am, String name) {
        super(am);

        Label.LabelStyle style = new Label.LabelStyle();
        style.fontColor = new Color(Var.COLOR_LILAC_DARK);
        style.font = new BitmapFont(
                Gdx.files.internal(Font.FONT_ROBOTO_MEDIUM_45),
                findRegion(Atlas.FONT_ROBOTO_MEDIUM_45)
        );

        label = new Label(name, style);
        label.setTouchable(Touchable.disabled);
        label.setPosition(0f, 0f);
        label.setHeight(Utils.cell(1.5f));

        checkboxes = new ArrayList<Checkbox>();
        for (int i=0; i<OPTIONS; i++) {
            checkboxes.add(new Checkbox(getAssetManager(), false));
            checkboxes.get(i).setPositionGrid(5.5f + i * 1.5f, 0);
            checkboxes.get(i).setName(String.valueOf(i));
        }

        lever = new Image(getAsset(Asset.UI_MULTICHECK_1, Texture.class));
        lever.setTouchable(Touchable.disabled);
        lever.setSize(Utils.cell(4.5f), Utils.cell(1.5f));
        lever.setPosition(Utils.cell(5.5f), Utils.cell(0));

        addActor(label);
        for (Actor actor : checkboxes) addActor(actor);
        addActor(lever);

        setHeight(Utils.cell(1.5f));
        setTouchable(Touchable.childrenOnly);
        setValue(DEFAULT_VALUE);
    }

    /**
     * Fija el valor de la multiopcion
     *
     * @param value Valor
     */
    public void setValue (int value) {
        value = MathUtils.clamp(value, 0, OPTIONS);
        setCheckboxes(value);
        setLever(value);
        this.value = value;
    }

    /**
     * Activa la checkbox correspondiente al valor indicado
     *
     * @param value Valor
     */
    private void setCheckboxes(int value) {
        for (Checkbox checkbox : checkboxes)
            checkbox.uncheck();

        checkboxes.get(value).check();
    }

    /**
     * Carga la textura del fader correspondiente al valor indicado
     *
     * @param value Valor
     */
    private void setLever(int value) {
        switch (value) {
            case 0: lever.setDrawable(new TextureRegionDrawable(new TextureRegion(getAsset(Asset.UI_MULTICHECK_0, Texture.class)))); break;
            case 1: lever.setDrawable(new TextureRegionDrawable(new TextureRegion(getAsset(Asset.UI_MULTICHECK_1, Texture.class)))); break;
            case 2: lever.setDrawable(new TextureRegionDrawable(new TextureRegion(getAsset(Asset.UI_MULTICHECK_2, Texture.class)))); break;
            default: lever.setDrawable(new TextureRegionDrawable(new TextureRegion(getAsset(Asset.UI_MULTICHECK_1, Texture.class))));
        }
    }

}

