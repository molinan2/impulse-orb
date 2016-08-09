package com.jmolina.orb.widgets.ui;

import com.jmolina.orb.managers.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.jmolina.orb.utils.Utils;
import com.jmolina.orb.var.Asset;
import com.jmolina.orb.var.Var;
import com.jmolina.orb.widgets.BaseGroup;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

public class MainButton extends BaseGroup {

    public enum Type {
        SUCCESS, DANGER, DEFAULT, WARNING
    }

    private Label label;
    private Image background, frame;

    public MainButton(AssetManager am, String name, Type type) {
        super(am);

        Label.LabelStyle style = new Label.LabelStyle();
        style.fontColor = new Color(Var.COLOR_WHITE);
        style.font = getAsset(Asset.FONT_ROBOTO_BOLD_45, BitmapFont.class);

        label = new Label(name, style);
        label.setTouchable(Touchable.disabled);
        label.setPosition(0f, 0f);
        label.setSize(Utils.cell(8), Utils.cell(1.5f));
        label.setAlignment(Align.center);

        background = new Image(getBackgroundTexture(type));
        background.setPosition(0f, 0f);

        frame = new Image(getAsset(Asset.UI_BUTTON_FRAME, Texture.class));
        frame.setPosition(0, 0);
        frame.addAction(alpha(0));
        frame.act(0);

        addActor(background);
        addActor(frame);
        addActor(label);

        setFrame(frame);

        setHeight(Utils.cell(1.5f));
        setOrigin(background.getWidth() * 0.5f, background.getHeight() * 0.5f);
    }

    private Texture getBackgroundTexture(Type type) {
        switch (type) {
            case DEFAULT: return getAsset(Asset.UI_BUTTON_DEFAULT, Texture.class);
            case SUCCESS: return getAsset(Asset.UI_BUTTON_SUCCESS, Texture.class);
            case DANGER: return getAsset(Asset.UI_BUTTON_DANGER, Texture.class);
            case WARNING: return getAsset(Asset.UI_BUTTON_WARNING, Texture.class);
            default: return getAsset(Asset.UI_BUTTON_DEFAULT, Texture.class);
        }
    }



}
