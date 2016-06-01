package com.jmolina.orb.widgets;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.jmolina.orb.utils.Grid;
import com.jmolina.orb.var.Asset;
import com.jmolina.orb.var.Var;

public class Button extends BaseWidget {

    public enum Type {
        Play, Exit, Default
    }

    private Label label;
    private Image bg;

    public Button(AssetManager am, String name, Type type) {
        super(am);

        Label.LabelStyle style = new Label.LabelStyle();
        style.fontColor = new Color(Var.COLOR_BLUE);
        style.font = getAsset(Asset.FONT_ROBOTO_BOLD_45, BitmapFont.class);

        label = new Label(name, style);
        label.setTouchable(Touchable.disabled);
        label.setPosition(0f, 0f);
        label.setSize(Grid.unit(8), Grid.unit(1.5f));
        label.setAlignment(Align.center);

        this.bg = new Image(getBgTexture(type));
        this.bg.setPosition(0f, 0f);

        addActor(bg);
        addActor(label);

        setHeight(1.5f * Var.GRID_UNIT);
        setOrigin(bg.getWidth() * 0.5f, bg.getHeight() * 0.5f);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    private Texture getBgTexture(Type type) {
        switch (type) {
            case Default:
                return getAsset(Asset.UI_BUTTON_DEFAULT, Texture.class);
            case Play:
                return getAsset(Asset.UI_BUTTON_PLAY, Texture.class);
            case Exit:
                return getAsset(Asset.UI_BUTTON_EXIT, Texture.class);
            default:
                return getAsset(Asset.UI_BUTTON_DEFAULT, Texture.class);
        }
    }

}
