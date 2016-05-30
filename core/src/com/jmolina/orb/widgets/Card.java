package com.jmolina.orb.widgets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Disposable;
import com.jmolina.orb.groups.BaseGroup;
import com.jmolina.orb.interfaces.Visitor;
import com.jmolina.orb.utils.Grid;
import com.jmolina.orb.var.Var;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

public class Card extends BaseGroup implements Disposable {

    private Image cover;
    private Image coverWhite;
    private Image background;
    private Image lock;

    private Label title;
    private Label personal;
    private Label world;
    private BitmapFont titleFont;
    private BitmapFont timeFont;

    private Texture backgroundTexture;
    private Texture coverWhiteTexture;
    private Texture lockTexture;

    private boolean locked;

    public Card(String title, String personal, String world, Texture coverTexture) {
        this(title, personal, world, coverTexture, false);
    }

    public Card(String title, String personal, String world, Texture coverTexture, boolean locked) {
        super();

        this.locked = locked;

        backgroundTexture = new Texture(Gdx.files.internal("card_background.png"));
        coverWhiteTexture = new Texture(Gdx.files.internal("card_cover_white.png"));
        lockTexture = new Texture(Gdx.files.internal("padlock.png"));

        cover = new Image(coverTexture);
        coverWhite = new Image(coverWhiteTexture);
        background = new Image(backgroundTexture);
        lock = new Image(lockTexture);

        background.setPosition(0f, 0f);
        cover.setPosition(0f, 0f);
        coverWhite.setPosition(0f, 0f);
        lock.setPosition(Grid.unit(1.75f), Grid.unit(1));

        titleFont = new BitmapFont(Gdx.files.internal("font/roboto_bold_45.fnt"));
        titleFont.setColor(Color.WHITE);
        timeFont = new BitmapFont(Gdx.files.internal("font/roboto_regular_30.fnt"));
        timeFont.setColor(Color.WHITE);


        Label.LabelStyle titleLabelStyle = new Label.LabelStyle();
        titleLabelStyle.fontColor = new Color(Var.COLOR_BLUE);
        titleLabelStyle.font = titleFont;

        this.title = new Label(title, titleLabelStyle);
        this.title.setTouchable(Touchable.disabled);
        this.title.setPosition(Grid.unit(5), Grid.unit(3));
        this.title.setHeight(Grid.unit(1));
        this.title.setWidth(Grid.unit(4.75f));
        this.title.setAlignment(Align.right);


        Label.LabelStyle timeLabelStyle = new Label.LabelStyle();
        timeLabelStyle.fontColor = new Color(Var.COLOR_BLACK);
        timeLabelStyle.font = timeFont;

        this.personal = new Label("Best " + personal, timeLabelStyle);
        this.personal.setTouchable(Touchable.disabled);
        this.personal.setPosition(Grid.unit(5), Grid.unit(0.75f));
        this.personal.setHeight(Grid.unit(0.75f));
        this.personal.setWidth(Grid.unit(4.75f));
        this.personal.setAlignment(Align.right);

        this.world = new Label("World " + world, timeLabelStyle);
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
        backgroundTexture.dispose();
        coverWhiteTexture.dispose();
        lockTexture.dispose();
        timeFont.dispose();
        titleFont.dispose();
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
                    lock.addAction(sequence(
                            alpha(1f),
                            fadeOut(0),
                            fadeIn(0.5f)
                    ));
                }
            }
        });
    }
}