package com.jmolina.orb.widgets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Disposable;
import com.jmolina.orb.groups.BaseGroup;
import com.jmolina.orb.utils.Grid;
import com.jmolina.orb.var.Var;

public class LadderRow extends BaseGroup implements Disposable {

    private Label rank;
    private Label time;
    private Label user;
    private Label onlineRank;
    private BitmapFont regularFont;
    private BitmapFont strongFont;

    public LadderRow(String rank, String time, String user, String onlineRank) {
        regularFont = new BitmapFont(Gdx.files.internal("font/roboto_regular_30.fnt"));
        strongFont = new BitmapFont(Gdx.files.internal("font/roboto_bold_30.fnt"));

        Label.LabelStyle regular = new Label.LabelStyle();
        Label.LabelStyle strong = new Label.LabelStyle();
        regular.fontColor = new Color(Var.COLOR_BLACK);
        strong.fontColor = new Color(Var.COLOR_BLACK);
        regular.font = regularFont;
        strong.font = strongFont;

        this.rank = new Label(rank, strong);
        this.rank.setPosition(Grid.unit(0), Grid.unit(0));
        this.rank.setSize(Grid.unit(1), Grid.unit(0.5f));
        this.rank.setAlignment(Align.left);

        this.time = new Label(time, regular);
        this.time.setPosition(Grid.unit(1), Grid.unit(0));
        this.time.setSize(Grid.unit(2), Grid.unit(0.5f));
        this.time.setAlignment(Align.right);

        this.user = new Label(user, strong);
        this.user.setPosition(Grid.unit(4), Grid.unit(0));
        this.user.setSize(Grid.unit(3.5f), Grid.unit(0.5f));
        this.user.setAlignment(Align.left);
        this.user.setEllipsis(true);

        this.onlineRank = new Label(onlineRank, regular);
        this.onlineRank.setPosition(Grid.unit(7.5f), Grid.unit(0));
        this.onlineRank.setSize(Grid.unit(1.5f), Grid.unit(0.5f));
        this.onlineRank.setAlignment(Align.right);

        addActor(this.rank);
        addActor(this.time);
        addActor(this.user);
        addActor(this.onlineRank);

        setHeight(0.5f);
    }

    @Override
    public void dispose() {
        regularFont.dispose();
        strongFont.dispose();
    }

}