package com.jmolina.orb.widgets;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.jmolina.orb.utils.Grid;
import com.jmolina.orb.assets.Asset;

public class Title extends BaseGroup {

    private BackButton button;
    private Label label;

    public Title(AssetManager am, String name) {
        super(am);

        Label.LabelStyle style = new Label.LabelStyle();
        style.fontColor = new Color(BaseGroup.COLOR_BLUE);
        style.font = getAsset(Asset.FONT_ROBOTO_BOLD_90, BitmapFont.class);

        label = new Label(name, style);
        label.setTouchable(Touchable.disabled);
        label.setPosition(Grid.unit(3), 0f);
        label.setHeight(Grid.unit(2));
        label.setWidth(Grid.unit(7));
        label.setAlignment(Align.left);

        button = new BackButton(getAssetManager());
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
