package com.jmolina.orb.widgets;

import com.badlogic.gdx.assets.AssetManager;
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
import com.jmolina.orb.utils.Grid;
import com.jmolina.orb.var.Asset;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

public class Card extends Base {

    private Image cover;
    private Image coverWhite;
    private Image background;
    private Image lock;

    private Label title;
    private Label personal;
    private Label world;

    private boolean locked;

    public Card(AssetManager am, String title, String personal, String world, Texture coverTexture) {
        this(am, title, personal, world, coverTexture, false);
    }

    public Card(AssetManager am, String title, String personal, String world, Texture coverTexture, boolean locked) {
        super(am);

        this.locked = locked;

        cover = new Image(coverTexture);
        coverWhite = new Image(getAsset(Asset.UI_CARD_COVER_WHITE, Texture.class));
        background = new Image(getAsset(Asset.UI_CARD_BACKGROUND, Texture.class));
        lock = new Image(getAsset(Asset.UI_CARD_PADLOCK, Texture.class));

        background.setPosition(0f, 0f);
        cover.setPosition(0f, 0f);
        coverWhite.setPosition(0f, 0f);
        lock.setPosition(Grid.unit(1.75f), Grid.unit(1));

        Label.LabelStyle titleStyle = new Label.LabelStyle();
        titleStyle.fontColor = new Color(Base.COLOR_BLUE);
        titleStyle.font = getAsset(Asset.FONT_ROBOTO_BOLD_45, BitmapFont.class);

        this.title = new Label(title, titleStyle);
        this.title.setTouchable(Touchable.disabled);
        this.title.setPosition(Grid.unit(5), Grid.unit(3));
        this.title.setHeight(Grid.unit(1));
        this.title.setWidth(Grid.unit(4.75f));
        this.title.setAlignment(Align.right);

        Label.LabelStyle timeStyle = new Label.LabelStyle();
        timeStyle.fontColor = new Color(Base.COLOR_BLACK);
        timeStyle.font = getAsset(Asset.FONT_ROBOTO_REGULAR_30, BitmapFont.class);

        this.personal = new Label("Best " + personal, timeStyle);
        this.personal.setTouchable(Touchable.disabled);
        this.personal.setPosition(Grid.unit(5), Grid.unit(0.75f));
        this.personal.setHeight(Grid.unit(0.75f));
        this.personal.setWidth(Grid.unit(4.75f));
        this.personal.setAlignment(Align.right);

        this.world = new Label("World " + world, timeStyle);
        this.world.setTouchable(Touchable.disabled);
        this.world.setPosition(Grid.unit(5), Grid.unit(0));
        this.world.setHeight(Grid.unit(0.75f));
        this.world.setWidth(Grid.unit(4.75f));
        this.world.setAlignment(Align.right);

        addActor(this.background);
        addActor(this.coverWhite);
        addActor(this.cover);
        addActor(this.lock);
        addActor(this.title);
        addActor(this.personal);
        addActor(this.world);

        setHeight(Grid.unit(4));

        if (isLocked()) lock();
        else unlock();
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    public void lock() {
        this.locked = true;

        this.cover.clearActions();
        this.lock.clearActions();
        this.personal.clearActions();

        this.cover.addAction(alpha(0.5f));
        this.lock.addAction(alpha(1f));
        this.personal.addAction(alpha(0f));

        //this.setTouchable(Touchable.disabled);
    }

    public void unlock() {
        this.locked = false;

        this.cover.clearActions();
        this.lock.clearActions();
        this.personal.clearActions();

        this.lock.addAction(alpha(0f));

        //this.setTouchable(Touchable.enabled);
    }

    public boolean isLocked() {
        return this.locked;
    }

    public void setOnClickOperation(final Visitor visitor) {
        this.clearListeners();
        this.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (!locked) {
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

    @Override
    public void reset() {
        if (this.isLocked())
            lock();
        else
            unlock();
    }
}