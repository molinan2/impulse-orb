package com.jmolina.orb.widgets.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.jmolina.orb.managers.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.jmolina.orb.var.Utils;
import com.jmolina.orb.var.Atlas;
import com.jmolina.orb.var.Font;
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
        style.font = new BitmapFont(
                Gdx.files.internal(Font.FONT_ROBOTO_BOLD_45),
                findRegion(Atlas.FONT_ROBOTO_BOLD_45)
        );

        label = new Label(name, style);
        label.setTouchable(Touchable.disabled);
        label.setPosition(0f, 0f);
        label.setSize(Utils.cell(8), Utils.cell(1.5f));
        label.setAlignment(Align.center);

        background = new Image(getBackgroundRegion(type));
        background.setPosition(0f, 0f);

        frame = new Image(findRegion(Atlas.UI_BUTTON_FRAME));
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

    private TextureRegion getBackgroundRegion(Type type) {
        switch (type) {
            case DEFAULT: return findRegion(Atlas.UI_BUTTON_DEFAULT);
            case SUCCESS: return findRegion(Atlas.UI_BUTTON_SUCCESS);
            case DANGER: return findRegion(Atlas.UI_BUTTON_DANGER);
            case WARNING: return findRegion(Atlas.UI_BUTTON_WARNING);
            default: return findRegion(Atlas.UI_BUTTON_DEFAULT);
        }
    }

}
