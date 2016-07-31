package com.jmolina.orb.widgets.ui;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.jmolina.orb.utils.Utils;
import com.jmolina.orb.var.Asset;
import com.jmolina.orb.var.Var;
import com.jmolina.orb.widgets.BaseGroup;

public class LadderRow extends BaseGroup {

    private Label rank;
    private Label time;
    private Label user;
    private Label globalRank;

    public LadderRow(AssetManager am, String rank, String time, String user) {
        this(am, rank, time, user, "");
    }

    public LadderRow(AssetManager am, String rank, String time, String user, String globalRank) {
        super(am);

        Label.LabelStyle regular = new Label.LabelStyle();
        Label.LabelStyle strong = new Label.LabelStyle();
        regular.fontColor = new Color(Var.COLOR_BLACK);
        strong.fontColor = new Color(Var.COLOR_BLACK);
        regular.font = getAssetManager().get(Asset.FONT_ROBOTO_REGULAR_30, BitmapFont.class);
        strong.font = getAssetManager().get(Asset.FONT_ROBOTO_BOLD_30, BitmapFont.class);

        this.rank = new Label(rank, strong);
        this.rank.setPosition(Utils.cell(0), Utils.cell(0));
        this.rank.setSize(Utils.cell(1), Utils.cell(0.5f));
        this.rank.setAlignment(Align.left);

        this.time = new Label(time, regular);
        this.time.setPosition(Utils.cell(1), Utils.cell(0));
        this.time.setSize(Utils.cell(2), Utils.cell(0.5f));
        this.time.setAlignment(Align.right);

        this.user = new Label(user, strong);
        this.user.setPosition(Utils.cell(4), Utils.cell(0));
        this.user.setSize(Utils.cell(3.5f), Utils.cell(0.5f));
        this.user.setAlignment(Align.left);
        this.user.setEllipsis(true);

        addActor(this.rank);
        addActor(this.time);
        addActor(this.user);

        if (!globalRank.equals("")) {
            this.globalRank = new Label(globalRank, regular);
            this.globalRank.setPosition(Utils.cell(7.5f), Utils.cell(0));
            this.globalRank.setSize(Utils.cell(1.5f), Utils.cell(0.5f));
            this.globalRank.setAlignment(Align.right);
            addActor(this.globalRank);
        }

        setHeight(Utils.cell(0.5f));
    }

    @Override
    public void dispose() {
        super.dispose();
    }

}