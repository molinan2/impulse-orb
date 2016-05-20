package com.jmolina.orb.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.jmolina.orb.actors.LadderActor;

/**
 * Test class
 */
public class ScrollScreen extends BaseScreen {

    //private BitmapFont font;
    private ScrollPane scrollPane;
    Texture knob_scroll;
    Texture tfBackground;
    Texture scroll_horizontal;

    public ScrollScreen() {
        super();

        // Gdx.input.setInputProcessor(getStage());

        //font = new BitmapFont(Gdx.files.internal("layout/font.fnt")); // TODO Crear con Hiero
        tfBackground = new Texture(Gdx.files.internal("layout/tfbackground.png"));
        scroll_horizontal = new Texture(Gdx.files.internal("layout/scroll_horizontal.png"));
        knob_scroll = new Texture(Gdx.files.internal("layout/knob_scroll.png"));

        ScrollPane.ScrollPaneStyle sps = new ScrollPane.ScrollPaneStyle();
        sps.background = new TextureRegionDrawable(new TextureRegion(tfBackground));
        sps.vScroll = new TextureRegionDrawable(new TextureRegion(scroll_horizontal));
        sps.vScrollKnob = new TextureRegionDrawable(new TextureRegion(knob_scroll));

        LadderActor ladder = new LadderActor();
        // ladder.setSize(500f, 500f);

        scrollPane = new ScrollPane(ladder, sps);
        scrollPane.debug();
        scrollPane.setWidth(200f);
        scrollPane.setHeight(100f);
        scrollPane.setPosition(200f, 200f);

        getStage().addActor(scrollPane);
    }

    @Override
    public void dispose() {
        //font.dispose();
        //knob_scroll.dispose();
        //tfBackground.dispose();

        super.dispose();
    }
}
