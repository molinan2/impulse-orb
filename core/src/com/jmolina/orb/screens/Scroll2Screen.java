package com.jmolina.orb.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;

public class Scroll2Screen extends BaseScreen {

    private ScrollPane scrollPane;
    Texture logo, actor, accept, checkBoxOn, checkBoxOff, tfSelection, tfBackground,
            tfCursor, scroll_horizontal, knob_scroll, plus, minus, touchpad_background,
            touchpad_knob, dialog_background, caveman, divider, progress_bar, knob_progress_bar, slider_background, slider_knob;
    private List<String> list;

    public Scroll2Screen() {
        Gdx.input.setInputProcessor(getStage());

        tfBackground = new Texture(Gdx.files.internal("layout/tfbackground.png"));
        scroll_horizontal = new Texture(Gdx.files.internal("layout/scroll_horizontal.png"));
        knob_scroll = new Texture(Gdx.files.internal("layout/knob_scroll.png"));

        ScrollPane.ScrollPaneStyle sps = new ScrollPane.ScrollPaneStyle();
        sps.background = new TextureRegionDrawable(new TextureRegion(tfBackground));
        sps.vScroll = new TextureRegionDrawable(new TextureRegion(scroll_horizontal));
        sps.vScrollKnob = new TextureRegionDrawable(new TextureRegion(knob_scroll));


        BitmapFont font = new BitmapFont(Gdx.files.internal("layout/font.fnt"));

        //List
        List.ListStyle listS = new List.ListStyle();
        listS.font = font;
        listS.fontColorSelected = Color.BLACK;
        listS.fontColorUnselected = Color.GRAY;
        listS.selection = new TextureRegionDrawable(new TextureRegion(tfBackground));

        list = new List<String>(listS);
        Array<String> items = new Array<String>();
        items.add("item1");
        items.addAll("item2", "item3", "item4");
        list.setItems(items);

        //ScrollPane
        List list2 = new List(listS);
        items.addAll("item5", "item6", "item7", "item8");
        list2.setItems(items);
        list2.pack();
        scrollPane = new ScrollPane(list2, sps);
        scrollPane.setWidth(scrollPane.getWidth()*0.4f);
        scrollPane.setHeight(scrollPane.getHeight()*0.4f);
        scrollPane.setPosition(100f, 100f);

        getStage().addActor(scrollPane);
    }


}
