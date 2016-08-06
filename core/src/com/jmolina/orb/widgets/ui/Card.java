package com.jmolina.orb.widgets.ui;

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
import com.jmolina.orb.actions.UIAction;
import com.jmolina.orb.interfaces.Visitor;
import com.jmolina.orb.utils.Utils;
import com.jmolina.orb.var.Asset;
import com.jmolina.orb.var.Var;
import com.jmolina.orb.widgets.BaseGroup;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

public class Card extends BaseGroup {

    private Image cover;
    private Image coverWhite;
    private Image background;
    private Image lock;

    private Label title;
    private Label personal;
    private Label world;

    private boolean available;

    public Card(AssetManager am, String title, String personal, String world, Texture coverTexture, boolean available) {
        super(am);

        this.available = available;

        cover = new Image(coverTexture);
        coverWhite = new Image(getAsset(Asset.UI_CARD_COVER_WHITE, Texture.class));
        background = new Image(getAsset(Asset.UI_CARD_BACKGROUND, Texture.class));
        lock = new Image(getAsset(Asset.UI_CARD_PADLOCK, Texture.class));

        background.setPosition(0f, 0f);
        cover.setPosition(0f, 0f);
        coverWhite.setPosition(0f, 0f);
        lock.setPosition(Utils.cell(1.75f), Utils.cell(1));

        Label.LabelStyle titleStyle = new Label.LabelStyle();
        titleStyle.fontColor = new Color(Var.COLOR_BLUE);
        titleStyle.font = getAsset(Asset.FONT_ROBOTO_BOLD_45, BitmapFont.class);

        this.title = new Label(title, titleStyle);
        this.title.setTouchable(Touchable.disabled);
        this.title.setPosition(Utils.cell(5), Utils.cell(3));
        this.title.setHeight(Utils.cell(1));
        this.title.setWidth(Utils.cell(4.75f));
        this.title.setAlignment(Align.right);

        Label.LabelStyle timeStyle = new Label.LabelStyle();
        timeStyle.fontColor = new Color(Var.COLOR_BLACK);
        timeStyle.font = getAsset(Asset.FONT_ROBOTO_REGULAR_30, BitmapFont.class);

        this.personal = new Label("Best   " + personal, timeStyle);
        this.personal.setTouchable(Touchable.disabled);
        this.personal.setPosition(Utils.cell(5), Utils.cell(0));
        this.personal.setHeight(Utils.cell(0.75f));
        this.personal.setWidth(Utils.cell(4.75f));
        this.personal.setAlignment(Align.right);

        this.world = new Label("World   " + world, timeStyle);
        this.world.setTouchable(Touchable.disabled);
        this.world.setPosition(Utils.cell(5), Utils.cell(0.75f));
        this.world.setHeight(Utils.cell(0.75f));
        this.world.setWidth(Utils.cell(4.75f));
        this.world.setAlignment(Align.right);

        addActor(this.background);
        addActor(this.coverWhite);
        addActor(this.cover);
        addActor(this.lock);
        addActor(this.title);
        addActor(this.personal);
        // addActor(this.world);

        setHeight(Utils.cell(4));

        if (available) unlock();
        else lock();
    }

    public void lock() {
        available = false;
        cover.clearActions();
        lock.clearActions();
        personal.clearActions();
        cover.addAction(alpha(0.5f));
        lock.addAction(alpha(1f));
        personal.addAction(alpha(0f));
        //this.setTouchable(Touchable.disabled);
    }

    public void unlock() {
        available = true;
        cover.clearActions();
        lock.clearActions();
        personal.clearActions();
        lock.addAction(alpha(0f));
        //this.setTouchable(Touchable.enabled);
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
                    visitor.run();
                }
                else {
                    lock.clearActions();
                    lock.addAction(UIAction.blink());

                    // Si creara un metodo publico para lockBlink(), la Screen podria llamarlo si
                    // isLocked(), con lo que no necesitaria entrar con el Visitor. Sin embargo,
                    // necesitaria crear el listener, y es mas limpio hacerlo aqui. La Screen no
                    // tiene por que saber lo que hay que hacer si isLocked() es true; su
                    // responsabilidad es solo indicar la pantalla destino.
                }
            }
        });
    }

}