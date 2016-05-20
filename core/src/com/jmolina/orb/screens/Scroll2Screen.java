package com.jmolina.orb.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.jmolina.orb.actors.LadderActor;

public class Scroll2Screen extends MenuScreen {

    private ScrollPane scrollPane;
    Texture knob_scroll, tfBackground, scroll_horizontal;

    public Scroll2Screen() {
        super();

        // Gdx.input.setInputProcessor(getStage());

        tfBackground = new Texture(Gdx.files.internal("layout/tfbackground.png"));
        scroll_horizontal = new Texture(Gdx.files.internal("layout/scroll_horizontal.png"));
        knob_scroll = new Texture(Gdx.files.internal("layout/knob_scroll.png"));

        ScrollPane.ScrollPaneStyle sps = new ScrollPane.ScrollPaneStyle();
        sps.background = new TextureRegionDrawable(new TextureRegion(tfBackground));
        sps.vScroll = new TextureRegionDrawable(new TextureRegion(scroll_horizontal));
        sps.vScrollKnob = new TextureRegionDrawable(new TextureRegion(knob_scroll));

        LadderActor ladder = new LadderActor();

        Texture ladderTexture = new Texture(Gdx.files.internal("ladder.png"));
        Image ladderImage = new Image(ladderTexture);

        scrollPane = new ScrollPane(ladderImage, sps);
        scrollPane.setWidth(scrollPane.getWidth() * 0.5f);
        scrollPane.setHeight(scrollPane.getHeight() * 0.5f);
        scrollPane.setPosition(100f, 100f);

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
