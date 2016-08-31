package com.jmolina.orb.widgets.ui;

import com.badlogic.gdx.Gdx;
import com.jmolina.orb.managers.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.jmolina.orb.var.Utils;
import com.jmolina.orb.var.Atlas;
import com.jmolina.orb.var.Font;
import com.jmolina.orb.var.Var;
import com.jmolina.orb.widgets.BaseGroup;

/**
 * Titulo de una pantalla de menu
 */
public class Title extends BaseGroup {

    /** Boton atras */
    private Back button;

    /** Texto del titulo */
    private Label label;

    /**
     * Constructor
     *
     * @param am AssetManager
     * @param name Texto del titulo
     */
    public Title(AssetManager am, String name) {
        super(am);

        Label.LabelStyle style = new Label.LabelStyle();
        style.fontColor = new Color(Var.COLOR_GREEN_DARK);
        style.font = new BitmapFont(
                Gdx.files.internal(Font.FONT_ROBOTO_BLACK_90),
                findRegion(Atlas.FONT_ROBOTO_BLACK_90)
        );

        label = new Label(name, style);
        label.setTouchable(Touchable.disabled);
        label.setPosition(Utils.cell(3), 0f);
        label.setHeight(Utils.cell(2));
        label.setWidth(Utils.cell(7));
        label.setAlignment(Align.left);

        button = new com.jmolina.orb.widgets.ui.Back(getAssetManager());
        button.setPosition(0f, 0f);

        addActor(button);
        addActor(label);
    }

    /**
     * Fija un nuevo listener para el boton "Atras". There can be only one!
     *
     * @param listener EventListener
     */
    public void setListener(EventListener listener) {
        button.clearListeners();
        button.addListener(listener);
    }

    /**
     * Modifica el texto del titulo
     *
     * @param name Texto del titulo
     */
    public void setLabel (String name) {
        label.setText(name);
    }

    @Override
    public void clickEffect() {
        button.clickEffect();
    }
}
