package com.jmolina.orb.widgets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Disposable;
import com.jmolina.orb.groups.BaseGroup;
import com.jmolina.orb.managers.ReflectionAssetManager;
import com.jmolina.orb.var.Var;

public class Button extends BaseGroup implements Disposable {

    /**
     * Bootstrap: Default, Primary, Success, Warning, Danger, Info
     */
    public enum Type {
        Play, Exit, Default
    }

    private ReflectionAssetManager assetManager;
    private Label label;
    private BitmapFont font;
    private Image bg;
    private Texture bgTexture;

    public Button(ReflectionAssetManager assetManager, String name, Type type) {
        this.assetManager = assetManager;

        font = new BitmapFont(Gdx.files.internal("font/roboto_bold_45.fnt"));
        font.setColor(Color.WHITE);

        Label.LabelStyle ls = new Label.LabelStyle();
        ls.fontColor = new Color(Var.COLOR_BLUE);
        ls.font = font;

        label = new Label(name, ls);
        label.setTouchable(Touchable.disabled);
        label.setPosition(0f, 0f);
        label.setHeight(1.5f * Var.GRID_UNIT);
        label.setWidth(8f * Var.GRID_UNIT);
        label.setAlignment(Align.center);

        this.bgTexture = getBgTexture(type);
        this.bg = new Image(bgTexture);
        this.bg.setPosition(0f, 0f);

        addActor(bg);
        addActor(label);

        setHeight(1.5f * Var.GRID_UNIT);
        setOrigin(bgTexture.getWidth() * 0.5f, bgTexture.getHeight() * 0.5f);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
    }

    @Override
    public void dispose() {
        bgTexture.dispose();
        font.dispose();
    }

    private Texture getBgTexture(Type type) {
        switch (type) {
            case Default:
                return new Texture(Gdx.files.internal("button_default.png"));
            case Play:
                return new Texture(Gdx.files.internal("button_play.png"));
            case Exit:
                return new Texture(Gdx.files.internal("button_exit.png"));
            default:
                return new Texture(Gdx.files.internal("button_default.png"));
        }
    }

}
