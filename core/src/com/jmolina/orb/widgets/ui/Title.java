package com.jmolina.orb.widgets.ui;

import com.jmolina.orb.managers.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.jmolina.orb.utils.Utils;
import com.jmolina.orb.var.Asset;
import com.jmolina.orb.var.Var;
import com.jmolina.orb.widgets.BaseGroup;

public class Title extends BaseGroup {

    private com.jmolina.orb.widgets.ui.Back button;
    private Label label;

    public Title(AssetManager am, String name) {
        super(am);

        Label.LabelStyle style = new Label.LabelStyle();
        style.fontColor = new Color(Var.COLOR_BLUE);
        style.font = getAsset(Asset.FONT_ROBOTO_BOLD_90, BitmapFont.class);

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

    @Override
    public void dispose() {
        super.dispose();
    }

    /**
     * Set a new listener for back button. There can be only one
     * @param listener EventListener
     */
    public void setListener(EventListener listener) {
        button.clearListeners();
        button.addListener(listener);
    }

    public void setLabel (String name) {
        label.setText(name);
    }

    @Override
    public void clickEffect() {
        button.clickEffect();
    }
}
