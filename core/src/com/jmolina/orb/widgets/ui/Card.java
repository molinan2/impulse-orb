package com.jmolina.orb.widgets.ui;

import com.badlogic.gdx.Gdx;
import com.jmolina.orb.managers.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.jmolina.orb.interfaces.Visitor;
import com.jmolina.orb.managers.GameManager;
import com.jmolina.orb.managers.ScreenManager;
import com.jmolina.orb.var.Utils;
import com.jmolina.orb.var.Asset;
import com.jmolina.orb.var.Atlas;
import com.jmolina.orb.var.Font;
import com.jmolina.orb.var.Var;
import com.jmolina.orb.widgets.BaseGroup;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

public class Card extends BaseGroup {

    private Image coverFrame, cover, coverBlur, background, padlock, fullFrame;
    private Label titleLabel, timeLabel;
    private GameManager gameManager;
    private boolean available;

    public Card(AssetManager am, GameManager gm, String title, String time, ScreenManager.Key level) {
        super(am);

        gameManager = gm;

        fullFrame = new Image(getAsset(Asset.UI_CARD_FRAME, Texture.class));
        coverFrame = new Image(getAsset(Asset.UI_CARD_COVER_FRAME, Texture.class));
        cover = new Image(getCoverTexture(level));
        coverBlur = new Image(getCoverBlurTexture(level));
        background = new Image(getAsset(Asset.UI_CARD_BACKGROUND, Texture.class));
        padlock = new Image(getAsset(Asset.UI_CARD_PADLOCK, Texture.class));

        fullFrame.setPosition(0, 0);
        fullFrame.addAction(alpha(0));
        fullFrame.act(0);
        coverFrame.setPosition(0, 0);
        background.setPosition(0f, 0f);
        cover.setPosition(0f, 0f);
        coverBlur.setPosition(0f, 0f);
        padlock.setPosition(Utils.cell(1.75f), Utils.cell(1));

        Label.LabelStyle titleStyle = new Label.LabelStyle();
        titleStyle.fontColor = new Color(Var.COLOR_GREEN_DARK);
        titleStyle.font = new BitmapFont(
                Gdx.files.internal(Font.FONT_ROBOTO_BOLD_45),
                findRegion(Atlas.FONT_ROBOTO_BOLD_45)
        );

        titleLabel = new Label(title, titleStyle);
        titleLabel.setTouchable(Touchable.disabled);
        titleLabel.setPosition(Utils.cell(5), Utils.cell(2.75f));
        titleLabel.setHeight(Utils.cell(1));
        titleLabel.setWidth(Utils.cell(4.5f));
        titleLabel.setAlignment(Align.right);

        Label.LabelStyle timeStyle = new Label.LabelStyle();
        timeStyle.fontColor = new Color(Var.COLOR_LILAC_DARK);
        timeStyle.font = new BitmapFont(
                Gdx.files.internal(Font.FONT_ROBOTO_REGULAR_30),
                findRegion(Atlas.FONT_ROBOTO_REGULAR_30)
        );

        timeLabel = new Label(time, timeStyle);
        timeLabel.setTouchable(Touchable.disabled);
        timeLabel.setPosition(Utils.cell(5), Utils.cell(0.25f));
        timeLabel.setHeight(Utils.cell(0.75f));
        timeLabel.setWidth(Utils.cell(4.5f));
        timeLabel.setAlignment(Align.right);

        addActor(background);
        addActor(coverBlur);
        addActor(cover);
        addActor(coverFrame);
        addActor(fullFrame);
        addActor(padlock);
        addActor(titleLabel);
        addActor(timeLabel);

        setFrame(fullFrame);

        setHeight(Utils.cell(4));
        lock();
    }

    public void lock() {
        available = false;
        cover.clearActions();
        padlock.clearActions();
        timeLabel.clearActions();
        cover.setVisible(false);
        coverBlur.setVisible(true);
        coverBlur.addAction(alpha(0.5f));
        padlock.addAction(alpha(1f));
        timeLabel.addAction(alpha(0f));
    }

    public void unlock() {
        available = true;
        cover.clearActions();
        cover.setVisible(true);
        coverBlur.setVisible(false);
        padlock.clearActions();
        timeLabel.clearActions();
        padlock.addAction(alpha(0f));
    }

    public boolean isAvailable() {
        return available;
    }

    public void setOnClickOperation(final Visitor visitor) {
        this.clearListeners();
        this.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (isAvailable()) {
                    clickEffect();
                    visitor.run();
                }
                else {
                    gameManager.play(GameManager.Fx.Error);
                    padlock.clearActions();
                    padlock.addAction(sequence(
                            alpha(0f),
                            fadeIn(0.5f),
                            fadeOut(0.5f),
                            fadeIn(0.5f)
                    ));

                }
            }
        });
    }

    private Texture getCoverTexture(ScreenManager.Key level) {
        switch (level) {
            case LEVEL_1: return getAsset(Asset.UI_CARD_COVER_1, Texture.class);
            case LEVEL_2: return getAsset(Asset.UI_CARD_COVER_2, Texture.class);
            case LEVEL_3: return getAsset(Asset.UI_CARD_COVER_3, Texture.class);
            case LEVEL_4: return getAsset(Asset.UI_CARD_COVER_4, Texture.class);
            case LEVEL_5: return getAsset(Asset.UI_CARD_COVER_5, Texture.class);
            default: return getAsset(Asset.UI_CARD_COVER_1, Texture.class);
        }
    }

    private Texture getCoverBlurTexture(ScreenManager.Key level) {
        switch (level) {
            case LEVEL_1: return getAsset(Asset.UI_CARD_COVER_1_BLUR, Texture.class);
            case LEVEL_2: return getAsset(Asset.UI_CARD_COVER_2_BLUR, Texture.class);
            case LEVEL_3: return getAsset(Asset.UI_CARD_COVER_3_BLUR, Texture.class);
            case LEVEL_4: return getAsset(Asset.UI_CARD_COVER_4_BLUR, Texture.class);
            case LEVEL_5: return getAsset(Asset.UI_CARD_COVER_5_BLUR, Texture.class);
            default: return getAsset(Asset.UI_CARD_COVER_1_BLUR, Texture.class);
        }
    }

}