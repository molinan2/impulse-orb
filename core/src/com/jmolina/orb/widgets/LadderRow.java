package com.jmolina.orb.widgets;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.jmolina.orb.utils.Grid;
import com.jmolina.orb.assets.Asset;

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
        regular.fontColor = new Color(BaseGroup.COLOR_BLACK);
        strong.fontColor = new Color(BaseGroup.COLOR_BLACK);
        regular.font = getAssetManager().get(Asset.FONT_ROBOTO_REGULAR_30, BitmapFont.class);
        strong.font = getAssetManager().get(Asset.FONT_ROBOTO_BOLD_30, BitmapFont.class);

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

        addActor(this.rank);
        addActor(this.time);
        addActor(this.user);

        if (!globalRank.equals("")) {
            this.globalRank = new Label(globalRank, regular);
            this.globalRank.setPosition(Grid.unit(7.5f), Grid.unit(0));
            this.globalRank.setSize(Grid.unit(1.5f), Grid.unit(0.5f));
            this.globalRank.setAlignment(Align.right);
            addActor(this.globalRank);
        }

        setHeight(Grid.unit(0.5f));
    }

    @Override
    public void dispose() {
        super.dispose();
    }

}